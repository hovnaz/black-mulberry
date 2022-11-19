package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.exception.CategoryNotFoundException;
import com.black.mulberry.core.repository.CategoryProductRepository;
import com.black.mulberry.core.service.CategoryProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryProductImpl implements CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;

    @Override
    public CategoryProduct findById(long categoryId) {
        CategoryProduct categoryProduct = categoryProductRepository.findById(categoryId).orElseThrow(() -> {
            log.error("category with id: {} not found", categoryId);
            throw new CategoryNotFoundException("product with id: " + categoryId + " does not exist");
        });
        log.info("succesfully found category with id: {}", categoryId);
        return categoryProduct;
    }
}
