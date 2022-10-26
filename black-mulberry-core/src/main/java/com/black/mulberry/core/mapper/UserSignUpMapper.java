package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.user.UserSignUpRequest;
import com.black.mulberry.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpMapper implements BaseMapper<User, UserSignUpRequest, UserResponse> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserSignUpRequest signUpRequest) {
        return modelMapper.map(signUpRequest, User.class);
    }

    @Override
    public UserResponse toResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
