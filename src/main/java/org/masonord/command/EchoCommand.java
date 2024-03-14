package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;
import org.masonord.response.ResponseString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class EchoCommand implements CommandInterface<String>{
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoCommand.class);
    private String message;

    public EchoCommand(String[] args) throws InvalidCommand {
        if (args.length != 1) {
            LOGGER.error("Invalid number of arguments: " + args.length + ". Provide only one");
            throw new InvalidCommand("Not enough args");
        }
        this.message = args[0];
    }

    @Override
    public Response<String> execute() throws InvalidCommand {
        if (Objects.isNull(message)) {
            throw new InvalidCommand("Null value provided");
        }

        LOGGER.info("Performing ECHO command...");
        return new ResponseString(message);
    }
}
