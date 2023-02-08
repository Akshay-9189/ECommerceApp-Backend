package com.csi.controller;

import com.csi.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItems")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")

public class CartItemsController {

    @Autowired
    private CartItemsService cartItemsService;

    @GetMapping("/get-cart items/{cartItemsId}")
    public ResponseEntity<?> getCartItems(@PathVariable String cartItemsId) {
        return new ResponseEntity<>(cartItemsService.getById(cartItemsId), HttpStatus.OK);
    }
}
