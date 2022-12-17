package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductBasket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductBasketRepository extends JpaRepository<ProductBasket, Long> {

    Optional<ProductBasket> findByUserIdAndIsActualTrue(long userId);

    Optional<ProductBasket> findByIdAndUserId(long basketId, long userId);

}
