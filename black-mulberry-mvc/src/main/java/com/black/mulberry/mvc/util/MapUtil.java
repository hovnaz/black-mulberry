package com.black.mulberry.mvc.util;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductCommentService;
import com.black.mulberry.core.service.ProductRatingService;
import com.black.mulberry.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MapUtil {

    private final ProductCommentService productCommentService;
    private final ProductRatingService productRatingService;
    private final ProductService productService;

    public Map<String, Object> productDetail(long id, CurrentUser currentUser, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        int yourRating = currentUser == null ? 0 : productRatingService.findRateByProductIdUserId(id, currentUser.getId());

        map.put("product", productService.findById(id));
        map.put("comments", productCommentService.findPaginatedByProductId(id, pageable));
        map.put("commentCount", productCommentService.countAllByProductId(id));
        map.put("yourRating", yourRating);
        map.put("rating", productRatingService.avgProduct(id));
        map.put("ratingCount", productRatingService.countAllByProductId(id));
        return map;
    }
}
