package com.architecture.eda.share.events.exceptions;

public class HandleAlreadyRegistered extends RuntimeException{
    public HandleAlreadyRegistered(String message) {
        super(message);
    }
}
