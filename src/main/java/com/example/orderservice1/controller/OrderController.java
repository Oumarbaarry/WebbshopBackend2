package com.example.orderservice1.controller;


import com.example.orderservice1.Implementering.OrderServiceImpl;
import com.example.orderservice1.models.CreateOrder;
import com.example.orderservice1.models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable long id) {
        return orderService.OrderById(id);
    }

    @GetMapping("/items")
    public ResponseEntity<Object> getAllOrdersWithItems() {
        return orderService.getAllOrdersWithItems();
    }

    @PostMapping("/buy")
    public ResponseEntity<Object> buyItem(@RequestBody CreateOrder createOrder) {
        return orderService.buyItem(createOrder);
    }
}
