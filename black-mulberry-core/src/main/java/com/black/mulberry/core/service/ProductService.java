package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The ProductService interface provides methods for managing and retrieving products.
 */
public interface ProductService {

    /**
     * Saves a new product for a given user.
     *
     * @param productRequest the product request data
     * @param userId         the ID of the user who is creating the product
     * @return the saved product
     */
    Product save(ProductRequest productRequest, long userId);

    /**
     * Updates an existing product for a given user.
     *
     * @param productId      the ID of the product to update
     * @param userId         the ID of the user who is updating the product
     * @param productRequest the updated product request data
     * @return the updated product
     */
    Product update(long productId, long userId, ProductRequest productRequest);

    /**
     * Deletes a product by its ID and the ID of the user who owns it.
     *
     * @param productId the ID of the product to delete
     * @param userId    the ID of the user who owns the product
     */
    void deleteById(long productId, long userId);

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to find
     * @return the found product, or null if it doesn't exist
     */
    Product findById(long id);

    /**
     * Finds a product by its ID and the ID of the user who owns it.
     *
     * @param productId the ID of the product to find
     * @param userId    the ID of the user who owns the product
     * @return the found product, or null if it doesn't exist or if the user doesn't own the product
     */
    Product findByIdAndUserId(long productId, long userId);

    /**
     * Finds all products, with pagination.
     *
     * @param pageable the pagination information
     * @return the list of all products
     */
    List<ProductResponse> findAll(Pageable pageable);

    /**
     * Finds all products owned by a given user, with pagination.
     *
     * @param userId   the ID of the user who owns the products
     * @param pageable the pagination information
     * @return the list of products owned by the user
     */
    List<ProductResponse> findAllByUserId(long userId, Pageable pageable);

    /**
     * Gets the image data for a given file name.
     *
     * @param fileName the name of the image file
     * @return the image data as a byte array
     * @throws IOException if an error occurs while retrieving the image data
     */
    byte[] getImage(String fileName) throws IOException;

    /**
     * Saves an image file and returns the name of the saved file.
     *
     * @param file the image file to save
     * @return the name of the saved file
     */
    String saveImage(MultipartFile file);

    /**
     * Counts the total number of products.
     *
     * @return the total number of products
     */
    long countAll();

    /**
     * Counts the total number of products in a given category.
     *
     * @param categoryProductId the ID of the category to count products for
     * @return the total number of products in the category
     */
    long countAllByCategoryId(long categoryProductId);

    /**
     * Counts the total number of products owned by a given user.
     *
     * @param userId the ID of the user who owns the products
     * @return the total number of products owned by the user
     */
    long countAllByUserId(long userId);

    /**
     * Finds all products in a given category, with pagination.
     *
     * @param categoryProductId the ID of the category to find products for
     * @param pageable          the pagination information
     * @return the list of products in the category
     */
    List<ProductResponse> findAllByCategoryProduct(long categoryProductId, Pageable pageable);
}
