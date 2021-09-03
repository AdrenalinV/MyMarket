package ru.geekbrains.orders.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "baskets")
@Data
@NoArgsConstructor
public class Basket {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketItem> items;

    @Column(name = "cost")
    private double cost;

    @Column(name = "userId")
    private Long userId;

    public void add(BasketItem item) {
        for (BasketItem bI : this.items) {
            if (bI.getProduct_id() == item.getProduct_id()) {
                bI.incrementQuantity(item.getQuantity());
                recalculate();
                return;
            }
        }
        this.items.add(item);
        item.setBasket(this);
        recalculate();

    }

    public void recalculate() {
        cost = 0;
        for (BasketItem bi : items) {
            cost += bi.getCost();
        }
    }

    public void clear() {
        for (BasketItem bi : items) {
            bi.setBasket(null);
        }
        items.clear();
        recalculate();
    }

    public BasketItem getItemByProductId(Long product_id) {
        for (BasketItem bi : items){
            if(bi.getProduct_id()==product_id){
                return bi;
            }
        }
        return null;
    }

    public void merge(Basket another){
        for(BasketItem bi : another.items){
            add(bi);
        }
    }
}
