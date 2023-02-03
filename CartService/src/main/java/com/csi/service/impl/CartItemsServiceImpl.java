package com.csi.service.impl;

import com.csi.dao.CartItemsDao;
import com.csi.model.CartItems;
import com.csi.service.CartItemsService;
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
