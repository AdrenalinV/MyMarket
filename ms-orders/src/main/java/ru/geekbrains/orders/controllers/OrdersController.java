package ru.geekbrains.orders.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.core.interfaces.ITokenService;
import ru.geekbrains.core.models.UserInfo;
import ru.geekbrains.orders.services.BasketService;
import ru.geekbrains.orders.services.OrderService;
import ru.geekbrains.routing.dtos.OrderDto;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    private final BasketService basketService;

    private final ITokenService tokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam UUID basketUuid, @RequestParam String address) {
        UserInfo userInfo = tokenService.parseToken(token);
        OrderDto orderDto = orderService.createFromUserBasket(userInfo.getUserId(), basketUuid, address);
        basketService.clearBasket(basketUuid);
        return orderDto;
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        UserInfo userInfo = tokenService.parseToken(token);
        return orderService.findAllOrdersByUserId(userInfo.getUserId());
    }

}
