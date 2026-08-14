package com.vicma.microservices.cart_microservice.cartItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private Integer productId;
    private Integer quantity;
}
