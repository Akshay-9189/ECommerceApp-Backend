package com.csi.dao;

import com.csi.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistDao extends JpaRepository<Wishlist, String> {

    Wishlist findByUserId(String userId);

    boolean existsByUserId(String userId);
}
