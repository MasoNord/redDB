package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.Response;
import org.masonord.response.ResponseInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExistsCommand implements CommandInterface<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExistsCommand.class);
    private final String[] keys;

    public ExistsCommand(String[] args) throws InvalidCommand {
        if (args.length < 1) {
            LOGGER.error("Invalid number of arguments: " + args.length + " provide at least one");
            throw new InvalidCommand("Not enough args");
        }
        keys = new String[args.length];
        System.arraycopy(args, 0, keys, 0, keys.length);
    }

    @Override
    public Response<Integer> execute() throws InvalidCommand {
        int count = 0;

        for (String k : keys) {
            if (InMemoryDB.INSTANCE.containsKey(k)) {
                count++;
            }
        }

        LOGGER.info("Performing EXISTS command...");
        return new ResponseInteger(count);
    }
}
