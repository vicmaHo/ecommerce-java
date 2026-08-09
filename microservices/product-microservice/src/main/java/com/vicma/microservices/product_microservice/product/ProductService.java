package com.vicma.microservices.product_microservice.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vicma.microservices.product_microservice.category.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRespository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public ProductResponse getProductById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow();
    }

    public void createProduct(ProductRequest request) {
        var category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow();
        var product = mapper.toProduct(request, category);

        repository.save(product);
    }

    public void updateProduct(Integer productId, ProductRequest request) {
        var product = repository.findById(productId)
                .orElseThrow();

        var category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);
        repository.save(product);
    }

    public void deleteProduct(Integer productId) {
        repository.findById(productId)
                .orElseThrow();
        repository.deleteById(productId);
    }

}
