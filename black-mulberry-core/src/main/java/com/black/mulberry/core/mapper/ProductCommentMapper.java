package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.ProductComment;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.ProductCommentRequest;
import com.black.mulberry.data.transfer.response.ProductCommentResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCommentMapper implements BaseMapper<ProductComment, ProductCommentRequest, ProductCommentResponse> {

    private final ModelMapper modelMapper;

    @Override
    public ProductComment toEntity(ProductCommentRequest productCommentRequest) {
        return modelMapper.map(productCommentRequest, ProductComment.class);
    }

    @Override
    public ProductCommentResponse toResponse(ProductComment productComment) {
        return modelMapper.map(productComment, ProductCommentResponse.class);
    }
}
