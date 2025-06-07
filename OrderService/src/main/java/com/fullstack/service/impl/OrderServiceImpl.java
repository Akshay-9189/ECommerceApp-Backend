package com.fullstack.service.impl;

import com.fullstack.dao.OrderDao;
import com.fullstack.dto.response.CreateOrderDto;
import com.fullstack.external.ProductService;
import com.fullstack.external.ShoppingCartService;
import com.fullstack.model.OrderDetails;
import com.fullstack.service.OrderService;
import com.fullstack.vo.CartItems;
import com.fullstack.vo.Product;
import com.fullstack.vo.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;


    @Override
    public CreateOrderDto getOrder(String orderId) {
        OrderDetails orderDetails = orderDao.findOrder(orderId);
        Product product = productService.getProduct(orderDetails.getProductId());

        CreateOrderDto createOrderDto = new CreateOrderDto();
        createOrderDto.setOrderDetails(orderDetails);
        createOrderDto.setProduct(product);
        return createOrderDto;
    }

    @Override
    public List<CreateOrderDto> saveOrder(String userId) {
        List<CreateOrderDto> createOrderDtos = new ArrayList<>();

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(userId);
        shoppingCart.getCartItems().stream().map(CartItems::getCartItemsId).forEach(id -> {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setUserId(shoppingCart.getUserId());
            orderDetails.setOrderCreatedAt(LocalDateTime.now());

            CartItems cartItems = shoppingCartService.getCartItems(id);
            orderDetails.setProductQuantity(cartItems.getProductSelectedQuantity());
            orderDetails.setTotalPrice(cartItems.getTotalPrice());

            Product product = productService.getProduct(cartItems.getProductId());
            orderDetails.setProductId(product.getProductId());

            orderDao.saveOrder(orderDetails);

            CreateOrderDto createOrderDto = new CreateOrderDto();
            createOrderDto.setOrderDetails(orderDetails);
            createOrderDto.setProduct(product);

            createOrderDtos.add(createOrderDto);
        });
        return createOrderDtos;
    }

    @Override
    public List<OrderDetails> orderList() {
        return orderDao.orderList().stream().sorted(Comparator.comparing(OrderDetails::getOrderCreatedAt).reversed()).toList();
    }

    @Override
    public List<OrderDetails> reverseOrderList() {
        return orderDao.orderList().stream().sorted(Comparator.comparing(OrderDetails::getOrderCreatedAt)).toList();
    }

    @Override
    public void deleteOrder(String orderId) {
        orderDao.deleteOrder(orderId);
    }

    @Override
    public List<OrderDetails> filterByProductName(String productName) {
        try {
            Product product = productService.getProductByName(productName);
            return orderList().stream().filter(or -> or.getProductId().equals(product.getProductId()))
                    .sorted(Comparator.comparing(OrderDetails::getOrderCreatedAt).reversed()).toList();
        } catch (NullPointerException e) {
            throw new NullPointerException("Product Not Found");
        }
    }

    @Override
    public List<OrderDetails> filterByUserId(String userId) {
        return orderList().stream().filter(or -> or.getUserId().equals(userId))
                .sorted(Comparator.comparing(OrderDetails::getOrderCreatedAt).reversed()).toList();
    }

    @Override
    public List<OrderDetails> filterByCurrentDate() {
        return orderList().stream().filter(data -> data.getOrderCreatedAt().toLocalDate().equals(LocalDate.now()))
                .sorted(Comparator.comparing(OrderDetails::getOrderCreatedAt)).toList();
    }

    @Override
    public void deleteOrderByProductId(String productId) {
        orderDao.deleteOrderByProductId(productId);
    }
}
