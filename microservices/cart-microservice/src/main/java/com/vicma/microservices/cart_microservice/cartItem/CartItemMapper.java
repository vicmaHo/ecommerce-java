package com.vicma.microservices.cart_microservice.cartItem;

import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .productId(cartItem.getProductId())
                .quantity(cartItem.getQuantity())
                .build();
    }
}
