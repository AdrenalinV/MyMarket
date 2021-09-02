package ru.geekbrains.routing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BasketItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private double costPerProduct;
    private double cost;
}