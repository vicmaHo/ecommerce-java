package com.vicma.microservices.cart_microservice.cart;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vicma.microservices.cart_microservice.cartItem.CartItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Cart {
    @Id
    private String id;
    private String customerId;
    private List<CartItem> items;
}
