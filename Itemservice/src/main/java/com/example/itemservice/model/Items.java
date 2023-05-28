package com.example.itemservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;




import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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
