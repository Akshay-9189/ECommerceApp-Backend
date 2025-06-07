package com.fullstack.external;

import com.fullstack.vo.CartItems;
import com.fullstack.vo.ShoppingCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CartService")
public interface ShoppingCartService {

    @GetMapping("/cart/get-shopping-cart/{userId}")
    ShoppingCart getShoppingCart(@PathVariable String userId);

    @GetMapping("/cartItems/get-cart items/{cartItemsId}")
    CartItems getCartItems(@PathVariable String cartItemsId);
}
