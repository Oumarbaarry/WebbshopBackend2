package com.example.customerservice.controllers;



import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class CustomerController {
    private final CustomerRepo customerRepo;


    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;

    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepo.save(customer);
        return ResponseEntity.created(URI.create("/customers/" + savedCustomer.getId())).body(savedCustomer);
    }
}
