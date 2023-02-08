package com.csi.controller;

import com.csi.dto.product.SaveProductDTO;
import com.csi.exception.FileFormatNotMatch;
import com.csi.model.Product;
import com.csi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getAllProduct() {
        List<Product> allProduct = productService.getAllProduct();
        if (!(allProduct == null)) {
            return new ResponseEntity<>(allProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Products Not Found !!!!!!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable String productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveProduct(@RequestPart("product") SaveProductDTO product, @RequestPart("file") MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty() || !multipartFile.getContentType().equals("image/jpeg")) {
            throw new FileFormatNotMatch("The Image Content Type Must Be .jpeg ");
        } else {
            return new ResponseEntity<>(productService.saveProduct(product, multipartFile), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/update/{productId}", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateProduct(@PathVariable String productId, @RequestPart("product") SaveProductDTO product,
                                           @RequestPart("file") MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty() || !multipartFile.getContentType().equals("image/jpeg")) {
            throw new FileFormatNotMatch("The Image Content Type Must Be .jpeg ");
        } else {
            return new ResponseEntity<>(productService.updateProduct(productId, product, multipartFile), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product" + productId + "Deleted Successfully", HttpStatus.OK);
    }

    @PatchMapping("/decrease-product-quantity/{productId}/{productSelectedQuantity}")
    public ResponseEntity<?> decreaseQuantity(@PathVariable String productId, @PathVariable int productSelectedQuantity) {
        return new ResponseEntity<>(productService.decreaseProductQuantity(productId, productSelectedQuantity), HttpStatus.PARTIAL_CONTENT);
    }

    @PatchMapping("/increase-product-quantity/{productId}/{productQuantity}")
    public ResponseEntity<?> increaseQuantity(@PathVariable String productId, @PathVariable int productQuantity) {
        return new ResponseEntity<>(productService.increaseProductQuantity(productId, productQuantity), HttpStatus.PARTIAL_CONTENT);
    }

    @GetMapping("/get-by-name/{productName}")
    public ResponseEntity<?> getProductByName(@PathVariable String productName) {
        return new ResponseEntity<>(productService.getByProductName(productName), HttpStatus.OK);
    }

    @GetMapping("/filter-product-by-sub-category-name/{subCategoryName}")
    public ResponseEntity<?> filterProductBySubCategoryName(@PathVariable String subCategoryName) {
        return new ResponseEntity<>(productService.filterProductBySubCategory(subCategoryName), HttpStatus.OK);
    }

    @GetMapping("/filter-product-by-price/{minPrice}/{maxPrice}")
    public ResponseEntity<?> filterProductBySubCategoryName(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return new ResponseEntity<>(productService.filterProductByPrice(minPrice, maxPrice), HttpStatus.OK);
    }

    @GetMapping("/sort-product-by-name")
    public ResponseEntity<?> sortProductByNameAs() {
        return new ResponseEntity<>(productService.sortProductByNameA_Z(), HttpStatus.OK);
    }

    @GetMapping("/sort-product-by-name-descending")
    public ResponseEntity<?> sortProductByNameDe() {
        return new ResponseEntity<>(productService.sortProductByNAmeZ_A(), HttpStatus.OK);
    }

    @GetMapping("/filter-product-by-below-price/{price}")
    public ResponseEntity<?> filterProductByBelowPrice(@PathVariable int price) {
        return new ResponseEntity<>(productService.filterByProductBelowPrice(price), HttpStatus.OK);
    }

    @GetMapping("/filter-product-by-above-price/{price}")
    public ResponseEntity<?> filterProductByAbovePrice(@PathVariable int price) {
        return new ResponseEntity<>(productService.filterByProductAbovePrice(price), HttpStatus.OK);
    }

    @GetMapping("/sort-by-price-ascending")
    public ResponseEntity<?> sortByproductPriceAscending() {
        return new ResponseEntity<>(productService.sortProductByPriceAscending(), HttpStatus.OK);
    }

    @GetMapping("/sort-by-price-descending")
    public ResponseEntity<?> sortByProductPriceDescending() {
        return new ResponseEntity<>(productService.sortProductByPriceDescending(), HttpStatus.OK);
    }

    @GetMapping("/filter-product-by-name/{productName}")
    public ResponseEntity<?> filterProductByName(@PathVariable String productName) {
        return new ResponseEntity<>(productService.filterProductByName(productName), HttpStatus.OK);
    }
}