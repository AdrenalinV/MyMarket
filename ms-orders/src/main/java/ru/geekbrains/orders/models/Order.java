package ru.geekbrains.orders.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "address")
    private String address;

    @Column(name = "cost")
    private double cost;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order(Basket basket, Long userId, String address) {
        this.items = new ArrayList<>();
        this.userId = userId;
        this.address = address;
        this.cost = basket.getCost();
        for (BasketItem bi : basket.getItems()) {
            OrderItem oi = new OrderItem(bi);
            oi.setOrder(this);
            this.items.add(oi);
        }
    }

}
