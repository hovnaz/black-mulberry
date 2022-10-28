package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.EmailExistException;
import com.black.mulberry.core.exception.UserNotFoundException;
import com.black.mulberry.core.mapper.UserMapper;
import com.black.mulberry.core.mapper.UserSignUpMapper;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.data.transfer.model.UserRole;
import com.black.mulberry.data.transfer.request.UserRequest;
import com.black.mulberry.data.transfer.request.UserSignUpRequest;
import com.black.mulberry.data.transfer.response.UserResponse;
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
    private final UserSignUpMapper userSignUpMapper;

    /**
     * @param userRequest
     * @return UserResponse
     */
    @Override
    public UserResponse create(UserSignUpRequest userRequest) {
        Optional<User> byEmail = userRepository.findByEmail(userRequest.getEmail());
        if (byEmail.isPresent()) {
            throw new EmailExistException(
                    "User with email: " + userRequest.getEmail() + " already exist"
            );
        }
        User user = User.builder()
                .phone(userRequest.getPhone())
                .email(userRequest.getEmail())
                .role(UserRole.USER)
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
        User save = userRepository.save(user);
        return userSignUpMapper.toResponse(save);
    }

    /**
     * This method receive data for add new user
     *
     * @param userRequest it's a new user
     * @return update user
     */
    @Override
    public UserResponse update(UserRequest userRequest) {
        long userId = userRequest.getId();
        User user = findByIdOrElseThrow(userId);
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        User save = userRepository.save(user);
        return userMapper.toResponse(save);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(Long id) {
        User user = findByIdOrElseThrow(id);
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private User findByIdOrElseThrow(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                "user with id: " + id + " NOT FOUND"
        ));
    }
}
