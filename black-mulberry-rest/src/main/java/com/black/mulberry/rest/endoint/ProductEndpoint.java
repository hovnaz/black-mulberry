package com.black.mulberry.rest.endoint;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.exception.ProductNotExistException;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.service.ProductService;
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
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest)  {
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest productRequest){
            productService.update(id,productRequest);
            return ResponseEntity.ok().build();
    }

}
