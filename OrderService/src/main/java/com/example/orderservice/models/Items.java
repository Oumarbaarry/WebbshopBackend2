package com.example.orderservice.models;


import jakarta.persistence.*;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Items {

    @Id
    @GeneratedValue
    protected long id;
    protected String name;
    protected long pris;

    public Items(String name, long pris) {
        this.name = name;
        this.pris = pris;
    }
}
