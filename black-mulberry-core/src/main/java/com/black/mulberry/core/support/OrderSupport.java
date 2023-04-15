package com.black.mulberry.core.support;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.core.exception.OrderConflictException;
import com.black.mulberry.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The OrderSupport class provides methods for checking the presence of orders for a given basket and user.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderSupport {

    private final OrderRepository orderRepository;

    /**
     * Checks if an order already exists for a given basket and user, and throws an exception if it does.
     *
     * @param basketId the ID of the basket to check
     * @param userId   the ID of the user to check
     * @throws OrderConflictException if an order already exists for the basket and user
     */
    public void ifPresentThrow(long basketId, long userId) {
        log.info("Checking if order exists for basket ID {} and user ID {}", basketId, userId);
        Optional<Order> orderOptional = orderRepository.findByBasketIdAndUserId(basketId, userId);
        if (orderOptional.isPresent()) {
            log.error("Order conflict exception for basket ID {} and user ID {}", basketId, userId);
            throw new OrderConflictException("Order conflict exception for basket ID " + basketId + " and user ID " + userId);
        }
    }
}
