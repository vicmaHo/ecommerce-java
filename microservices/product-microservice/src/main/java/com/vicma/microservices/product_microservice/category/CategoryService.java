package com.vicma.microservices.product_microservice.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

}
