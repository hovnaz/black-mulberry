package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    /**
     * save product by user id
     *
     * @param productRequest
     * @param user
     * @return Product
     */
    Product save(ProductRequest productRequest, long userId);

    /**
     * update product by taking it's id and userId
     *
     * @param productId
     * @param userId
     * @param productRequest
     * @return product
     */
    Product update(long productId, long userId, ProductRequest productRequest);

    /**
     * delete product
     *
     * @param productId
     * @param userId
     */
    void deleteById(long productId, long userId);

    /**
     * find product by it's id
     *
     * @param id
     * @return product
     */
    Product findById(long id);

    /**
     * find product by it's id and userId
     *
     * @param productId
     * @param userId
     * @return Product
     */
    Product findByIdAndUserId(long productId, long userId);

    /**
     * find product list
     *
     * @return List<ProductResponse>
     */
    List<ProductResponse> findAll(Pageable pageable);

    /**
     * find products list by user id
     *
     * @param userId
     * @return
     */
    List<ProductResponse> findAllByUserId(long userId, Pageable pageable);

    /**
     * get product image
     *
     * @param fileName
     * @return byte[] buffer
     */
    byte[] getImage(String fileName) throws IOException;

    String saveImage(MultipartFile file);

    long countAll();

    long countAllByUserId(long userId);
}
