package com.prototype.SpringPrototype.exceptionHandler;

public class UserAccessException extends RuntimeException {

    public UserAccessException(String message) {
        super(message);
    }
}
