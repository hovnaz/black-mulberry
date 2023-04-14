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

/**
 * The ProductBasketSupport class provides methods for managing and retrieving product baskets.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductBasketSupport {

    private final UserService userService;
    private final ProductBasketRepository productBasketRepository;
    private final ProductBasketItemRepository productBasketItemRepository;

    /**
     * Finds the actual product basket for a given user, or creates one if it doesn't exist.
     *
     * @param userId the ID of the user to find the basket for
     * @return the actual basket for the user
     */
    public ProductBasket findActualBasketOrCreate(long userId) {
        log.info("Finding actual basket or creating one for user ID {}", userId);
        User user = userService.findById(userId);
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsActualTrue(userId);
        if (basketOptional.isEmpty()) {
            ProductBasket productBasketOptional = ProductBasket.builder()
                    .isActual(true)
                    .user(user)
                    .build();
            log.info("Creating actual basket for user ID {}", userId);
            return productBasketRepository.save(productBasketOptional);
        }
        log.info("Finding actual basket for user ID {}", userId);
        return basketOptional.get();
    }

    /**
     * Checks if a given product basket is empty.
     *
     * @param basketId the ID of the basket to check
     * @return true if the basket is empty, false otherwise
     */
    public boolean isEmpty(long basketId) {
        return productBasketItemRepository.countAllByProductBasketId(basketId) == 0;
    }
}
