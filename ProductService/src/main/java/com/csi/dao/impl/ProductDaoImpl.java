package com.csi.dao.impl;

import com.csi.dao.ProductDao;
import com.csi.exception.ProductNotFound;
import com.csi.model.Product;
import com.csi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getProduct(String productId) {
        return productRepo.findById(productId).orElseThrow(() -> new ProductNotFound("Product Not Found"));
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFound("Product Not Found"));
        productRepo.delete(product);
    }

    @Override
    public Product getByProductName(String productName) {
        return productRepo.findByProductName(productName);
    }
}
