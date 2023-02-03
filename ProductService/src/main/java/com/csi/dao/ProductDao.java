package com.csi.dao;

import com.csi.exception.ProductNotFound;
import com.csi.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> getAllProduct();

    Product saveProduct(Product product);

    Product getProduct(String productId);

    Product updateProduct(Product product);

    void deleteProduct(String productId);

    Product getByProductName(String productName);
}
