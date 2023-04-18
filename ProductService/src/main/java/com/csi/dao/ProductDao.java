package com.csi.dao;

import com.csi.model.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ProductDao {

    @Cacheable(value = "Products")
    List<Product> getAllProduct();

    @CacheEvict(value = {"Products", "ProductById", "ProductByName"}, allEntries = true)
    Product saveProduct(Product product);

    @Cacheable(value = "ProductById")
    Product getProduct(String productId);

    @CacheEvict(value = {"Products", "ProductById", "ProductByName"}, allEntries = true)
    Product updateProduct(Product product);

    @CacheEvict(value = {"Products", "ProductById", "ProductByName"}, allEntries = true)
    void deleteProduct(String productId);

    @Cacheable(value = "ProductByName")
    Product getByProductName(String productName);
}