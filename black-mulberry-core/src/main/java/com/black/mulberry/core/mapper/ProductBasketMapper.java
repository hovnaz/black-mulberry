package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.ProductBasketRequest;
import com.black.mulberry.data.transfer.response.ProductBasketResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductBasketMapper implements BaseMapper<ProductBasket, ProductBasketRequest, ProductBasketResponse> {

    private final ModelMapper modelMapper;

    @Override
    public ProductBasket toEntity(ProductBasketRequest productBasketRequest) {
        return modelMapper.map(productBasketRequest, ProductBasket.class);
    }

    @Override
    public ProductBasketResponse toResponse(ProductBasket productBasket) {
        return modelMapper.map(productBasket, ProductBasketResponse.class);
    }
}
