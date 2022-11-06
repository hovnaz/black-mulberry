package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.UserNotFoundException;
import com.black.mulberry.core.mapper.UserMapper;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.data.transfer.request.UserUpdateRequest;
import com.black.mulberry.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse update(final UserUpdateRequest userRequest, final long userId) {
        log.info("Request from user by id: {} to update", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            log.debug("User with id: {} already exists", userId);
            throw new UserNotFoundException("User with id: " + userId + " already exists");
        }
        User user = optionalUser.get();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        User save = userRepository.save(user);
        log.info("Succeed updated data from user {}", user.getEmail());
        return userMapper.toResponse(save);
    }

    @Override
    public void deleteById(final long id) {
        log.info("Request delete by id: {} user", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(final long id) {
        log.info("Find by id user");
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.debug("User with id: {} not found", id);
            return new UserNotFoundException("User with id: " + id + " NOT FOUND");
        });
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse findByEmail(final String email) {
        log.info("Find by email user");
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            log.debug("User with email: {} not found", email);
            return new UserNotFoundException("User with email: " + email + " NOT FOUND");
        });
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        log.info("Find all list Users");
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
