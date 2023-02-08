package com.csi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
public class FallBackController {

    @GetMapping("/userfallback")
    public ResponseEntity<String> userFallBack() {
        return new ResponseEntity<>("User Service Is Down Please Try After Some Time !!!!!!",
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/productfallback")
    public ResponseEntity<String> productFallBack() {
        return new ResponseEntity<>("Product Service Is Down Please Try After Some Time !!!!!!",
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/cartfallback")
    public ResponseEntity<String> cartFallBack() {
        return new ResponseEntity<>("Cart Service Is Down Please Try After Some Time !!!!!!",
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/orderfallback")
    public ResponseEntity<String> orderFallBack() {
        return new ResponseEntity<>("Order Service Is Down Please Try After Some Time !!!!!!",
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/wishlistfallback")
    public ResponseEntity<?> wishlistFallBack() {
        return new ResponseEntity<>("Wishlist Service Is Down Please Try After Some Time !!!!!!",
                HttpStatus.SERVICE_UNAVAILABLE);
    }
}
