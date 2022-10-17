package com.mulberry.blackmulberry.service.impl;

import com.mulberry.blackmulberry.entity.User;
import com.mulberry.blackmulberry.repository.UserRepository;
import com.mulberry.blackmulberry.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Username" + email + " does not exists!")
        );
        return new CurrentUser(user);
    }
}
