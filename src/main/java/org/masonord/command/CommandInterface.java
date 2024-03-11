package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;
import org.masonord.response.ResponseInteger;

public interface CommandInterface<T> {
    Response<T> execute() throws InvalidCommand;
}
