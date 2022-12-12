package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.CategoryParent;
import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.exception.CategoryNotFoundException;
import com.black.mulberry.core.exception.CategoryProductIsNotEmptyException;
import com.black.mulberry.core.mapper.CategoryProductMapper;
import com.black.mulberry.core.repository.CategoryProductRepository;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.service.CategoryParentService;
import com.black.mulberry.core.service.CategoryProductService;
import com.black.mulberry.data.transfer.request.CategoryProductRequest;
import com.black.mulberry.data.transfer.response.CategoryProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryProductServiceImpl implements CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;
    private final CategoryParentService categoryParentService;
    private final CategoryProductMapper categoryProductMapper;
    private final ProductRepository productRepository;

    @Override
    public CategoryProduct save(CategoryProductRequest categoryProductRequest) {
        CategoryProduct CategoryProduct = categoryProductMapper.toEntity(categoryProductRequest);
        return categoryProductRepository.save(CategoryProduct);
    }

    @Override
    public List<CategoryProductResponse> findAll() {
        List<CategoryProduct> productList = categoryProductRepository.findAll();
        return productList.stream()
                .map(categoryProductMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public CategoryProduct update(long categoryProductId, CategoryProductRequest categoryProductRequest) {
        log.info("request to update category by id: {}", categoryProductId);
        CategoryProduct categoryProduct = categoryProductMapper.toEntity(categoryProductRequest);
        CategoryProduct categoryProductById = findById(categoryProductId);
        CategoryParent categoryParentById = categoryParentService.findById(categoryProductRequest.getCategoryParentId());
        categoryProduct.setId(categoryProductById.getId());
        categoryProduct.setCategoryParent(categoryParentById);
        return categoryProductRepository.save(categoryProduct);
    }

    @Override
    public void deleteById(long categoryProductId) {
        log.info("request to delete categoryProduct with id: {}", categoryProductId);
        if (productRepository.countAllByCategoryProductIdAndIsDeleteFalse(categoryProductId) != 0){
            throw new CategoryProductIsNotEmptyException("you can't delete categoryProduct with id: " + categoryProductId + "because it is not empty");
        }
        CategoryProduct categoryProductById = findById(categoryProductId);
        categoryProductRepository.delete(categoryProductById);
        categoryProductById.setDelete(true);
        categoryProductRepository.save(categoryProductById);
        log.info("categoryProduct is successfully deleted");
    }

    @Override
    public CategoryProduct findById(long categoryId) {
        log.info("find category product by id: {}", categoryId);
        CategoryProduct categoryProduct = categoryProductRepository.findById(categoryId).orElseThrow(() -> {
            log.error("category with id: {} not found", categoryId);
            throw new CategoryNotFoundException("category with id: " + categoryId + " does not exist");
        });
        return categoryProduct;
    }
}
