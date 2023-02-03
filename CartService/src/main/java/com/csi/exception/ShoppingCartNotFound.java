package com.csi.exception;

public class ShoppingCartNotFound extends RuntimeException {
    public ShoppingCartNotFound(String message) {
        super(message);
    }
}
