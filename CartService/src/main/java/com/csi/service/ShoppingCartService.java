package com.csi.service;

import com.csi.model.ShoppingCart;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart createShoppingCart(String userId);

    ShoppingCart addProductToCart(String shoppingCartId, String productId);

    ShoppingCart increaseProductQuantityFromCart(String shoppingCartId, String productId);

    ShoppingCart removeCartItemFromShoppingCart(String shoppingCartId, String cartItemsId);

    ShoppingCart addCouponToCart(String userId, String couponName);

    ShoppingCart decreaseProductQuantityFromCart(String shoppingCartId, String cartItemsId);

    ShoppingCart getUser(String userId);

    ShoppingCart cleanCart(String shoppingCartId, List<String> cartIds);

    boolean checkUserHaveShoppingCart(String userId);

    ShoppingCart deleteCouponFromCart(String shoppingCartId);

    @Cacheable(value = "shoppingCartId")
    ShoppingCart getShoppingCart(String shoppingCartId);
}
