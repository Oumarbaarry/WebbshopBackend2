package com.example.customerservice.gränssnitt;

import com.example.customerservice.model.Customer;

public interface CustomerService {
    Customer getCustomerById(Long customerId);
    Customer createCustomer(Customer customer);
}
