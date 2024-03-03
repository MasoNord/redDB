package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;
import org.masonord.response.ResponseString;

import java.util.Objects;

public class EchoCommand implements CommandInterface<String>{
    private String message;

    public EchoCommand(String[] args) throws InvalidCommand {
        if (args.length != 1) {
            throw new InvalidCommand("Invalid number of arguments");
        }
        this.message = args[0];
    }

    @Override
    public Response<String> execute() throws InvalidCommand {
        if (Objects.isNull(message)) {
            throw new InvalidCommand("Null value provided");
        }

        return new ResponseString(message);
    }
}
