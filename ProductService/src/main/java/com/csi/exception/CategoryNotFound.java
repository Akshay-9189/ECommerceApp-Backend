package com.csi.exception;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(String msg) {
        super(msg);
    }
}
