package com.csi.dao;

import com.csi.model.CartItems;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface CartItemsDao {

    @CacheEvict(value = {"CartItemsByID", "ShoppingCardByID", "ShoppingCartByUserID"}, allEntries = true)
    CartItems saveCartItem(CartItems cartItems);

    @Cacheable(value = "CartItemsByID")
    CartItems getCartItems(String cartItemsId);

    @CacheEvict(value = {"CartItemsByID", "ShoppingCardByID", "ShoppingCartByUserID"}, allEntries = true)
    void deleteCart(String cartItemsId);
}