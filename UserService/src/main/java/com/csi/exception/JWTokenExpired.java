package com.csi.exception;

public class JWTokenExpired extends RuntimeException {
    public JWTokenExpired(String msg) {
        super(msg);
    }
}
