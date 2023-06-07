package com.example.orderservice.models;



import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    protected long id;
    protected String name;
    protected long personnummer;

    public Customer(String name, long personnummer) {
        this.name = name;
        this.personnummer = personnummer;
    }
}
