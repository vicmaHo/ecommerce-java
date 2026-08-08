package com.vicma.microservices.product_microservice.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
