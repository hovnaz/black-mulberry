package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.QProduct;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.service.ProductSearchService;
import com.black.mulberry.data.transfer.request.ProductFilterRequest;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductRepository productRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Product> searchForProduct(ProductFilterRequest productFilterRequest) {
        log.info("request filtered product");

        QProduct qProduct = QProduct.product;

        var jpaQuery = new JPAQuery(entityManager);

        var from = jpaQuery.from(qProduct);

        if (StringUtils.isNotBlank(productFilterRequest.getTitle())) {
            from.where(qProduct.title.contains(productFilterRequest.getTitle()));
        }

        return from.fetch();
    }

    @Override
    public List<Product> filterProductByPrice(ProductFilterRequest productFilterRequest) {
        QProduct qProduct = QProduct.product;

        long minPrice = productFilterRequest.getMinPrice();

        long maxPrice = productFilterRequest.getMaxPrice();

        var jpaQuery = new JPAQuery(entityManager);

        var from = jpaQuery.from(qProduct);

        if (maxPrice >= minPrice) {
            from.where(qProduct.price.between(minPrice, maxPrice));
        }

        return from.fetch();
    }
}
