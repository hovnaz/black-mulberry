package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.ProductRating;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.ProductRatingRequest;
import com.black.mulberry.data.transfer.response.ProductRatingResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductRatingMapper implements BaseMapper<ProductRating, ProductRatingRequest, ProductRatingResponse> {

    private final ModelMapper modelMapper;

    @Override
    public ProductRating toEntity(ProductRatingRequest ProductRatingRequest) {
        return modelMapper.map(ProductRatingRequest, ProductRating.class);
    }

    @Override
    public ProductRatingResponse toResponse(ProductRating productRating) {
        return modelMapper.map(productRating, ProductRatingResponse.class);
    }

}
