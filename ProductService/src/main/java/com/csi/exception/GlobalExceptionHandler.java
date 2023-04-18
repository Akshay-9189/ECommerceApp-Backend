package com.csi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductNotFound.class, ProductOutOfStock.class, CategoryNotFound.class, SubCategoryNotFound.class})
    public ResponseEntity<?> handleNotFound(RuntimeException runtimeException) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Message :-", runtimeException.getMessage());
        map.put("Status Code :- ", HttpStatus.NOT_FOUND.value());
        map.put("Status :- ", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileFormatNotMatch.class)
    public ResponseEntity<?> fileFormatNotMatch(FileFormatNotMatch fileFormatNotMatch) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Message :-", fileFormatNotMatch.getMessage());
        map.put("Status Code :- ", HttpStatus.BAD_REQUEST.value());
        map.put("Status :- ", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> fileNotFound(IOException ioException) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Message :- ", ioException.getMessage());
        map.put("Status Code :- ", HttpStatus.NOT_FOUND.value());
        map.put("Status :- ", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> internalServerError(RuntimeException exception) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Message :- ", exception.getMessage());
        map.put("Cause :- ", exception.getCause());
        map.put("Status Code :- ", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("Status :- ", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxFileSizeExceed(MaxUploadSizeExceededException maxUploadSizeExceededException) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Message :- ", maxUploadSizeExceededException.getMessage());
        map.put("Status Code :- ", HttpStatus.EXPECTATION_FAILED.value());
        map.put("Status :- ", HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
    }
}