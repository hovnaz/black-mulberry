package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.core.entity.ProductBasketItem;
import com.black.mulberry.core.exception.ProductBasketNotExistException;
import com.black.mulberry.core.mapper.ProductBasketItemMapper;
import com.black.mulberry.core.repository.ProductBasketItemRepository;
import com.black.mulberry.core.repository.ProductBasketRepository;
import com.black.mulberry.core.service.ProductBasketService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.core.support.ProductBasketSupport;
import com.black.mulberry.core.support.ProductSupport;
import com.black.mulberry.core.support.UserSupport;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductBasketServiceImpl implements ProductBasketService {

    private final UserSupport userServiceSupport;
    private final ProductBasketRepository productBasketRepository;
    private final ProductBasketItemRepository productBasketItemRepository;
    private final ProductService productService;
    private final ProductBasketItemMapper productBasketItemMapper;
    private final ProductBasketSupport productBasketServiceSupport;
    private final ProductSupport productServiceSupport;

    @Override
    public ProductBasketItemResponse add(ProductBasketItemRequest productBasketItemRequest, long userId) {
        userServiceSupport.ifPresentOrElseThrow(userId);
        log.info("request add product in basket user id: {} and product id: {}", userId, productBasketItemRequest.getProductId());
        ProductBasket actualBasket = productBasketServiceSupport.findActualBasketOrCreate(userId);
        Product product = productService.findById(productBasketItemRequest.getProductId());
        userServiceSupport.ifUsersNotEqualsOrElseThrow(userId, product.getUser().getId());
        Optional<ProductBasketItem> productBasketItemOptional = productBasketItemRepository.findByProductBasketIdAndProductId(actualBasket.getId(), product.getId());
        ProductBasketItem productBasketItem;
        if (productBasketItemOptional.isPresent()) {
            productBasketItem = productBasketItemOptional.get();
            productBasketItem.setQuantity(productBasketItem.getQuantity() + productBasketItemRequest.getQuantity());
        } else {
            productBasketItem = productBasketItemMapper.toEntity(productBasketItemRequest);
            productBasketItem.setProductBasket(actualBasket);
            productBasketItem.setProduct(product);
        }
        ProductBasketItem save = productBasketItemRepository.save(productBasketItem);
        log.info("successfully added product in basket user id: {} and product id: {}", userId, productBasketItemRequest.getProductId());
        return productBasketItemMapper.toResponse(save);
    }

    @Override
    public void cancelByProductId(long userId, long productId) {
        log.info("cansel from actual basket product id: {} and user id: {}", productId, userId);
        userServiceSupport.ifPresentOrElseThrow(userId);
        Optional<ProductBasket> productBasketOptional = productBasketRepository.findByUserIdAndIsActualTrue(userId);
        productBasketOptional.flatMap(productBasket -> productBasketItemRepository.findByProductBasketIdAndProductId(productBasket.getId(), productId))
                .ifPresent(productBasketItem -> productBasketItemRepository.deleteById(productBasketItem.getId()));
    }

    @Override
    public List<ProductBasketItemResponse> findAllByActual(long userId, Pageable pageable) {
        log.info("find all actual basket product by user id: {}", userId);
        userServiceSupport.ifPresentOrElseThrow(userId);
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsActualTrue(userId);
        List<ProductBasketItem> productList = new ArrayList<>();
        if (basketOptional.isPresent()) {
            productList = productBasketItemRepository.findAllByProductBasketId(basketOptional.get().getId(), pageable);
        }
        return productList.stream()
                .map(productBasketItemMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public ProductBasket findBasketByActual(long userId) {
        log.info("find actual basket by user id: {}", userId);
        userServiceSupport.ifPresentOrElseThrow(userId);
        return productBasketRepository.findByUserIdAndIsActualTrue(userId).orElseThrow(() -> {
            log.error("Actual Product Basket not found by user id: {}", userId);
            throw new ProductBasketNotExistException("Actual Product Basket not found by user id:" + userId);
        });
    }

    @Override
    public ProductBasket findByIdAndUserId(long basketId, long userId) {
        log.info("find basket by id: {} and user id: {}", basketId, userId);
        return productBasketRepository.findByIdAndUserId(basketId, userId).orElseThrow(() -> {
            log.error("basket not found by id: {} and user id: {}", basketId, userId);
            throw new ProductBasketNotExistException("basket not found by id: " + basketId + " and user id: " + userId);
        });
    }

    @Override
    public long countAllByUserId(long userId) {
        log.info("get count all product in actual basket by user id: {}", userId);
        userServiceSupport.ifPresentOrElseThrow(userId);
        Optional<ProductBasket> productBasketOptional = productBasketRepository.findByUserIdAndIsActualTrue(userId);
        return productBasketOptional.map(productBasket -> productBasketItemRepository.countAllByProductBasketId(productBasket.getId())).orElse(0L);
    }

    @Override
    public void clear(long userId) {
        log.info("clear all basket by user id: {}", userId);
        userServiceSupport.ifPresentOrElseThrow(userId);
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsActualTrue(userId);
        basketOptional.ifPresent(productBasket -> productBasketItemRepository.deleteAllByProductBasketId(productBasket.getId()));
    }

    @Override
    public ProductBasketItemResponse update(ProductBasketItemRequest productBasketItemRequest, long userId) {
        log.info("update by product id: {} quantity and by user id: {}", productBasketItemRequest.getProductId(), userId);
        userServiceSupport.ifPresentOrElseThrow(userId);
        productServiceSupport.ifPresentOrElseThrow(productBasketItemRequest.getProductId());
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsActualTrue(userId);
        if (basketOptional.isPresent()) {
            Optional<ProductBasketItem> productBasketItemOptional = productBasketItemRepository.findByProductBasketIdAndProductId(basketOptional.get().getId(), productBasketItemRequest.getProductId());
            if (productBasketItemOptional.isPresent()) {
                ProductBasketItem productBasketItem = productBasketItemOptional.get();
                productBasketItem.setQuantity(productBasketItem.getQuantity());
                ProductBasketItem save = productBasketItemRepository.save(productBasketItem);
                return productBasketItemMapper.toResponse(save);
            }
        }
        return add(productBasketItemRequest, userId);
    }

    @Override
    public BigDecimal actualAmountByUserId(long userId) {
        log.info("find amount actual basket by user id: {}", userId);
        userServiceSupport.ifPresentOrElseThrow(userId);
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsActualTrue(userId);
        BigDecimal amount = BigDecimal.ZERO;
        if (basketOptional.isPresent()) {
            List<ProductBasketItem> productBasketItemList = productBasketItemRepository.findAllByProductBasketId(basketOptional.get().getId());
            amount = calculateAmountByList(productBasketItemList);
        }
        return amount;
    }

    @Override
    public BigDecimal amountByUserIdAndBasketId(long userId, long basketId) {
        ProductBasket basket = findByIdAndUserId(basketId, userId);
        List<ProductBasketItem> productBasketItemList = productBasketItemRepository.findAllByProductBasketId(basket.getId());
        return calculateAmountByList(productBasketItemList);
    }

    private BigDecimal calculateAmountByList(List<ProductBasketItem> productBasketItemList) {
        BigDecimal amount = BigDecimal.ZERO;
        for (ProductBasketItem productBasketItem : productBasketItemList) {
            amount = amount.add(productBasketItem.getProduct().getPrice());
        }
        return amount;
    }
}
