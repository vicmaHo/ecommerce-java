package com.vicma.microservices.cart_microservice.cartItem;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

    public String addItemToCart(String customerId, CartItemRequest request) {
        return "Item added to cart";
    }

}
