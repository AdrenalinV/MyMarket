package ru.geekbrains.products.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.core.configurations.BeanConfiguration;
import ru.geekbrains.products.entityes.Product;
import ru.geekbrains.products.repositories.ProductsRepository;
import ru.geekbrains.products.services.ProductService;
import ru.geekbrains.routing.dtos.ProductDto;

import java.util.Optional;

@SpringBootTest(classes = {BeanConfiguration.class, ProductService.class})
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductsRepository productsRepository;

    @Test
    public void getProduct() {
        Product demoProduct = new Product();
        demoProduct.setTitle("Product_demo");
        demoProduct.setCost(999.99);
        demoProduct.setId(6L);

        Mockito.doReturn(Optional.of(demoProduct))
                .when(productsRepository)
                .findById(6L);

        ProductDto pDto = productService.findProductById(6L).get();
        Assertions.assertEquals("Product_demo",pDto.getTitle());
    }

}
