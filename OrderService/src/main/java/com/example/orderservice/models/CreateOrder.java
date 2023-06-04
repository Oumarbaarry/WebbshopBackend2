package com.example.orderservice.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateOrder {

    private long customerId;
    private List<Long> itemIds;
    private LocalDate orderDate;


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
