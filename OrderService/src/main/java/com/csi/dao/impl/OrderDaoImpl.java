package com.csi.dao.impl;

import com.csi.dao.OrderDao;
import com.csi.exceptions.OrderNotFound;
import com.csi.model.OrderDetails;
import com.csi.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public OrderDetails saveOrder(OrderDetails orderDetails) {
        return orderRepo.save(orderDetails);
    }

    @Override
    public OrderDetails findOrder(String orderId) {
        return orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFound("Order Not Found"));
    }

    @Override
    public void deleteOrder(String orderId) {
        OrderDetails orderDetails = findOrder(orderId);
        orderRepo.delete(orderDetails);
    }

    @Override
    public List<OrderDetails> orderList() {
        return orderRepo.findAll();
    }

    @Override
    public void deleteOrderByProductId(String productId) {
        List<OrderDetails> orderDetailsList = orderRepo.findByProductId(productId);
        if (orderDetailsList != null || !orderDetailsList.isEmpty()) {
            orderDetailsList.forEach(or -> orderRepo.delete(or));
        }
    }
}
