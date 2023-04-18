package com.csi.dao;

import com.csi.model.ShoppingCart;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface ShoppingCartDao {

    @CacheEvict(value = {"ShoppingCardByID", "ShoppingCartByUserID"}, allEntries = true)
    ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);

    @Cacheable(value = "ShoppingCardByID")
    ShoppingCart getCartById(String shoppingCartId);

    @Cacheable(value = "ShoppingCartByUserID")
    ShoppingCart getByUserId(String userId);

    boolean checkUserHaveShoppingCart(String userId);
}