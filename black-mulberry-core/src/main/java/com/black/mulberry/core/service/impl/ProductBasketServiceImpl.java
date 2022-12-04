package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.core.entity.ProductBasketItem;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.ProductBasketNotExistException;
import com.black.mulberry.core.mapper.ProductBasketItemMapper;
import com.black.mulberry.core.mapper.ProductBasketMapper;
import com.black.mulberry.core.repository.ProductBasketItemRepository;
import com.black.mulberry.core.repository.ProductBasketRepository;
import com.black.mulberry.core.service.ProductBasketService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.request.ProductBasketRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import com.black.mulberry.data.transfer.response.ProductBasketResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductBasketServiceImpl implements ProductBasketService {

    private final UserService userService;
//    private final ProductBasketService productBasketService;
    private final ProductBasketRepository productBasketRepository;
    private final ProductBasketItemRepository productBasketItemRepository;
    private final ProductBasketMapper productBasketMapper;
    private final ProductService productService;
    private final ProductBasketItemMapper productBasketItemMapper;

    @Override
    public ProductBasketItemResponse add(ProductBasketItemRequest productBasketItemRequest, long userId) {
        ProductBasket actualBasketOrCreate = findActualBasketOrCreate(userId);
        Product product = productService.findById(productBasketItemRequest.getProductId());
        ProductBasketItem productBasketItem = productBasketItemMapper.toEntity(productBasketItemRequest);
        productBasketItem.setProductBasket(actualBasketOrCreate);
        productBasketItem.setProduct(product);
        ProductBasketItem save = productBasketItemRepository.save(productBasketItem);
        return productBasketItemMapper.toResponse(save);
    }

    @Override
    public ProductBasket findByIdAndUserId(long basketId, long userId) {
        return productBasketRepository.findByUserIdAndIsPaidFalse(userId).orElseThrow(() -> {
            throw new ProductBasketNotExistException("Product basket with id: " + basketId + "and user id: " + userId + "not found");
        });
    }

    @Override
    public ProductBasket findById(long id) {
        return productBasketRepository.findByIdAndIsPaidFalse(id).orElseThrow(() -> {
            throw new ProductBasketNotExistException("Basket with id: " + id + " NOT FOUND");
        });
    }

    @Override
    public ProductBasketResponse cancelByProductId(long basketId, long userId) {
        ProductBasket basket = findByIdAndUserId(basketId, userId);
        basket.setPaid(true);
        ProductBasket save = productBasketRepository.save(basket);
        return productBasketMapper.toResponse(save);
    }

    @Override
    public Long countAllByUserId(Long userId) {
        User user = userService.findById(userId);
        return productBasketRepository.countAllByUserIdAndIsPaidFalse(userId);
    }


    @Override
    public void clear(long userId) {
        userService.findById(userId);
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsPaidFalse(userId);
        if (basketOptional.isPresent()){
            List<ProductBasketItem> productBasketItems = basketOptional.get().getProductBasketItems();
            productBasketItemRepository.deleteAll(productBasketItems);
        }
    }

    @Override
    public ProductBasketResponse update(ProductBasketRequest productBasketItemRequest, int quantity, long userId) {
        return null;
    }

    @Override
    public BigDecimal amount() {
        return null;
    }

    @Override
    public BigDecimal amountByProductId(Long productId) {
        return null;
    }

    @Override
    public ProductBasket findActualBasketOrCreate(long userId) {
        User user = userService.findById(userId);
        Optional<ProductBasket> basketOptional = productBasketRepository.findByUserIdAndIsPaidFalse(userId);
        if (basketOptional.isEmpty()) {
            ProductBasket product = ProductBasket.builder()
                    .user(user)
                    .isPaid(false)
                    .build();
            return productBasketRepository.save(product);
        }
        return basketOptional.get();
    }
}
