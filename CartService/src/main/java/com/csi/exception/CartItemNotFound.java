package com.csi.exception;

public class CartItemNotFound extends RuntimeException {
    public CartItemNotFound(String message) {
        super(message);
    }
}
