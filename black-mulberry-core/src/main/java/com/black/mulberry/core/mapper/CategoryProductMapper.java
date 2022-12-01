package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.CategoryProductRequest;
import com.black.mulberry.data.transfer.response.CategoryProductResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryProductMapper implements BaseMapper<CategoryProduct, CategoryProductRequest, CategoryProductResponse> {
    private final ModelMapper modelMapper;

    @Override
    public CategoryProduct toEntity(CategoryProductRequest categoryProductRequest) {
        return modelMapper.map(categoryProductRequest, CategoryProduct.class);
    }

    @Override
    public CategoryProductResponse toResponse(CategoryProduct categoryProduct) {
        return modelMapper.map(categoryProduct, CategoryProductResponse.class);
    }
}
