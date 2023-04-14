package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface represents the repository for User entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User entity with the specified email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the User entity with the specified email address, or an empty Optional if no matching entity is found
     */
    Optional<User> findByEmail(String email);
}
