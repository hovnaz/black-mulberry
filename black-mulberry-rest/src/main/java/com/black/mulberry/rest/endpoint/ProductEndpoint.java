package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.service.impl.ProductServiceImpl;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import com.black.mulberry.rest.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductEndpoint {
    private final ProductServiceImpl productService;

    private final ProductMapper productMapper;


    @GetMapping
    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productService.findAll();
        return productList.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") long id) {
        return productMapper.toResponse(productService.findById(id));
    }


    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest, @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productMapper.toResponse(productService.save(productRequest,currentUser.getUser())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        if (productService.findById(id).getUser().getId() != currentUser.getUser().getId()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest productRequest,@AuthenticationPrincipal CurrentUser currentUser) {
        if (productService.findById(id).getUser().getId() != currentUser.getUser().getId()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        productService.update(id, productRequest);
        return ResponseEntity.noContent().build();
    }

}
