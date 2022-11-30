package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.CategoryParent;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.data.transfer.request.CategoryParentRequest;
import com.black.mulberry.data.transfer.response.CategoryParentResponse;

import java.util.List;

public interface CategoryParentService {

    CategoryParent save(CategoryParentRequest categoryParentRequest);

    List<CategoryParentResponse> findAll();

    CategoryParent update(long categoryParentId, CategoryParentRequest categoryParentRequest);

    void deleteById(final long id);

    CategoryParent findById(long categoryParentId);

}
