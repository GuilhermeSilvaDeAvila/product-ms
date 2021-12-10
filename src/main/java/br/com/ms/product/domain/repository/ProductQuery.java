package br.com.ms.product.domain.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;


import br.com.ms.product.domain.model.Product;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

@Getter
public class ProductQuery {

    private String q;
    private String minPrice;
    private String maxPrice;

    //CheckOff
    public ProductQuery(String q, String minPrice, String maxPrice) {
        this.q = q;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Specification<Product> toSpec() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.q != null) {
                Predicate criteriaName = builder.like(root.get("name"), "%" + this.q + "%");
                Predicate criteriaDescription = builder.like(root.get("description"), "%" + this.q + "%");
                Predicate criteriaNameOrDescription = builder.or(criteriaName,criteriaDescription);
                predicates.add(criteriaNameOrDescription);
            }

            if (this.minPrice != null) {
                Predicate criteriaMinPrice = builder.greaterThanOrEqualTo(root.get("price"), this.minPrice);
                predicates.add(criteriaMinPrice);
            }

            if (this.maxPrice != null) {
                Predicate criteriaMaxPrice = builder.lessThanOrEqualTo(root.get("price"), this.maxPrice);
                predicates.add(criteriaMaxPrice);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
