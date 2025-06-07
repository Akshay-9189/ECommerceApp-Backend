package com.fullstack.service.impl;

import com.fullstack.dao.CartItemsDao;
import com.fullstack.model.CartItems;
import com.fullstack.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CartItemsServiceImpl implements CartItemsService {

    @Autowired
    private CartItemsDao cartItemsDao;

    @Override
    public CartItems getById(String cartItemsId) {
        return cartItemsDao.getCartItems(cartItemsId);
    }
}
