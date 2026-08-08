package com.vicma.microservices.product_microservice.category;

import java.util.List;

import com.vicma.microservices.product_microservice.product.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String name;
    private String description;
    private List<ProductResponse> products;
}
