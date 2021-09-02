package ru.geekbrains.routing.interfeces;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.routing.dtos.ProductDto;

import java.util.List;


@FeignClient("ms-products")
public interface ProductsClient {

    @GetMapping("/api/v1/products/{id}")
    ProductDto getProductById(@PathVariable Long id);

    @GetMapping("/api/v1/products/ids")
    List<ProductDto> getProductDtos(@RequestParam List<Long> ids);

}
