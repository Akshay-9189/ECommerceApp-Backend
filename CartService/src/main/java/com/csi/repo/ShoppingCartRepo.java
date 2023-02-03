package com.csi.repo;

import com.csi.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, String> {

    ShoppingCart findByUserId(String userId);

    boolean existsByUserId(String userId);

}
