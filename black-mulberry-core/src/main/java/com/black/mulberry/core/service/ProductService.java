package com.black.mulberry.core.service;

import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {

    ProductResponse save(ProductRequest productRequest);

    ProductResponse update(long id, ProductRequest productRequest);

    void deleteById(long id);

    ProductResponse findById(long id);

    List<ProductResponse> findAll();

    byte[] getImage(@RequestParam("fileName") String fileName);

}
