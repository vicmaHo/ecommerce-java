package com.vicma.microservices.cart_microservice.cartItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {

    @NotNull(message = "Product ID is required")
    private Integer productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    @NotNull(message = "Quantity is required")
    private Integer quantity;

}
