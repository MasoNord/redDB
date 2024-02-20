package org.masonord.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum RedisCommand {

    PING,
    ECHO;

    private static final Map<String, RedisCommand> map =
            Arrays.stream(RedisCommand.values())
                    .collect(Collectors.toMap(
                            redisCommand -> redisCommand.name().toLowerCase(), it -> it));

    public static RedisCommand getCommand(String command) {
        return map.get(command.toLowerCase());
    }
}
