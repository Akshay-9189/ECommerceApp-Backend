package com.csi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IncorrectDetails.class, BadCredentialsException.class})
    public ResponseEntity<?> incorrectDetails(RuntimeException incorrectDetails) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", incorrectDetails.getMessage());
        map.put("Status Code :- ", HttpStatus.BAD_REQUEST.value());
        map.put("Status :- ", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> userNotFound(UserNotFound userNotFound) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", userNotFound.getMessage());
        map.put("Status Code :- ", HttpStatus.NOT_FOUND.value());
        map.put("Status :- ", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDenied(AccessDeniedException accessDeniedException) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", accessDeniedException.getMessage());
        map.put("Status Code :- ", HttpStatus.FORBIDDEN.value());
        map.put("Status :- ", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> exceptionHAndler(RuntimeException exception) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Message :- ", exception.getMessage());
        map.put("Cause :- ", exception.getCause());
        map.put("Status Code :- ", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("Status :- ", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}