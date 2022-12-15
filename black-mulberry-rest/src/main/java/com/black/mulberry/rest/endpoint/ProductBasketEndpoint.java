package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductBasketService;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product/basket")
@PreAuthorize("isAuthenticated()")
public class ProductBasketEndpoint {

    private final ProductBasketService productBasketService;

    @GetMapping("/actual/list")
    public ResponseEntity<List<ProductBasketItemResponse>> findAll(@PageableDefault Pageable pageable,
                                                                   @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productBasketService.findAllByActual(currentUser.getId(), pageable));
    }

    @GetMapping("/amount")
    public ResponseEntity<BigDecimal> amount(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productBasketService.amountByUserId(currentUser.getId()));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody ProductBasketItemRequest productBasketItemRequest,
                                    @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productBasketService.update(productBasketItemRequest, currentUser.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> cansel(@PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser) {
        productBasketService.cancelByProductId(currentUser.getId(), id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping
    public ResponseEntity<?> clear(@AuthenticationPrincipal CurrentUser currentUser) {
        productBasketService.clear(currentUser.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ProductBasketItemResponse> add(@Valid @RequestBody ProductBasketItemRequest productBasketItemRequest,
                                                         @AuthenticationPrincipal CurrentUser currentUser) {
        ProductBasketItemResponse productBasketItemResponse = productBasketService.add(productBasketItemRequest, currentUser.getId());
        return ResponseEntity.ok(productBasketItemResponse);
    }
}
