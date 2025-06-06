package com.fullstack.external;

import com.fullstack.vo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ProductService")
public interface ProductService {

    @GetMapping("/product/get/{productId}")
    Product getProduct(@PathVariable String productId);
}
