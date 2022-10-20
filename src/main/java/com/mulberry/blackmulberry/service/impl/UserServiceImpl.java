package com.mulberry.blackmulberry.service.impl;

import com.mulberry.blackmulberry.entity.User;
import com.mulberry.blackmulberry.exception.EmailExistException;
import com.mulberry.blackmulberry.exception.UserNotFoundException;
import com.mulberry.blackmulberry.mapper.UserMapper;
import com.mulberry.blackmulberry.repository.UserRepository;
import com.mulberry.blackmulberry.service.UserService;
import com.mulberry.blackmulberry.transfer.request.UserRequest;
import com.mulberry.blackmulberry.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * This method receive data for add new user
     *
     * @param userRequest it's a new user
     * @return saved user
     */
    @Override
    public UserResponse save(UserRequest userRequest) {
        Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            throw new EmailExistException(
                    "User with email: " + userRequest.getEmail() + " already exist"
            );
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User save = userRepository.save(userMapper.toEntity(userRequest));
        return userMapper.toResponse(save);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                "user with id: " + id + " NOT FOUND"
        ));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
