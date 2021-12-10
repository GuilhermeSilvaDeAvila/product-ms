package br.com.ms.product.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class MessageError {
    private Integer status;
    private String message;

    public MessageError(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
