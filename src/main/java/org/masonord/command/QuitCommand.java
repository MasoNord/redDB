package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.response.OkResponse;
import org.masonord.response.Response;

public class QuitCommand implements CommandInterface<String>{

    public QuitCommand(String[] args) throws InvalidCommand {
        if (args.length > 0) {
            throw new InvalidCommand("Wrong number of arguments");
        }
    }

    @Override
    public Response<String> execute() {
        return new OkResponse();
    }
}
