package com.fullstack.exception;

public class SubCategoryNotFound extends RuntimeException {
    public SubCategoryNotFound(String message) {
        super(message);
    }
}
