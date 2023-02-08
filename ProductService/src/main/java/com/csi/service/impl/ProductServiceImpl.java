package com.csi.service.impl;

import com.csi.dao.ProductDao;
import com.csi.dao.SubCategoryDao;
import com.csi.dto.product.SaveProductDTO;
import com.csi.exception.ProductOutOfStock;
import com.csi.external.OrderService;
import com.csi.model.Product;
import com.csi.model.SubCategory;
import com.csi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Autowired
    private OrderService orderService;

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public Product saveProduct(SaveProductDTO product, MultipartFile multipartFile) throws IOException {
        SubCategory subCategory = subCategoryDao.getSubCategoryByName(product.getSubCategoryName());
        Product product1 = new Product();
        product1.setProductName(product.getProductName());
        product1.setProductWeight(product.getProductWeight());
        product1.setProductDescription(product.getProductDescription());
        product1.setProductCode(product.getProductCode());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductTotalQuantityAvailable(product.getProductTotalQuantityAvailable());
        product1.setImageURL(uploadFile(multipartFile));
        product1.setSubCategory(subCategory);
        return productDao.saveProduct(product1);
    }

    @Override
    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    @Override
    public Product updateProduct(String productId, SaveProductDTO product, MultipartFile multipartFile) throws IOException {
        SubCategory subCategory = subCategoryDao.getSubCategoryByName(product.getSubCategoryName());

        Product product1 = getProduct(productId);
        product1.setProductName(product.getProductName());
        product1.setProductWeight(product.getProductWeight());
        product1.setProductDescription(product.getProductDescription());
        product1.setProductCode(product.getProductCode());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductTotalQuantityAvailable(product.getProductTotalQuantityAvailable());
        product1.setImageURL(uploadFile(multipartFile));
        product1.setSubCategory(subCategory);

        return productDao.updateProduct(product1);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productDao.getProduct(productId);
        product.setSubCategory(null);
        productDao.deleteProduct(productId);
        orderService.deleteOrderByProductId(product.getProductId());
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {

        // Copy To "./src/main/resources/static/images/"
        String localLocation = String.valueOf(Paths.get("./src/main/resources/static/images/").toAbsolutePath().normalize());
        Files.copy(multipartFile.getInputStream(), Paths.get(localLocation + File.separator + multipartFile
                .getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        // Copy To Target Folder
        String uploadLocation = new ClassPathResource("static/images/").getFile().getAbsolutePath();
        Files.copy(multipartFile.getInputStream(), Paths.get(uploadLocation + File.separator + multipartFile
                .getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(Objects.requireNonNull(
                multipartFile.getOriginalFilename())).toUriString();
        // return  Paths.get(uploadLocation)+File.separator+multipartFile.getOriginalFilename();
    }

    @Override
    public Product decreaseProductQuantity(String productId, int productSelectedQuantity) {
        Product product = productDao.getProduct(productId);
        if (product.getProductTotalQuantityAvailable() != 0 && product.getProductTotalQuantityAvailable() >= productSelectedQuantity) {
            product.setProductTotalQuantityAvailable(product.getProductTotalQuantityAvailable() - productSelectedQuantity);
            return productDao.saveProduct(product);
        } else {
            throw new ProductOutOfStock("Product Out Of Stock");
        }
    }

    @Override
    public Product increaseProductQuantity(String productId, int productQuantity) {
        Product product = productDao.getProduct(productId);
        product.setProductTotalQuantityAvailable(productQuantity);
        return productDao.saveProduct(product);
    }

    @Override
    public Product getByProductName(String productName) {
        return productDao.getByProductName(productName);
    }

    @Override
    public List<Product> filterProductBySubCategory(String subCategoryName) {
        return getAllProduct().stream().filter(product -> product.getSubCategory().getSubCategoryName().equals(subCategoryName)).toList();
    }

    @Override
    public List<Product> filterProductByPrice(int minPrice, int maxPrice) {
        return getAllProduct().stream().filter(product -> product.getProductPrice() >= minPrice && product.getProductPrice() <= maxPrice).toList();
    }

    @Override
    public List<Product> sortProductByNameA_Z() {
        return getAllProduct().stream().sorted(Comparator.comparing(Product::getProductName)).toList();
    }

    @Override
    public List<Product> sortProductByNAmeZ_A() {
        return getAllProduct().stream().sorted(Comparator.comparing(Product::getProductName).reversed()).toList();
    }

    @Override
    public List<Product> filterByProductBelowPrice(int amount) {
        return getAllProduct().stream().filter(product -> product.getProductPrice() <= amount).toList();
    }

    @Override
    public List<Product> filterByProductAbovePrice(int amount) {
        return getAllProduct().stream().filter(product -> product.getProductPrice() >= amount).toList();
    }

    @Override
    public List<Product> sortProductByPriceAscending() {
        return getAllProduct().stream().sorted(Comparator.comparingInt(Product::getProductPrice)).toList();
    }

    @Override
    public List<Product> sortProductByPriceDescending() {
        return getAllProduct().stream().sorted(Comparator.comparingInt(Product::getProductPrice).reversed()).toList();
    }

    @Override
    public List<Product> filterProductByName(String productName) {
        return getAllProduct().stream().filter(prod -> prod.getProductName().equals(productName)).toList();
    }
}
