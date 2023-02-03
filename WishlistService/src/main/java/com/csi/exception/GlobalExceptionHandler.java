package com.csi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WishlistNotFound.class)
    public ResponseEntity<?> wishListNotFound(WishlistNotFound wishlistNotFound){
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", wishlistNotFound.getMessage());
        map.put("HTTP Status Value :- ", HttpStatus.NOT_FOUND.value());
        map.put("HTTP Status :- ", HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(map , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public  ResponseEntity<?> runtimeException(RuntimeException runtimeException){
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", runtimeException.getMessage());
        map.put("HTTP Status Value :- ", HttpStatus.NOT_FOUND.value());
        map.put("HTTP Status :- ", HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
