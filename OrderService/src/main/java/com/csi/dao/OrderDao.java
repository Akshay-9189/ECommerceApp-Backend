package com.csi.dao;

import com.csi.model.OrderDetails;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface OrderDao {
    @CacheEvict(value = "OrderList", allEntries = true)
    OrderDetails saveOrder(OrderDetails orderDetails);

    @Cacheable(value = "OrderId")
    OrderDetails findOrder(String orderId);

    @CacheEvict(value = {"OrderList", "OrderId"}, allEntries = true)
    void deleteOrder(String orderId);

    @Cacheable(value = "OrderList")
    List<OrderDetails> orderList();

    @CacheEvict(value = {"OrderList", "OrderId"}, allEntries = true)
    void deleteOrderByProductId(String productId);
}