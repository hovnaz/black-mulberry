package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.CategoryParent;
import com.black.mulberry.core.exception.CategoryParentIsNotDeletedException;
import com.black.mulberry.core.exception.CategoryParentNotFoundException;
import com.black.mulberry.core.mapper.CategoryParentMapper;
import com.black.mulberry.core.repository.CategoryParentRepository;
import com.black.mulberry.core.repository.CategoryProductRepository;
import com.black.mulberry.core.service.CategoryParentService;
import com.black.mulberry.data.transfer.request.CategoryParentRequest;
import com.black.mulberry.data.transfer.response.CategoryParentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryParentServiceImpl implements CategoryParentService {

    private final CategoryParentRepository categoryParentRepository;
    private final CategoryParentMapper categoryParentMapper;
    private final CategoryProductRepository categoryProductRepository;

    @Override
    public CategoryParent save(CategoryParentRequest categoryParentRequest) {
        log.info("save categoryParent");
        CategoryParent categoryParent = categoryParentMapper.toEntity(categoryParentRequest);
        return categoryParentRepository.save(categoryParent);
    }

    @Override
    public List<CategoryParentResponse> findAll() {
        log.info("find all categoryParent's");
        List<CategoryParent> productList = categoryParentRepository.findAll();
        return productList.stream()
                .map(categoryParentMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public CategoryParent update(long categoryParentId, CategoryParentRequest categoryParentRequest) {
        log.info("request to update categoryParent");
        CategoryParent categoryParent = categoryParentMapper.toEntity(categoryParentRequest);
        CategoryParent categoryParentById = findById(categoryParentId);
        categoryParent.setId(categoryParentById.getId());
        log.info("categoryParent is successfully updated");
        return categoryParentRepository.save(categoryParent);
    }

    @Override
    public void deleteById(long categoryParentId) {
        log.info("request to delete categoryParent");
        if (categoryProductRepository.countAllByCategoryParentIdAndIsDeleteFalse(categoryParentId) != 0){
            log.error("you can't delete categoryParent with this id: {} because it is not empty", categoryParentId );
            throw new CategoryParentIsNotDeletedException("you can't delete categoryParent with this id: " + categoryParentId + "because it is not empty");
        }
        CategoryParent categoryParentById = findById(categoryParentId);
        categoryParentById.setDelete(true);
        log.info("categoryParent with id: {} is successfully deleted", categoryParentId);
        categoryParentRepository.save(categoryParentById);
    }

    @Override
    public CategoryParent findById(long categoryParentId) {
        log.info("find category parent by id: {}", categoryParentId);
        return categoryParentRepository.findById(categoryParentId).orElseThrow(() -> {
           log.error("categoryParent with id: {} not found", categoryParentId);
           throw new CategoryParentNotFoundException("categoryProduct with id: " + categoryParentId + " does not exist");
       });
    }
}
