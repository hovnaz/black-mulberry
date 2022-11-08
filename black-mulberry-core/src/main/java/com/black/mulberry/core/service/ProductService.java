package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {

    Product save(ProductRequest productRequest, User user);

    Product update(long id, ProductRequest productRequest);

    void deleteById(long id);

    Product findById(long id);

    List<Product> findAll();

    byte[] getImage(@RequestParam("fileName") String fileName);

}
