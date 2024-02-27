package org.masonord.exception;

public class InvalidCommand extends Exception {

    public InvalidCommand(String message) {
        super(message);
    }

    public InvalidCommand(String message, Throwable e) {
        super(message, e);
    }
}
