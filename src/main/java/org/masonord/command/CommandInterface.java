package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;

public interface CommandInterface<T> {
    Response<T> execute() throws InvalidCommand;

}
