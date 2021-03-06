package br.com.ms.product.api.exceptionHandler;

import br.com.ms.product.domain.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        MessageError response  = new MessageError();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> message = new ArrayList<>();

        fieldErrors.forEach(e ->{
            Integer statusError = status.value();
            String error = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            String field =  e.getField();

            message.add(field.concat(" " + error));

            response.setStatus(statusError);
            response.setMessage(message);
        });

        return handleExceptionInternal(ex, response, headers, status, request);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request){
        return handleExceptionInternal(ex, "", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
