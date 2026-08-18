package com.vicma.microservices.cart_microservice.cart;

import org.springframework.stereotype.Service;

import com.vicma.microservices.cart_microservice.exceptions.CartException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartResponse getCartStatus(String customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartException("Cart not found for customer with id " + customerId));

        return cartMapper.toCartResponse(cart);

    }

}
