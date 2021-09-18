package ru.geekbrains.routing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BasketDto {
    private List<BasketItemDto> items;
    private double totalCost;
}
