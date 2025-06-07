package com.fullstack.exception;

public class FileFormatNotMatch extends RuntimeException {
    public FileFormatNotMatch(String msg) {
        super(msg);
    }
}
