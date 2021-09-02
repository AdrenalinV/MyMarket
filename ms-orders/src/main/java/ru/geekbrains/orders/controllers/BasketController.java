package ru.geekbrains.orders.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.core.interfaces.ITokenService;
import ru.geekbrains.core.models.UserInfo;
import ru.geekbrains.orders.services.BasketService;
import ru.geekbrains.routing.dtos.BasketDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    private final ITokenService tokenService;

    @PostMapping
    public UUID createNewBasket(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        if (token == null) {
            return basketService.getBasketForUser(null, null);
        }
        UserInfo userInfo = tokenService.parseToken(token);
        return basketService.getBasketForUser(userInfo.getUserId(), null);
    }

    @GetMapping("/{uuid}")
    public BasketDto getCurrentBasket(@PathVariable UUID uuid) {
        return basketService.findById(uuid);
    }

    @PostMapping("/add")
    public void addProductToBasket(@RequestParam UUID uuid, @RequestParam(name = "product_id") Long productId) {
        basketService.addToBasket(uuid, productId);
    }

    @PostMapping("/clear")
    public void clearBasket(@RequestParam UUID uuid) {
        basketService.clearBasket(uuid);
    }
}
