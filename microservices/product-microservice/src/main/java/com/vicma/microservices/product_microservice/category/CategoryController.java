package com.vicma.microservices.product_microservice.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(service.getCategoryById(categoryId));
    }

    @PostMapping
    public ResponseEntity<Integer> createCategory(@Valid @RequestBody CategoryRequest request) {
        Integer id = service.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryRequest request) {
        service.updateCategory(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        service.deleteCategory(categoryId);
        return ResponseEntity.accepted().build();
    }

}
