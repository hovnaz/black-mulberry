package com.black.mulberry.mvc.util;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductBasketService;
import com.black.mulberry.core.service.ProductCommentService;
import com.black.mulberry.core.service.ProductRatingService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MapUtil {

    private final ProductCommentService productCommentService;
    private final ProductRatingService productRatingService;
    private final ProductService productService;
    private final ProductBasketService productBasketService;

    public Map<String, Object> productDetail(long id, CurrentUser currentUser, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        int yourRating = currentUser == null ? 0 : productRatingService.findRateByProductIdUserId(id, currentUser.getId());

        map.put("product", productService.findById(id));
        map.put("comments", productCommentService.findPaginatedByProductId(id, pageable));
        map.put("commentCount", productCommentService.countAllByProductId(id));
        map.put("yourRating", yourRating);
        map.put("rating", productRatingService.avgProduct(id));
        map.put("ratingCount", productRatingService.countAllByProductId(id));
        map.put("categoryProduct",productService.findAllByCategoryProduct(id, pageable));
        return map;
    }

    public Map<String, Object> basketDetail(long userId, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        List<ProductBasketItemResponse> allByActual = productBasketService.findAllByActual(userId, pageable);
        map.put("products", allByActual);
        map.put("amount", productBasketService.amountByUserId(userId));
        map.put("isEmptyPage", allByActual.isEmpty());
        return map;
    }
}
