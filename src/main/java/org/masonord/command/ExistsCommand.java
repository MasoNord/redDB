package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.Response;
import org.masonord.response.ResponseInteger;

public class ExistsCommand implements CommandInterface<Integer> {

    private final String[] keys;

    public ExistsCommand(String[] args) throws InvalidCommand {
        if (args.length < 1) {
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
        return new ResponseInteger(count);
    }
}
