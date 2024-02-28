package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.OkResponse;
import org.masonord.response.Response;

public class SetCommand implements CommandInterface<String> {
    private final String value;
    private final String key;

    public SetCommand(String[] args)throws InvalidCommand {
        if (args.length < 2) {
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

        return new OkResponse();
    }
}
