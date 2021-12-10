package br.com.ms.product.domain.service;

import br.com.ms.product.domain.exception.BusinessException;
import br.com.ms.product.domain.model.Product;
import br.com.ms.product.domain.repository.ProductQuery;
import br.com.ms.product.domain.repository.ProductRepository;
import br.com.ms.product.domain.repository.ProductSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    ProductRepository productRepository;
    @Transactional
    public void create(Product product) {
        BigDecimal formattedPrice = product.getPrice().setScale(2, RoundingMode.HALF_UP);
        product.setPrice(formattedPrice);
        productRepository.save(product);
    }

    @Transactional
    public Optional<Product> findById(String productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public List<Product>  get(ProductQuery productQuery) {
        return productRepository.findAll(productQuery.toSpec());
    }

    public List<Product> get(String q, String minPrice, String maxPrice) {
        ProductQuery productQuery = new ProductQuery(q, minPrice, maxPrice);

        return get(productQuery);
    }
}
