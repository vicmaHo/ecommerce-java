package com.vicma.microservices.cart_microservice.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/{customerId}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> getCartStatus(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(cartService.getCartStatus(customerId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCart(@PathVariable("customerId") String customerId) {
        cartService.deleteCart(customerId);
        return ResponseEntity.noContent().build();
    }
}
