package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.service.impl.ProductServiceImpl;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductEndpoint {
    private final ProductServiceImpl productService;


    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") long id) {
        return productService.findById(id);
    }


    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.save(productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest productRequest) {
        productService.update(id, productRequest);
        return ResponseEntity.noContent().build();
    }

}
