package com.vicma.microservices.cart_microservice.cartItem;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vicma.microservices.cart_microservice.cart.Cart;

public interface CartItemRepository extends MongoRepository<CartItem, String> {

    Optional<Cart> findByCustomerId(String customerId);

}
