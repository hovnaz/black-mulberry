package com.mulberry.blackmulberry.mapper;

import com.mulberry.blackmulberry.entity.User;
import com.mulberry.blackmulberry.mapper.base.BaseMapper;
import com.mulberry.blackmulberry.transfer.request.user.UserSignUpRequest;
import com.mulberry.blackmulberry.transfer.response.UserResponse;
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
