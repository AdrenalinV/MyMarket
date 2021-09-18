package ru.geekbrains.orders.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.geekbrains.routing.dtos.ProductDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "basket_items")
@Data
@NoArgsConstructor
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cost_per_product")
    private double costPerProduct;

    @Column(name = "cost")
    private double cost;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;


    public BasketItem(ProductDto pDto) {
        this.product_id = pDto.getId();
        this.quantity = 1;
        this.costPerProduct = pDto.getCost();
        this.cost = this.costPerProduct;
    }

    public void incrementQuantity() {
        quantity++;
        cost = quantity * costPerProduct;
    }

    public void incrementQuantity(int amount) {
        quantity += amount;
        cost = quantity * costPerProduct;
    }

    public void decrementQuantity() {
        quantity--;
        cost = quantity * costPerProduct;
    }
}
