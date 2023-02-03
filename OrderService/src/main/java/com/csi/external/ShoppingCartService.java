package com.csi.external;

import com.csi.vo.CartItems;
import com.csi.vo.ShoppingCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "CartService")
public interface ShoppingCartService {

    @GetMapping("/cart/get-shopping-cart/{userId}")
    ShoppingCart getShoppingCart(@PathVariable String userId);

    @GetMapping("/cartItems/get-cart items/{cartItemsId}")
    CartItems getCartItems(@PathVariable String cartItemsId);
}
