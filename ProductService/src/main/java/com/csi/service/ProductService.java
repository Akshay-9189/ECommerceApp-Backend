package com.csi.service;

import com.csi.dto.product.SaveProductDTO;
import com.csi.model.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Product saveProduct(SaveProductDTO product, MultipartFile multipartFile) throws IOException;

    @Cacheable(value = "productId")
    Product getProduct(String productId);

    Product updateProduct(String productId, SaveProductDTO product, MultipartFile multipartFile) throws IOException;

    void deleteProduct(String productId);

    String uploadFile(MultipartFile multipartFile) throws IOException;

    Product decreaseProductQuantity(String productId, int productSelectedQuantity);

    Product increaseProductQuantity(String productId, int productQuantity);

    @Cacheable(value = "productName")
    Product getByProductName(String productName);

    List<Product> filterProductBySubCategory(String subCategoryName);

    List<Product> filterProductByPrice(int minPrice, int maxPrice);

    List<Product> sortProductByNameA_Z();

    List<Product> sortProductByNAmeZ_A();

    List<Product> filterByProductBelowPrice(int price);

    List<Product> filterByProductAbovePrice(int price);

    List<Product> sortProductByPriceAscending();

    List<Product> sortProductByPriceDescending();

    List<Product> filterProductByName(String productName);
}
