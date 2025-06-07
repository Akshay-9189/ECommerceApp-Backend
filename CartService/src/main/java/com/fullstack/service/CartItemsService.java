package com.fullstack.service;

import com.fullstack.model.CartItems;
import org.springframework.cache.annotation.Cacheable;

public interface CartItemsService {

    @Cacheable(value = "cartItemsId")
    CartItems getById(String cartItemsId);
}
