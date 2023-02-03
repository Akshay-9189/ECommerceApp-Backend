package com.csi.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CartItemNotFound.class , ShoppingCartNotFound.class , CouponNotFoundException.class })
    public ResponseEntity<?> cartItemNotFound(RuntimeException cartItemNotFound) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", cartItemNotFound.getMessage());
        map.put("HTTP Status Value :- ", HttpStatus.NOT_FOUND.value());
        map.put("HTTP Status :- ", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public ResponseEntity<?> productAlreadyExistsInCart(ProductAlreadyExists productAlreadyExists) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", productAlreadyExists.getMessage());
        map.put("HTTP Status Value :- ", HttpStatus.BAD_REQUEST.value());
        map.put("HTTP Status :- ", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> internalServerError(RuntimeException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", exception.getMessage());
        map.put("HTTP Status Value :- ", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("HTTP Status :- ", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
