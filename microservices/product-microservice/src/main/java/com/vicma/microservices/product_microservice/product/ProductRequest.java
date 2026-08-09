package com.vicma.microservices.product_microservice.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequest {

    @NotNull(message = "Product name is required")
    private String name;

    @NotNull(message = "Product description is required")
    private String description;

    @NotNull(message = "Product price is required")
    private Double price;

    @NotNull(message = "Product stock is required")
    private Integer stock;

    @NotNull(message = "Product image URL is required")
    private String imageUrl;

    @NotNull(message = "Category ID is required")
    private Integer categoryId;
}