package com.black.mulberry.core.support;

import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.repository.ProductBasketItemRepository;
import com.black.mulberry.core.repository.ProductBasketRepository;
import com.black.mulberry.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductBasketSupport {

    private final UserService userService;
    private final UserSupport userSupport;
    private final ProductBasketRepository productBasketRepository;
    private final ProductBasketItemRepository productBasketItemRepository;

    public ProductBasket findActualBasketOrCreate(long userId) {
        log.info("find actual basket or create by user id: {}", userId);
        User user = userService.findById(userId);
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsActualTrue(userId);
        if (basketOptional.isEmpty()) {
            ProductBasket productBasketOptional = ProductBasket.builder()
                    .isActual(true)
                    .user(user)
                    .build();
            log.info("find and create actual basket ");
            return productBasketRepository.save(productBasketOptional);
        }
        log.info("find actual basket by user id: {}", userId);
        return basketOptional.get();
    }

    public boolean isEmpty(long basketId) {
        return productBasketItemRepository.countAllByProductBasketId(basketId) == 0;
    }
}
