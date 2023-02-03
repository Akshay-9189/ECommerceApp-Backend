package com.csi.service;

import com.csi.model.CartItems;
import org.springframework.cache.annotation.Cacheable;

public interface CartItemsService {

    @Cacheable(value = "cartItemsId")
    CartItems getById(String cartItemsId);
}
