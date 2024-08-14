package com.architecture.eda.internal.exceptions;

public class InvalidClientEmail extends RuntimeException{
    public InvalidClientEmail(String message) {
        super(message);
    }
}
