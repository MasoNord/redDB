package org.masonord.exception;

public class PropertyNotFoundException extends Exception {

    public PropertyNotFoundException() {

    }

    public PropertyNotFoundException(String message) {
        super(message);
    }

    public PropertyNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
