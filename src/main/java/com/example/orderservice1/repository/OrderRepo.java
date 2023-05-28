package com.example.orderservice1.repository;

import com.example.orderservice1.models.CreateOrder;
import com.example.orderservice1.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface OrderRepo extends JpaRepository<Orders, Long> {
    //ResponseEntity<Object> buyItem(CreateOrder createOrder);
}
