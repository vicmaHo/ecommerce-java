package com.vicma.microservices.cart_microservice.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/{customerId}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Get cart status", description = "Returns the information of the customer's cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart information returned"),
            @ApiResponse(responseCode = "404", description = "Cart not found")
    })
    @GetMapping
    public ResponseEntity<CartResponse> getCartStatus(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(cartService.getCartStatus(customerId));
    }

    @Operation(summary = "Delete cart", description = "Deletes the customer's cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cart deleted"),
            @ApiResponse(responseCode = "404", description = "Cart not found")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteCart(@PathVariable("customerId") String customerId) {
        cartService.deleteCart(customerId);
        return ResponseEntity.noContent().build();
    }
}
