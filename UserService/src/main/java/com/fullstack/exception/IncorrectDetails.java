package com.fullstack.exception;

public class IncorrectDetails extends RuntimeException {
    public IncorrectDetails(String message) {
        super(message);
    }
}
