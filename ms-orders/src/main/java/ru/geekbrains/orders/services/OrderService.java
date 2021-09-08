package ru.geekbrains.orders.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.geekbrains.orders.models.Basket;
import ru.geekbrains.orders.models.Order;
import ru.geekbrains.orders.repositories.OrderRepository;
import ru.geekbrains.routing.dtos.BasketDto;
import ru.geekbrains.routing.dtos.OrderDto;
import ru.geekbrains.routing.dtos.ProductDto;
import ru.geekbrains.routing.interfeces.ProductsClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final BasketService basketService;

    private final ProductsClient productsClient;

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createFromUserBasket(Long userId, UUID cartUuid, String address) {
        BasketDto basketDto = basketService.findById(cartUuid);
        Basket basket = modelMapper.map(basketDto, Basket.class);
        Order order = new Order(basket, userId, address);
        order = orderRepository.save(order);
        return toDto(order);
    }

    public OrderDto findOrderById(Long id) {
        Order order = orderRepository.findById(id).get();
        List<Long> productIds = new ArrayList<>();
        order.getItems().forEach(item -> productIds.add(item.getProductId()));
        List<ProductDto> products = productsClient.getProductDtos(productIds);
        OrderDto orderDto = toDto(order);
        orderDto.setProducts(products);
        return orderDto;
    }

    public List<OrderDto> findAllOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private OrderDto toDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    private Order toEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}
