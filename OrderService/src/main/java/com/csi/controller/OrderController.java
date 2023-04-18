package com.csi.controller;

import com.csi.dto.response.CreateOrderDto;
import com.csi.model.OrderDetails;
import com.csi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get-order/{orderId}")
    public ResponseEntity<CreateOrderDto> getOrderById(@PathVariable String orderId) {
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @PostMapping("/create-order/{userId}")
    public ResponseEntity<List<CreateOrderDto>> saveOrder(@PathVariable String userId) {
        return new ResponseEntity<>(orderService.saveOrder(userId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDetails>> getAllOrders() {
        return new ResponseEntity<>(orderService.orderList(), HttpStatus.OK);
    }

    @GetMapping("/reverse")
    public ResponseEntity<List<OrderDetails>> getReverseList() {
        return new ResponseEntity<>(orderService.reverseOrderList(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/filter-by-product-name/{productName}")
    public ResponseEntity<List<OrderDetails>> getFilterByProductName(@PathVariable String productName) {
        return new ResponseEntity<>(orderService.filterByProductName(productName), HttpStatus.OK);
    }

    @GetMapping("/filter-by-user-id/{userId}")
    public ResponseEntity<List<OrderDetails>> getFilterBByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(orderService.filterByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/filter-by-current-date")
    public ResponseEntity<List<OrderDetails>> filterByCurrentData() {
        return new ResponseEntity<>(orderService.filterByCurrentDate(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-order-by-productId/{productId}")
    public ResponseEntity<String> deleteOrderByProductId(@PathVariable String productId) {
        orderService.deleteOrderByProductId(productId);
        return new ResponseEntity<>("Order Deleted Successfully", HttpStatus.OK);
    }
}