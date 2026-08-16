package com.vicma.microservices.cart_microservice.cartItem;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.vicma.microservices.cart_microservice.cart.Cart;
import com.vicma.microservices.cart_microservice.cart.CartRepository;
import com.vicma.microservices.cart_microservice.customer.CustomerClient;
import com.vicma.microservices.cart_microservice.customer.CustomerResponse;
import com.vicma.microservices.cart_microservice.exceptions.CartException;
import com.vicma.microservices.cart_microservice.product.ProductClient;
import com.vicma.microservices.cart_microservice.product.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartRepository cartRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public String addItemToCart(String customerId, CartItemRequest cartItemRequest) {

        // verifico que el cliente existe
        CustomerResponse customerResponse = customerClient.getCustomerById(customerId)
                .orElseThrow(() -> new CartException("Customer not found"));

        // verifico que el producto existe
        ProductResponse productResponse = productClient.getProductById(cartItemRequest.getProductId())
                .orElseThrow(() -> new CartException("Product not found"));

        // verifico que hay suficiente stock
        if (productResponse.getStock() < cartItemRequest.getQuantity()) {
            throw new CartException("Not enought stock");
        }

        // obten go el carrito del cliente, si no existe creo uno nuevo
        Cart cart = cartRepository.findByCustomerId(customerResponse.getId())
                .orElse(Cart.builder()
                        .customerId(customerResponse.getId())
                        .items(new ArrayList<>())
                        .build());

        // verifico que el producto no existe en el carrito
        boolean productExist = cart.getItems().stream()
                .anyMatch(item -> item.getProductId().equals(cartItemRequest.getProductId()));

        if (productExist) {
            throw new CartException("The product already exists in the cart");
        }

        cart.getItems()
                .add(CartItem.builder()
                        .productId(cartItemRequest.getProductId())
                        .quantity(cartItemRequest.getQuantity())
                        .build());
        cartRepository.save(cart);
        return cart.getId();
    }

}
