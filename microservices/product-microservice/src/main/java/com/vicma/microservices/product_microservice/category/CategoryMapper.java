package com.vicma.microservices.product_microservice.category;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vicma.microservices.product_microservice.product.ProductMapper;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .products(category.getProductos().stream().map(ProductMapper::toProductResponse)
                        .collect(Collectors.toList()))
                .build();
    }
}
