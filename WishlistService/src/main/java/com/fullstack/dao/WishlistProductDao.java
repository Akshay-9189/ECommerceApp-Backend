package com.fullstack.dao;

import com.fullstack.model.WishlistProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistProductDao extends JpaRepository<WishlistProduct, String> {

    WishlistProduct findByProductId(String productId);
}
