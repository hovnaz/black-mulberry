package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents the repository for ProductPriceHistory entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory, Long> {

}
