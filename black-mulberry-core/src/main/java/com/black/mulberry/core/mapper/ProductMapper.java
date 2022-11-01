package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMapper implements BaseMapper<Product,ProductRequest, ProductResponse> {
    private final ModelMapper modelMapper;

    @Override
    public Product toEntity(ProductRequest productRequest) {
        return modelMapper.map(productRequest, Product.class);
    }

    @Override
    public ProductResponse toResponse(Product product) {
        return modelMapper.map(product, ProductResponse.class);
    }

}
