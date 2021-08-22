package ru.geekbrains.routing.interfeces;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.routing.interfeces.entityes.ProductDto;


@FeignClient("ms-products")
public interface ProductsClient {

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable Long id);
}
