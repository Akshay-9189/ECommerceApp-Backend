package com.csi.dao.impl;

import com.csi.dao.ShoppingCartDao;
import com.csi.exception.ShoppingCartNotFound;
import com.csi.model.ShoppingCart;
import com.csi.repo.ShoppingCartRepo;
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
