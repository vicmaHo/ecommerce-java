package com.vicma.microservices.cart_microservice.cart;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vicma.microservices.cart_microservice.cartItem.CartItemMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartResponse toCartResponse(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .customerId(cart.getCustomerId())
                .items(cart.getItems().stream().map(cartItemMapper::toCartItemResponse)
                        .collect(Collectors.toList()))
                .build();
    }
}
