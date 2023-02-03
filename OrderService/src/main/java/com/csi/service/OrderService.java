package com.csi.service;

import com.csi.dto.response.CreateOrderDto;
import com.csi.model.OrderDetails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderService {

    @Cacheable(value = "orderId")
    CreateOrderDto getOrder(String orderId);

    List<CreateOrderDto> saveOrder(String shoppingCartId);

    List<OrderDetails> orderList();

    List<OrderDetails> reverseOrderList();

    void deleteOrder(String orderId);

    List<OrderDetails> filterByProductName(@PathVariable String productName);

    List<OrderDetails> filterByUserId(@PathVariable String userId);

    List<OrderDetails> filterByCurrentDate();

    void deleteOrderByProductId(String productId);
}
