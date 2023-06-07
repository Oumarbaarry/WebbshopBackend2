package com.example.orderservice.Implementering;


import com.example.orderservice.models.CreateOrder;
import com.example.orderservice.models.Customer;
import com.example.orderservice.models.Items;
import com.example.orderservice.models.Orders;
import com.example.orderservice.repository.OrderRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepo orderRepository;

    RestTemplate restTemplate = new RestTemplate();

    @Value("${customerservice.url}")
    String customerUrl;

    @Value("${itemservice.url}")
    String itemUrl;

    public OrderServiceImpl(OrderRepo orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    // Hämtar en order baserat på ID
    public ResponseEntity<Object> OrderById(long id) {
        Orders order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Order not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

    // Köper en vara och skapar en order
    public ResponseEntity<Object> buyItem(CreateOrder createOrder) {
        // Hämtar kundinformation från kundtjänsten
        ResponseEntity<Customer> customerResponse = getCustomer(createOrder.getCustomerId());
        if (customerResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Customer not found"), HttpStatus.NOT_FOUND);
        }

        Customer customer = customerResponse.getBody();
        if (customer == null) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Failed to retrieve customer information"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<Long> itemIds = createOrder.getItemIds();
        List<Items> items = new ArrayList<>();
        for (Long itemId : itemIds) {
            // Hämtar varuinformation från varutjänsten
            ResponseEntity<Items> response = restTemplate.getForEntity(itemUrl + "/items/" + itemId, Items.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                items.add(response.getBody());
            }
        }

        // Skapar en ny order
        Orders order = new Orders(createOrder.getOrderDate(), createOrder.getCustomerId(), createOrder.getItemIds());
        orderRepository.save(order);

        // Sätter ihop svar med kundinformation, orderinformation och varuinformation
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("customer", customer);
        responseData.put("order", order);
        responseData.put("items", items);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    // Hämtar kundinformation från kundtjänsten
    private ResponseEntity<Customer> getCustomer(long customerId) {
        try {
            ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(customerUrl + "/customers/" + customerId, Customer.class);
            return responseEntity;
        } catch (Exception ex) {
            logger.warn("Failed to retrieve customer information", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
