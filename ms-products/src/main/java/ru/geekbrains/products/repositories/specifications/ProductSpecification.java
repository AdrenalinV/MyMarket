package ru.geekbrains.products.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.products.entityes.Product;


public class ProductSpecification {
    private static Specification<Product> costGreaterOrEqualsThan(int minCost) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost));
    }

    private static Specification<Product> costLesserThanOrEqualTo(int maxCost) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost));
    }

    private static Specification<Product> titleLike(String titlePart) {

        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey("min_cost") && !params.getFirst("min_cost").isBlank()) {
            spec = spec.and(ProductSpecification.costGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_cost"))));
        }
        if (params.containsKey("max_cost") && !params.getFirst("max_cost").isBlank()) {
            spec = spec.and(ProductSpecification.costLesserThanOrEqualTo(Integer.parseInt(params.getFirst("max_cost"))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(params.getFirst("title")));
        }
        return spec;
    }

}
