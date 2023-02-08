package com.csi.repo;

import com.csi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {

    Product findByProductName(String productName);
}
