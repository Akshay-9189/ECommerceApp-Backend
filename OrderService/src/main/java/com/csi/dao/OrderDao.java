package com.csi.dao;

import com.csi.model.OrderDetails;

import java.util.List;

public interface OrderDao {

    OrderDetails saveOrder(OrderDetails orderDetails);

    OrderDetails findOrder(String orderId);

    void deleteOrder(String orderId);

    List<OrderDetails> orderList();

    void deleteOrderByProductId(String productId);
}
