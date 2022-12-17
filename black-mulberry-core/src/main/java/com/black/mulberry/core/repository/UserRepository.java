package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param email
     * @return find user by email
     */
    Optional<User> findByEmail(String email);
}
