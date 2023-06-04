package com.example.orderservice.repository;

import com.example.orderservice.models.CreateOrder;
import com.example.orderservice.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface OrderRepo extends JpaRepository<Orders, Long> {
    //ResponseEntity<Object> buyItem(CreateOrder createOrder);
}
