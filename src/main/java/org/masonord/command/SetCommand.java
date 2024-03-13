package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.OkResponse;
import org.masonord.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetCommand implements CommandInterface<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetCommand.class);
    private final String value;
    private final String key;

    public SetCommand(String[] args)throws InvalidCommand {
        if (args.length != 2) {
            LOGGER.error("Invalid number of arguments: " + args.length + " provide only two");
            throw new InvalidCommand("Not enough args");
        }

        key = args[0];
        value = args[1];
    }

    @Override
    public Response<String> execute() throws InvalidCommand {
        synchronized (InMemoryDB.INSTANCE) {
            InMemoryDB.INSTANCE.put(key, value);
        }

        LOGGER.info("Performing SET command...");
        return new OkResponse();
    }
}
