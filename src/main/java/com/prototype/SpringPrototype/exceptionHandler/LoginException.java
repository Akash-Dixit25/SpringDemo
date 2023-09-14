package com.prototype.SpringPrototype.exceptionHandler;

public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }
}
