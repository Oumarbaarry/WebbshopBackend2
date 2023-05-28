package com.example.customerservice.gränssnitt;

import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepo;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepo.findById(customerId).orElse(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }
}
