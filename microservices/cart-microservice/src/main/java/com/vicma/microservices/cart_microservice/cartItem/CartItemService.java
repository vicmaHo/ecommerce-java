package com.vicma.microservices.cart_microservice.cartItem;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.vicma.microservices.cart_microservice.cart.Cart;
import com.vicma.microservices.cart_microservice.customer.CustomerClient;
import com.vicma.microservices.cart_microservice.customer.CustomerResponse;
import com.vicma.microservices.cart_microservice.product.ProductClient;
import com.vicma.microservices.cart_microservice.product.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public String addItemToCart(String customerId, CartItemRequest cartItemRequest) {
        CustomerResponse customerResponse = customerClient.getCustomerById(customerId)
                .orElseThrow();

        ProductResponse productResponse = productClient.getProductById(cartItemRequest.getProductId())
                .orElseThrow();

        if (productResponse.getStock() < cartItemRequest.getQuantity()) {
            throw new IllegalArgumentException("Not enought stock");
        }

        Cart cart = repository.findByCustomerId(customerResponse.getId())
                .orElse(Cart.builder()
                        .customerId(customerResponse.getId())
                        .items(new ArrayList<>())
                        .build());

        // TODO: completar logica
        return null;
    }

}
