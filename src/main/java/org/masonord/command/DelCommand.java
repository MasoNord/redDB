package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.Response;
import org.masonord.response.ResponseInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelCommand implements CommandInterface<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DelCommand.class);

    private final String[] keys;

    public DelCommand(String[] args) throws InvalidCommand {
        if (args.length == 0) {
            LOGGER.error("Invalid number of arguments: " + args.length + ". Provide at least one");
            throw new InvalidCommand("Not enough args");
        }
        keys = new String[args.length];
        System.arraycopy(args, 0, keys, 0, keys.length);
    }

    @Override
    public Response<Integer> execute() throws InvalidCommand {
        int response = 0;

        synchronized (InMemoryDB.INSTANCE) {
            for (String s : keys) {
                if (InMemoryDB.INSTANCE.containsKey(s)) {
                    response++;
                    InMemoryDB.INSTANCE.remove(s);
                }
            }
        }


        LOGGER.info("Performing DEL command...");
        return new ResponseInteger(response);
    }
}
