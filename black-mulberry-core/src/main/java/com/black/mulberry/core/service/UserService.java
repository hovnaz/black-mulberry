package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.data.transfer.request.UserUpdateRequest;
import com.black.mulberry.data.transfer.response.UserResponse;

import java.util.List;

/**
 * The UserService interface provides methods for managing and retrieving users.
 */
public interface UserService {

    /**
     * Updates the personal data of a user, except for the password, phone number, and email.
     *
     * @param userRequest the updated user data
     * @param userId      the ID of the user to update
     * @return the updated user
     */
    User update(final UserUpdateRequest userRequest, final long userId);

    /**
     * Removes a user from the database by their ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteById(final long id);

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return the found user, or null if it doesn't exist
     */
    User findById(final long id);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find
     * @return the found user, or null if it doesn't exist
     */
    User findByEmail(final String email);

    /**
     * Finds all users in the database.
     *
     * @return the list of all users
     */
    List<UserResponse> findAll();
}
