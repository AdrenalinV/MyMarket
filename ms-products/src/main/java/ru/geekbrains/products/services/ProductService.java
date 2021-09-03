package ru.geekbrains.products.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.products.entityes.Product;
import ru.geekbrains.products.repositories.ProductsRepository;
import ru.geekbrains.routing.dtos.ProductDto;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;
    private final ModelMapper modelMapper;

    public Optional<ProductDto> findProductById(Long id) {
        return this.productsRepository.findById(id).map(this::toDto);
    }

    public Page<ProductDto> findProductAll(Specification<Product> spec, int page, int pageSize) {
        if (page < 0) {
            throw new RuntimeException();
        }
        return this.productsRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(this::toDto);

    }

    public List<ProductDto> findProductDtosByIds(List<Long> ids) {
        return productsRepository.findByIdIn(ids).stream().map(this::toDto).collect(Collectors.toList());
    }

    public void deleteProductById(Long id) {
        this.productsRepository.deleteById(id);
    }

    public Product addOrUpdateProduct(Product p) {
        return this.productsRepository.save(p);
    }

    private ProductDto toDto(Product p) {
        return modelMapper.map(p, ProductDto.class);
    }

    private Product toEntity(ProductDto pDto) {
        return modelMapper.map(pDto, Product.class);
    }

}
