package ru.geekbrains.products.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.products.entityes.Product;
import ru.geekbrains.products.entityes.ProductDto;
import ru.geekbrains.products.repositories.ProductsRepository;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;

    public Optional<Product> findProductById(Long id) {
        return this.productsRepository.findById(id);
    }

    public Page<ProductDto> findProductAll(Specification<Product> spec, int page, int pageSize) {
        if (page < 0) {
            throw new RuntimeException();
        }
        return this.productsRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);

    }

    public void deleteProductById(Long id) {
        this.productsRepository.deleteById(id);
    }

    public Product addOrUpdateProduct(Product p) {
        return this.productsRepository.save(p);
    }

}
