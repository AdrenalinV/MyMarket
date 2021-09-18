package ru.geekbrains.orders.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.core.models.UserInfo;
import ru.geekbrains.orders.services.BasketService;
import ru.geekbrains.routing.dtos.BasketDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @PostMapping
    public UUID createNewBasket() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            return basketService.getBasketForUser(null, null);
        }
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return basketService.getBasketForUser(userInfo.getUserId(), null);
    }

    @GetMapping("/{uuid}")
    public BasketDto getCurrentBasket(@PathVariable String uuid) {
        return basketService.findById(UUID.fromString(uuid));
    }

    @PostMapping("/add")
    public void addProductToBasket(@RequestParam String uuid, @RequestParam(name = "product_id") Long productId) {
        basketService.addToBasket(UUID.fromString(uuid), productId);
    }

    @PostMapping("/clear")
    public void clearBasket(@RequestParam String uuid) {
        basketService.clearBasket(UUID.fromString(uuid));
    }
}
