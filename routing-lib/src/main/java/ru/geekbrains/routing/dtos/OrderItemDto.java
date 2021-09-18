package ru.geekbrains.routing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private String productTitle;

    private int quantity;

    private double costPerProduct;

    private double cost;

}
