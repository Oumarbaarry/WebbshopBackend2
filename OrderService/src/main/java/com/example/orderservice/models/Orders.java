package com.example.orderservice.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;




import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class Orders {

    @Id
    @GeneratedValue
    private long id;

    private LocalDate orderDate;
    private long customerId;

    @ElementCollection
    @CollectionTable(name = "orders_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "item_id")
    private List<Long> items;

    public Orders(LocalDate orderDate, long customerId, List<Long> items) {
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.items = items;
    }
}
