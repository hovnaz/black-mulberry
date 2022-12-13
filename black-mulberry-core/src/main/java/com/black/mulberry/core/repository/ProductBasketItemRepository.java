package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductBasketItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductBasketItemRepository extends JpaRepository<ProductBasketItem, Long> {

    Optional<ProductBasketItem> findByProductBasketIdAndProductId(long basketId, long productId);

    List<ProductBasketItem> findAllByProductBasketId(long basketId, Pageable pageable);

    List<ProductBasketItem> findAllByProductBasketId(long basketId);

    long countAllByProductBasketId(long basketId);

    void deleteAllByProductBasketId(long basketId);
}
