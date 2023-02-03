package com.csi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({OrderNotFound.class , NullPointerException.class})
    public ResponseEntity<?> orderNotFoundException(RuntimeException runtimeException) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", runtimeException.getMessage());
        map.put("HTTP Status Value :- ", HttpStatus.NOT_FOUND.value());
        map.put("HTTP Status :- ", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> getException(Exception exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", exception.getMessage());
        map.put("HTTP Status Value :- ", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("HTTP Status :- ", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
