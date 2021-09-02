package ru.geekbrains.routing.dtos;

import lombok.Data;

@Data
public class OrderItemDto {

    private String productTitle;

    private int quantity;

    private double costPerProduct;

    private double cost;

}
