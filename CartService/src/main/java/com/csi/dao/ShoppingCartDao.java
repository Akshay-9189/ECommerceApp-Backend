package com.csi.dao;

import com.csi.model.ShoppingCart;

public interface ShoppingCartDao {

    ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart getCartById(String shoppingCartId);

    ShoppingCart getByUserId(String userId);

    boolean checkUserHaveShoppingCart(String userId);
}
