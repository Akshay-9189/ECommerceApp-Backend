package com.csi.exception;

public class SubCategoryNotFound extends RuntimeException {
    public SubCategoryNotFound(String message) {
        super(message);
    }
}
