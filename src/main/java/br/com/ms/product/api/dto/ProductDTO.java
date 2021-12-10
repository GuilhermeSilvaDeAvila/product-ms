package br.com.ms.product.api.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    @NotNull
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
