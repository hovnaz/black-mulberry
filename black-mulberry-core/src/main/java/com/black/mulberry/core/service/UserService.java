package com.black.mulberry.core.service;

import com.black.mulberry.data.transfer.request.UserRequest;
import com.black.mulberry.data.transfer.response.UserResponse;

import java.util.List;

public interface UserService {
    /**
     * Change of personal data
     * Except password, phone number and email
     *
     * @param userRequest
     * @return UserResponse
     */
    UserResponse update(UserRequest userRequest);

    /**
     * Remove user from database
     *
     * @param id "user id"
     */
    void deleteById(long id);

    /**
     * Find user by id
     *
     * @param id "user id"
     * @return UserResponse
     */
    UserResponse findById(long id);

    /**
     * Find user by email
     *
     * @param email
     * @return User
     */
    UserResponse findByEmail(String email);

    /**
     * Find all user list from database
     *
     * @return List of UserResponse
     */
    List<UserResponse> findAll();
}
