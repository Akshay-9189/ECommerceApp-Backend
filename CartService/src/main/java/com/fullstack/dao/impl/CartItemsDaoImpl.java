package com.fullstack.dao.impl;

import com.fullstack.dao.CartItemsDao;
import com.fullstack.exception.CartItemNotFound;
import com.fullstack.model.CartItems;
import com.fullstack.repo.CartItemsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartItemsDaoImpl implements CartItemsDao {

    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Override
    public CartItems saveCartItem(CartItems cartItems) {
        return cartItemsRepo.save(cartItems);
    }

    @Override
    public CartItems getCartItems(String cartItemsId) {
        return cartItemsRepo.findById(cartItemsId).orElseThrow(() -> new CartItemNotFound("Cart Items Not Found"));
    }

    @Override
    public void deleteCart(String cartItemsId) {
        CartItems cartItems = cartItemsRepo.findById(cartItemsId).orElseThrow(() -> new CartItemNotFound("Cart Items Not Found"));
        if (cartItems != null) {
             cartItemsRepo.delete(cartItems);
        }
    }
}
