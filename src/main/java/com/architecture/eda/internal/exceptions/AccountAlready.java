package com.architecture.eda.internal.exceptions;

public class AccountAlready extends RuntimeException{
    public AccountAlready(String message) {
        super(message);
    }
}
