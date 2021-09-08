package ru.geekbrains.products.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.geekbrains.products.entityes.Product;
import ru.geekbrains.products.repositories.ProductsRepository;

import java.util.List;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void initDBTest() {
        List<Product> products = productsRepository.findAll();
        Assertions.assertEquals(5, (products.size()));
    }

}
