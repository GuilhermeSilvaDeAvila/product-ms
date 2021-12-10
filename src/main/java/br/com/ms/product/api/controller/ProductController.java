package br.com.ms.product.api.controller;

import br.com.ms.product.api.dto.ProductDTO;
import br.com.ms.product.api.dto.ProductRequestDTO;
import br.com.ms.product.domain.model.Product;
import br.com.ms.product.domain.repository.ProductRepository;
import br.com.ms.product.domain.service.ProductService;
import br.com.ms.product.api.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    ProductRepository productRepository;
    ProductService productService;
    ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequest){
        Product product = productMapper.toEntity(productRequest);
        productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDTO(product));
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productMapper.toCollection(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return productService.findById(id)
                .map(product -> ResponseEntity.ok(productMapper.toDTO(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
         if(productService.findById(id).isEmpty()){
             return ResponseEntity.notFound().build();
         }
         productService.deleteProduct(id);

         return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateClient(@Valid @RequestBody ProductRequestDTO productRequestDTO, @PathVariable String id){
        if(productService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Product product = productMapper.toEntity(productRequestDTO);

        product.setId(id);
        productService.create(product);

        return ResponseEntity.ok().body(productMapper.toDTO(product));
    }

    @GetMapping("/search")
    public List<ProductDTO> getProductFilter(@RequestParam(required = false) String q,
                                             @RequestParam(required = false) String min_price,
                                             @RequestParam(required = false) String max_price){

        List<Product> products = productService.get(
                q, min_price, max_price);

        return productMapper.toCollection(products);
    }
}
