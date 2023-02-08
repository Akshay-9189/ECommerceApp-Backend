package com.csi.repo;

import com.csi.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems, String> {
}
