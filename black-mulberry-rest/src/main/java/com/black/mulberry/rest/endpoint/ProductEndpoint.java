package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.service.impl.ProductServiceImpl;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import com.black.mulberry.core.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductEndpoint {

    private final ProductServiceImpl productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/my-list")
    public List<ProductResponse> getAllProductsByUserId(@AuthenticationPrincipal CurrentUser currentUser) {
        return productService.findAllByUserId(currentUser.getUser().getId());
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") long id) {
        return productMapper.toResponse(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest, @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productMapper.toResponse(productService.save(productRequest, currentUser.getUser())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        productService.deleteById(id, currentUser.getUser().getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest productRequest,
                                                         @AuthenticationPrincipal CurrentUser currentUser) {
        Product updatedProduct = productService.update(id, currentUser.getUser().getId(), productRequest);
        return ResponseEntity.ok(productMapper.toResponse(updatedProduct));
    }
}
