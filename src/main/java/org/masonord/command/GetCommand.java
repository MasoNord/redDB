package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.Response;
import org.masonord.response.ResponseString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCommand implements CommandInterface<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetCommand.class);
    private String key;

    public GetCommand(String[] args) throws InvalidCommand {
        if (args.length != 1) {
            LOGGER.error("Invalid number of arguments: " + args.length + ". Provide only one");
            throw new InvalidCommand("Not enough args");
        }

        key = args[0];
    }

    @Override
    public Response<String> execute() throws InvalidCommand {
        String value = InMemoryDB.INSTANCE.get(key, String.class);

        if (value == null)  {
            throw new InvalidCommand("The key not found");
        }

        LOGGER.info("Performing GET command...");
        return new ResponseString(value);
    }
}
