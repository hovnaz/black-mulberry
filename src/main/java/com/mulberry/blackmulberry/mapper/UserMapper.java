package com.mulberry.blackmulberry.mapper;

import com.mulberry.blackmulberry.entity.User;
import com.mulberry.blackmulberry.mapper.base.BaseMapper;
import com.mulberry.blackmulberry.transfer.request.user.UserRequest;
import com.mulberry.blackmulberry.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @see org.modelmapper.ModelMapper
 */
@Service
@RequiredArgsConstructor
public class UserMapper implements BaseMapper<User, UserRequest, UserResponse> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    @Override
    public UserResponse toResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
