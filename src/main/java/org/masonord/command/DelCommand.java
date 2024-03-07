package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.Response;
import org.masonord.response.ResponseInteger;

public class DelCommand implements CommandInterface<Integer> {
    private final String[] keys;

    public DelCommand(String[] args) throws InvalidCommand {
        if (args.length == 0) {
            throw new InvalidCommand("Not enough arguments");
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

        return new ResponseInteger(response);
    }
}
