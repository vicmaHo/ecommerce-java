package com.vicma.microservices.cart_microservice.cart;

import java.util.List;

import com.vicma.microservices.cart_microservice.cartItem.CartItemResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private String id;
    private String customerId;
    private List<CartItemResponse> items;
}
