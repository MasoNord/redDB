package org.masonord;

import org.masonord.enums.DataType;
import org.masonord.enums.RedisCommand;
import org.masonord.exception.RedisExecuteException;
import org.masonord.records.RedisResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class RedisExecutor {
    private RedisExecutor() {}
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisExecutor.class);

    public static void execute(BufferedWriter writer, List<String> params) {
        try {
            if (!checkSupported(params.getFirst())) {
                returnCommonErrorMessage(writer);
            }

            List<RedisResultData> data = executeCommand(params);
            String outputStr = convertToOutputString(data);

            LOGGER.debug("output: {}", outputStr);
            writer.write(outputStr);
            writer.flush();
        } catch (RuntimeException e) {
            LOGGER.warn("command execute error - inputParams: {}", params, e);
            returnCommonErrorMessage(writer);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

    }

    private static boolean checkSupported(String command) {
        return !Objects.isNull(RedisCommand.getCommand(command));
    }

    private static void returnCommonErrorMessage(BufferedWriter in) {
        try {
            in.write("-ERR\r\n");
            in.flush();
        }catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }

    private static List<RedisResultData> executeCommand(List<String> inputParams) {
        RedisCommand command = RedisCommand.getCommand(inputParams.getFirst());
        List<String> restParams = inputParams.subList(1, inputParams.size());

        return switch (command) {
            case PING -> ping();
            case ECHO -> echo(restParams);
        };
    }

    private static String convertToOutputString(List<RedisResultData> redisResultDataList) {
        StringBuilder result = new StringBuilder();

        for (var redisResultData : redisResultDataList) {
            result.append(redisResultData.dataType().getValue());
            result.append(redisResultData.data());
            result.append("\r\n");
        }

        return result.toString();
    }


    private static List<RedisResultData> ping() {
        return List.of(new RedisResultData(
                DataType.SIMPLE_STRINGS,
                Constants.PONG));
    }


    private static List<RedisResultData> echo(List<String> args) {
        if (args.size() != 1) {
            throw new RedisExecuteException("execute error - echo need exact 1 args");
        }

        String str = args.getFirst();
        RedisResultData sizeData = new RedisResultData(DataType.BULK_STRING, String.valueOf(str.length()));
        RedisResultData strData = new RedisResultData(DataType.EMPTY_TYPE, str);

        return List.of(sizeData, strData);
    }
}

