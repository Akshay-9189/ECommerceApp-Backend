package com.fullstack.exception;

public class ProductNotFound extends RuntimeException {

    public ProductNotFound(String message) {
        super(message);
    }
}
