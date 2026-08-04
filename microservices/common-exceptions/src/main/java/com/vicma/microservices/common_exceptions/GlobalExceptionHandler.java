package com.vicma.microservices.common_exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Component
@RestControllerAdvice // manejo de excepcione de controladores rest
@Slf4j // nos permite trabajar con logger sin tener que declararlo manualmente
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // captura de excepciones de validacaion @Valid
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.warn("Validation Errors: {}", exception.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

    // excepción base, capturar errores que no hayamos definido
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        var errors = new HashMap<String, String>();
        var fieldName = "message";
        var errorMessage = "An unexpected error occurred, please try again later";
        errors.put(fieldName, errorMessage);
        log.error("Error: {}", exception.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(errors));
    }

}
