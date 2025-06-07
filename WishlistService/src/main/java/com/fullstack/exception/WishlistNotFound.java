package com.fullstack.exception;

public class WishlistNotFound extends RuntimeException {
    public WishlistNotFound(String message) {
        super(message);
    }
}
