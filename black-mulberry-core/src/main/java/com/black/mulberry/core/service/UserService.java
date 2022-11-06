package com.black.mulberry.core.service;

import com.black.mulberry.data.transfer.request.UserUpdateRequest;
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
    UserResponse update(final UserUpdateRequest userRequest, final long userId);

    /**
     * Remove user from database
     *
     * @param id "user id"
     */
    void deleteById(final long id);

    /**
     * Find user by id
     *
     * @param id "user id"
     * @return UserResponse
     */
    UserResponse findById(final long id);

    /**
     * Find user by email
     *
     * @param email
     * @return User
     */
    UserResponse findByEmail(final String email);

    /**
     * Find all user list from database
     *
     * @return List of UserResponse
     */
    List<UserResponse> findAll();
}
