package com.vicma.microservices.product_microservice.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vicma.microservices.product_microservice.category.CategoryRepository;
import com.vicma.microservices.product_microservice.exceptions.NotEnoughStockException;
import com.vicma.microservices.product_microservice.exceptions.ProductNotFoundException;

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
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with ID %d not found", productId)));
    }

    public void createProduct(ProductRequest request) {
        var category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow();
        var product = mapper.toProduct(request, category);

        repository.save(product);
    }

    public void updateProduct(Integer productId, ProductRequest request) {
        var product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with ID %d not found", productId)));

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
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with ID %d not found", productId)));
        repository.deleteById(productId);
    }

    @Transactional
    public void purchaseProduct(List<ProductQuantityRequest> request) {

        for (ProductQuantityRequest item : request) {

            Product product = repository.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(
                            String.format("Product with ID %d not found", item.getProductId())));

            if (product.getStock() < item.getQuantity()) {
                throw new NotEnoughStockException(
                        item.getProductId(),
                        item.getQuantity(),
                        product.getStock());
            }

            product.setStock(product.getStock() - item.getQuantity());
            repository.save(product);
        }
    }

    public void restockProduct(List<ProductQuantityRequest> request) {
        for (ProductQuantityRequest item : request) {

            Product product = repository.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(
                            String.format("Product with ID %d not found", item.getProductId())));

            product.setStock(product.getStock() + item.getQuantity());
            repository.save(product);
        }
    }

}
