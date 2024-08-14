package com.architecture.eda.pkg.events.exceptions;

public class HandleAlreadyRegistered extends RuntimeException{
    public HandleAlreadyRegistered(String message) {
        super(message);
    }
}
