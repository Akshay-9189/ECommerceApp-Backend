package com.csi.dao;

import com.csi.model.CartItems;

public interface CartItemsDao {

    CartItems saveCartItem(CartItems cartItems);

    CartItems getCartItems(String cartItemsId);

    void deleteCart(String cartItemsId);
}
