package com.mulberry.blackmulberry.service;

import com.mulberry.blackmulberry.transfer.request.UserRequest;
import com.mulberry.blackmulberry.transfer.response.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse save(UserRequest userRequest);

    void deleteById(Long id);

    UserResponse findById(Long id);

    List<UserResponse> findAll();
}
