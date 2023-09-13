package com.prototype.SpringPrototype.exceptionHandler;

public class SessionExpiredException extends RuntimeException {

    public SessionExpiredException(String message) {
        super(message);
    }
}

