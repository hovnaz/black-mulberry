package com.black.mulberry.core.service;

import com.black.mulberry.data.transfer.request.UserRequest;
import com.black.mulberry.data.transfer.request.UserSignUpRequest;
import com.black.mulberry.data.transfer.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserSignUpRequest userRequest);

    UserResponse update(UserRequest userRequest);

    void deleteById(Long id);

    UserResponse findById(Long id);

    List<UserResponse> findAll();
}
