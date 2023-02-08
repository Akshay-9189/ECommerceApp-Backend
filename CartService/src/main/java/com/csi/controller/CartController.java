package com.csi.controller;

import com.csi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
public class CartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/create-shopping-cart/{userId}")
    public ResponseEntity<?> createCart(@PathVariable String userId) {
        if (!shoppingCartService.checkUserHaveShoppingCart(userId)) {
            return new ResponseEntity<>(shoppingCartService.createShoppingCart(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(shoppingCartService.getUser(userId), HttpStatus.OK);
        }
    }

    @PostMapping("/add-product-to-cart/{shoppingCartId}/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable String shoppingCartId, @PathVariable String productId) {
        return new ResponseEntity<>(shoppingCartService.addProductToCart(shoppingCartId, productId), HttpStatus.OK);
    }

    @PostMapping("/increase-product-quantity-in-cart/{shoppingCartId}/{productId}")
    public ResponseEntity<?> increaseProductQuantityToCart(@PathVariable String shoppingCartId, @PathVariable String productId) {
        return new ResponseEntity<>(shoppingCartService.increaseProductQuantityFromCart(shoppingCartId, productId), HttpStatus.OK);
    }

    @PostMapping("/decrease-product-from-cart/{shoppingCartId}/{cartItemsId}")
    public ResponseEntity<?> decreaseProductFromCart(@PathVariable String shoppingCartId, @PathVariable String cartItemsId) {
        return new ResponseEntity<>(shoppingCartService.decreaseProductQuantityFromCart(shoppingCartId, cartItemsId), HttpStatus.OK);
    }

    @DeleteMapping("/remove-cart-item-from-shopping-cart/{shoppingCartId}/{cartItemsId}")
    public ResponseEntity<?> removeCartItem(@PathVariable String shoppingCartId, @PathVariable String cartItemsId) {
        return new ResponseEntity<>(shoppingCartService.removeCartItemFromShoppingCart(shoppingCartId, cartItemsId), HttpStatus.OK);
    }

    @DeleteMapping("/clear-cart/{shoppingCartId}/{cartIds}")
    public ResponseEntity<?> clearCart(@PathVariable String shoppingCartId, @PathVariable List<String> cartIds) {
        return new ResponseEntity<>(shoppingCartService.cleanCart(shoppingCartId, cartIds), HttpStatus.OK);
    }

    @PostMapping("/add-coupon/{shoppingCartId}/{couponName}")
    public ResponseEntity<?> addCoupon(@PathVariable String shoppingCartId, @PathVariable String couponName) {
        return new ResponseEntity<>(shoppingCartService.addCouponToCart(shoppingCartId, couponName), HttpStatus.OK);
    }

    @DeleteMapping("/delete-coupon/{shoppingCartId}")
    public ResponseEntity<?> deleteCoupon(@PathVariable String shoppingCartId) {
        return new ResponseEntity<>(shoppingCartService.deleteCouponFromCart(shoppingCartId), HttpStatus.OK);
    }

    @GetMapping("/get-shopping-cart/{userId}")
    public ResponseEntity<?> getShoppingCart(@PathVariable String userId) {
        return new ResponseEntity<>(shoppingCartService.getUser(userId), HttpStatus.OK);
    }
}