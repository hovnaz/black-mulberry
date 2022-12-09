package com.black.mulberry.core.service.support;

import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.repository.ProductBasketRepository;
import com.black.mulberry.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductBasketServiceSupport {

    private final UserService userService;
    private final ProductBasketRepository productBasketRepository;

    public ProductBasket findActualBasketOrCreate(long userId) {
        User user = userService.findById(userId);
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsPaidFalse(userId);
        if (basketOptional.isEmpty()) {
            ProductBasket productBasketOptional = ProductBasket.builder()
                    .isPaid(false)
                    .user(user)
                    .build();
            return productBasketRepository.save(productBasketOptional);
        }
        return basketOptional.get();
    }
}
