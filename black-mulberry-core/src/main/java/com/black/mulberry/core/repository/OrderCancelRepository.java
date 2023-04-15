package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.OrderCancel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface represents the repository for OrderCancel entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface OrderCancelRepository extends JpaRepository<OrderCancel, Long> {

    /**
     * Finds an OrderCancel entity with the specified orderId.
     *
     * @param orderId the id of the order to search for
     * @return an Optional containing the OrderCancel entity with the specified orderId, if it exists, otherwise an empty Optional
     */
    Optional<OrderCancel> findByOrderId(long orderId);
}
