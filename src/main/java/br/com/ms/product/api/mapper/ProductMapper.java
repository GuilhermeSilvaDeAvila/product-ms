package br.com.ms.product.api.mapper;

import br.com.ms.product.api.dto.ProductDTO;
import br.com.ms.product.api.dto.ProductRequestDTO;
import br.com.ms.product.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO toDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductRequestDTO ProductRequestDTO){
        return modelMapper.map(ProductRequestDTO, Product.class);
    }

    public List<ProductDTO> toCollection(List<Product> products){
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
