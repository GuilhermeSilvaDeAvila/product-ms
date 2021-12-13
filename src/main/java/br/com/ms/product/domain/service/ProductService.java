package br.com.ms.product.domain.service;

import br.com.ms.product.domain.exceptions.EntityNotFoundException;
import br.com.ms.product.domain.model.Product;
import br.com.ms.product.domain.repository.ProductQuery;
import br.com.ms.product.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Optional<Product> findById(String productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getFilter(String q, String minPrice, String maxPrice) {
        ProductQuery productQuery = new ProductQuery(q, minPrice, maxPrice);
        return productRepository.findAll(productQuery.toSpec());
    }

    public void productExist(String id) {
        if(productRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException();
        }else{
            throw new EntityNotFoundException();
        }
    }
}
