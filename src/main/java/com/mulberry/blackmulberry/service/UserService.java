package com.mulberry.blackmulberry.service;

import com.mulberry.blackmulberry.transfer.request.user.UserRequest;
import com.mulberry.blackmulberry.transfer.request.user.UserSignUpRequest;
import com.mulberry.blackmulberry.transfer.response.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse create(UserSignUpRequest userRequest);

    UserResponse update(UserRequest userRequest);

    void deleteById(Long id);

    UserResponse findById(Long id);

    List<UserResponse> findAll();
}
