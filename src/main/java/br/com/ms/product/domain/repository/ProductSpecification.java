package br.com.ms.product.domain.repository;

import br.com.ms.product.domain.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> name(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> description(String description) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Product> minPrice(String  minPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }
    public static Specification<Product> maxPrice(String maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> filters(String q, String maxPrice, String minPrice) {
        return Specification.where(
                name(q))
                .or(description(q))
                .or(maxPrice(maxPrice))
                .or(minPrice(minPrice));
    }



}
