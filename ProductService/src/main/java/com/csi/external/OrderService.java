package com.csi.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "OrderService")
public interface OrderService {

    @DeleteMapping("/order/delete-order-by-productId/{productId}")
    void deleteOrderByProductId(@PathVariable String productId);
}