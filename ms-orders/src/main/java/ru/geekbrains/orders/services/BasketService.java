package ru.geekbrains.orders.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.geekbrains.orders.models.Basket;
import ru.geekbrains.orders.models.BasketItem;
import ru.geekbrains.orders.repositories.BasketRepository;
import ru.geekbrains.routing.dtos.BasketDto;
import ru.geekbrains.routing.dtos.ProductDto;
import ru.geekbrains.routing.interfeces.ProductsClient;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;

    private final ProductsClient productsClient;

    private final ModelMapper modelMapper;

    public Basket save(Basket basket) {
        return basketRepository.save(basket);
    }

    public BasketDto findById(UUID id) {
        return modelMapper.map(basketRepository.findById(id).get(), BasketDto.class);
    }

    public Optional<Basket> findByUserId(Long userId) {
        return basketRepository.findByUserId(userId);
    }

    @Transactional
    public void addToBasket(UUID basketId, Long productId) {
        BasketDto basketDto = findById(basketId);
        Basket basket = modelMapper.map(basketDto, Basket.class);
        BasketItem basketItem = basket.getItemByProductId(productId);
        if (basketItem != null) {
            basketItem.incrementQuantity();
            basket.recalculate();
            return;
        }
        ProductDto productDto = productsClient.getProductById(productId);
        basket.add(new BasketItem(productDto));
    }

    @Transactional
    public void clearBasket(UUID basketId) {
        BasketDto basketDto = findById(basketId);
        Basket basket = modelMapper.map(basketDto, Basket.class);
        basket.clear();
    }

    @Transactional
    public UUID getBasketForUser(Long userId, UUID basketUUID) {
        if (userId != null && basketUUID != null) {
            BasketDto basketDto = findById(basketUUID);
            Basket basket = modelMapper.map(basketDto, Basket.class);
            Optional<Basket> oldBasket = findByUserId(userId);
            if (oldBasket.isPresent()) {
                basket.merge(oldBasket.get());
                basketRepository.delete(oldBasket.get());
            }
            basket.setUserId(userId);
        }
        if (userId == null) {
            Basket basket = save(new Basket());
            return basket.getId();
        }
        Optional<Basket> basket = findByUserId(userId);
        if (basket.isPresent()) {
            return basket.get().getId();
        }
        Basket newBasket = new Basket();
        newBasket.setUserId(userId);
        save(newBasket);
        return newBasket.getId();
    }

}
