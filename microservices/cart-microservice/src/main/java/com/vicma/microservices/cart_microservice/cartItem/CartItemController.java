package com.vicma.microservices.cart_microservice.cartItem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/{customerId}/cart/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService service;

    @PostMapping
    public ResponseEntity<String> addItemToCart(@PathVariable("customerId") String customerId,
            @Valid @RequestBody CartItemRequest request) {

        return ResponseEntity.ok(service.addItemToCart(customerId, request));
    }

    @PutMapping
    public ResponseEntity<Void> updateQuantity(@PathVariable("customerId") String customerId,
            @Valid @RequestBody CartItemRequest request) {
        service.updateQuantity(customerId, request);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("customerId") String customerId,
            @PathVariable("productId") Integer productId) {
        service.deleteProduct(customerId, productId);
        return ResponseEntity.noContent().build();
    }

}
