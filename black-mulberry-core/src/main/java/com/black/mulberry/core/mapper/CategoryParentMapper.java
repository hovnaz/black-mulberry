package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.CategoryParent;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.CategoryParentRequest;
import com.black.mulberry.data.transfer.response.CategoryParentResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryParentMapper implements BaseMapper<CategoryParent, CategoryParentRequest, CategoryParentResponse> {
    private final ModelMapper modelMapper;

    @Override
    public CategoryParent toEntity(CategoryParentRequest categoryParentRequest) {
        return modelMapper.map(categoryParentRequest, CategoryParent.class);
    }

    @Override
    public CategoryParentResponse toResponse(CategoryParent categoryParent) {
        return modelMapper.map(categoryParent, CategoryParentResponse.class);
    }
}
