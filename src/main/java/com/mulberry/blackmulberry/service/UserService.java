package com.mulberry.blackmulberry.service;

import com.mulberry.blackmulberry.entity.User;
import com.mulberry.blackmulberry.exception.DuplicateResourceException;
import com.mulberry.blackmulberry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) throws DuplicateResourceException {
        if (userService.findByEmail(user.getEmail()).isPresent()){
            throw new DuplicateResourceException("User already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }
}
