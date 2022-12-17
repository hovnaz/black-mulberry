package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.ProductBasketItem;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductBasketItemMapper implements BaseMapper<ProductBasketItem, ProductBasketItemRequest, ProductBasketItemResponse> {

    private final ModelMapper modelMapper;

    @Override
    public ProductBasketItem toEntity(ProductBasketItemRequest productBasketItemRequest) {
        return modelMapper.map(productBasketItemRequest, ProductBasketItem.class);
    }

    @Override
    public ProductBasketItemResponse toResponse(ProductBasketItem productBasketItem) {
        return modelMapper.map(productBasketItem, ProductBasketItemResponse.class);
    }
}
