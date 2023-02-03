package com.csi.repo;

import com.csi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, String> {

    Product findByProductName(String productName);
}
