package ru.geekbrains.products.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.products.entityes.Product;
import ru.geekbrains.products.entityes.ProductDto;
import ru.geekbrains.products.repositories.specifications.ProductSpecification;
import ru.geekbrains.products.services.ProductService;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductDto getProductById(@PathVariable Long id) {
        ProductDto p = new ProductDto(this.productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id:" + id + " doesn't exist")));
        return p;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        this.productService.deleteProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return this.productService.addOrUpdateProduct(product);
    }

    @PutMapping
    public Product UpdateProduct(@RequestBody Product product) {
        return this.productService.addOrUpdateProduct(product);
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return this.productService.findProductAll(ProductSpecification.build(params), page, 2);
    }
}
