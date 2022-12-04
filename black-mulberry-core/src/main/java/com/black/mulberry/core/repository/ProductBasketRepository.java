package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductBasket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductBasketRepository extends JpaRepository<ProductBasket, Long> {


    Optional<ProductBasket> findByIdAndIsPaidFalse(long id);

    Optional<ProductBasket> findByUserIdAndIsPaidFalse(long userId);

    Long countAllByUserIdAndIsPaidFalse(long userId);

    //    List<ProductBasket> findAllByUserIdAndIsPaidFalse(long userId, Pageable pageable);
//    long countAllByUserIdAndIsPaidFalse(long userId);

    //    List<ProductBasket> findAllByProductBasketIdAndUserIdAndIsPaidFalse(long productBasketId, long userId, Pageable pageable);
//
//    long countAllByProductBasketIdAndUserIdAndIsPaidFalse(long productBasketId, long userId);
//
//    List<ProductBasket> findAllByProductBasketIdAndIsPaidFalse(long productBasketId, Pageable pageable);
//
//    long countAllByProductBasketIdAndIsPaidFalse(long productBasketId);
//

//
//    Optional<ProductBasket> findByIdAndUserIdAndIsPaidFalse(long productBasketId, long userId);

}