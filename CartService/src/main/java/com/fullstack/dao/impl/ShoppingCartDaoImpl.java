package com.fullstack.dao.impl;

import com.fullstack.dao.ShoppingCartDao;
import com.fullstack.exception.ShoppingCartNotFound;
import com.fullstack.model.ShoppingCart;
import com.fullstack.repo.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Override
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public ShoppingCart getCartById(String shoppingCartId) {
        return shoppingCartRepo.findById(shoppingCartId).orElseThrow(() -> new ShoppingCartNotFound("Shopping Cart Not Found"));
    }

    @Override
    public ShoppingCart getByUserId(String userId) {
        return shoppingCartRepo.findByUserId(userId);
    }

    @Override
    public boolean checkUserHaveShoppingCart(String userId) {
        return shoppingCartRepo.existsByUserId(userId);
    }
}
