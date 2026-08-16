package com.vicma.microservices.cart_microservice.exceptions;

import java.util.HashMap;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vicma.microservices.common_exceptions.ErrorResponse;
import com.vicma.microservices.common_exceptions.GlobalExceptionHandler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Primary
@Slf4j
public class CartExceptionHandler extends GlobalExceptionHandler {

    // manejar excepciones de Feign
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException exception) {
        var errors = new HashMap<String, String>();
        var fieldName = "cart";
        errors.put(fieldName, exception.getMessage());

        log.warn("Error communicating with other microservices: {}", exception.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ErrorResponse> handleCartException(CartException exception) {
        var errors = new HashMap<String, String>();
        var fieldName = "cart";
        errors.put(fieldName, exception.getMessage());

        log.warn("Cart Exception: {}", exception.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

}
