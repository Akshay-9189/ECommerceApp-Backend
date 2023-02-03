package com.csi.exception;

public class ProductOutOfStock extends RuntimeException {
    public ProductOutOfStock(String msg) {
        super(msg);
    }
}
