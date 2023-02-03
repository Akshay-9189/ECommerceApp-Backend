package com.csi.repo;

import com.csi.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems, String> {

    CartItems findByCartItemsIdAndProductId(String cartItemsId, String productId);

    void deleteByCartItemsIdAndProductId(String cartItemsId, String productId);

    CartItems findByProductId(String productId);

    void  deleteByProductId(String productId);

    void deleteByCartItemsIdContains(String cartItemsId);
}
