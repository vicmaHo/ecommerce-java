package com.vicma.microservices.product_microservice.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vicma.microservices.product_microservice.exceptions.CategoryNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryResponse> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(mapper::toCategoryResponse)
                .collect(Collectors.toList());
    }

    public Integer createCategory(CategoryRequest request) {
        Category category = mapper.toCategory(request);
        return repository.save(category).getId();
    }

    public CategoryResponse getCategoryById(Integer id) {
        return repository.findById(id)
                .map(mapper::toCategoryResponse)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with ID %s not found", id)));
    }

    public void updateCategory(CategoryRequest request) {
        var category = repository.findById(request.getId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with ID %s not found", request.getId())));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        repository.save(category);

    }

    public void deleteCategory(Integer id) {
        repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(
                String.format("Category with ID %s not found", id)));
        repository.deleteById(id);
    }

}
