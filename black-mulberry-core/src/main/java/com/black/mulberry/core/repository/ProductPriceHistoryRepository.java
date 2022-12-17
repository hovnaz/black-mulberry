package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory, Long> {
}
