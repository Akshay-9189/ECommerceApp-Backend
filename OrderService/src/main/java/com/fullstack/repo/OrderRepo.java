package com.fullstack.repo;

import com.fullstack.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetails, String> {
    List<OrderDetails> findByProductId(String productId);
}
