package com.fullstack.repo;

import com.fullstack.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, String> {

    ShoppingCart findByUserId(String userId);

    boolean existsByUserId(String userId);

}
