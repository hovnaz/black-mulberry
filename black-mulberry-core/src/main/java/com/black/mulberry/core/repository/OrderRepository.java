package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.data.transfer.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the repository for Order entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds all orders for a user with the specified user ID and status.
     *
     * @param userId the ID of the user to search for
     * @param status the status of the orders to search for
     * @return a List of all orders for the user with the specified user ID and status
     */
    List<Order> findByUserIdAndStatusEquals(long userId, OrderStatus status);

    /**
     * Finds an order with the specified order ID and user ID.
     *
     * @param orderId the ID of the order to search for
     * @param userId  the ID of the user to search for
     * @return an Optional containing the Order entity with the specified order ID and user ID, if it exists, otherwise an empty Optional
     */
    Optional<Order> findByIdAndUserId(long orderId, long userId);

    /**
     * Finds an order with the specified basket ID and user ID.
     *
     * @param basketId the ID of the basket to search for
     * @param userId   the ID of the user to search for
     * @return an Optional containing the Order entity with the specified basket ID and user ID, if it exists, otherwise an empty Optional
     */
    Optional<Order> findByBasketIdAndUserId(long basketId, long userId);
}
