package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;

public class QuitCommand implements CommandInterface{
    @Override
    public Response execute() throws InvalidCommand {
        return null;
    }
}
