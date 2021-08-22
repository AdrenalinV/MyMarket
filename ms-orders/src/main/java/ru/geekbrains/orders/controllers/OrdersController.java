package ru.geekbrains.orders.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
//    private final ProductsClient productClient;


//    @GetMapping("/{id}")
//    public ProductDto getProductDTO(@PathVariable Long id){
//        return productClient.getProductById(id);
//    }

}
