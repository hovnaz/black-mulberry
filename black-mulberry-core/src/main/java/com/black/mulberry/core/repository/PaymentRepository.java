package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface represents the repository for Payment entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Finds a Payment entity with the specified user ID and order ID.
     *
     * @param userId  the ID of the user to search for
     * @param orderId the ID of the order to search for
     * @return an Optional containing the Payment entity with the specified user ID and order ID, if it exists, otherwise an empty Optional
     */
    Optional<Payment> findByUserIdAndOrderId(long userId, long orderId);

    /**
     * Finds a Payment entity with the specified order ID.
     *
     * @param orderId the ID of the order to search for
     * @return an Optional containing the Payment entity with the specified order ID, if it exists, otherwise an empty Optional
     */
    Optional<Payment> findByOrderId(long orderId);

    /**
     * Finds a Payment entity with the specified payment ID and user ID.
     *
     * @param paymentId the ID of the payment to search for
     * @param userId    the ID of the user to search for
     * @return an Optional containing the Payment entity with the specified payment ID and user ID, if it exists, otherwise an empty Optional
     */
    Optional<Payment> findByIdAndUserId(long paymentId, long userId);

}
