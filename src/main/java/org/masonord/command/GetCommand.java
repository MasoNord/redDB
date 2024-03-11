package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.Response;
import org.masonord.response.ResponseString;

public class GetCommand implements CommandInterface<String> {
    private String key;

    public GetCommand(String[] args) throws InvalidCommand {
        if (args.length != 1) {
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

        return new ResponseString(value);
    }
}
