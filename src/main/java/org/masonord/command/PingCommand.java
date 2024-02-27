package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;
import org.masonord.response.ResponseString;

public class PingCommand implements CommandInterface<String> {
    @Override
    public Response<String> execute() throws InvalidCommand {
        return new ResponseString("PONG");
    }
}
