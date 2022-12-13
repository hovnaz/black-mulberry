package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductSearchService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.request.ProductFilterRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductEndpoint {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductSearchService productSearchService;

    @GetMapping
    public List<ProductResponse> getAllProducts(@PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProduct(@RequestBody ProductFilterRequest productFilterRequest) {
        List<Product> searchedProducts = productSearchService.searchForProduct(productFilterRequest);
        return searchedProducts.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @GetMapping("/filtered")
    public List<ProductResponse> getAllFilteredProducts(@RequestBody ProductFilterRequest productFilterRequest) {
        List<Product> searchedProducts = productSearchService.filterProductByPrice(productFilterRequest);
        return searchedProducts.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @GetMapping("/my-list")
    public List<ProductResponse> getAllProductsByUserId(@AuthenticationPrincipal CurrentUser currentUser, @PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.findAllByUserId(currentUser.getId(), pageable);
    }

    @GetMapping("/category/{id}")
    public List<ProductResponse> getAllProductsByCategoryId(@PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable("id") long categoryProductId ) {
        return productService.findAllByCategoryProduct(categoryProductId, pageable);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllProducts() {
        return ResponseEntity.ok(productService.countAll());
    }

    @GetMapping("/my/count")
    public ResponseEntity<Long> countAllProductsByUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productService.countAllByUserId(currentUser.getId()));
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") long id) {
        return productMapper.toResponse(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest, @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productMapper.toResponse(productService.save(productRequest, currentUser.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        productService.deleteById(id, currentUser.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest productRequest,
                                                         @AuthenticationPrincipal CurrentUser currentUser) {
        Product updatedProduct = productService.update(id, currentUser.getId(), productRequest);
        return ResponseEntity.ok(productMapper.toResponse(updatedProduct));
    }
}
