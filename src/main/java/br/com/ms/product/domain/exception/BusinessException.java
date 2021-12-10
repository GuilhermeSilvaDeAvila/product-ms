package br.com.ms.product.domain.exception;

public class BusinessException extends RuntimeException{
    private static final long serialverisonUID = 1L;

    public BusinessException(String message){
        super(message);
    }
}
