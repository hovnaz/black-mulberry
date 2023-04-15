package com.black.mulberry.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A configuration class for the password encoder.
 */
@Configuration
public class PasswordEncoderConfiguration {

    /**
     * Creates a new instance of the BCryptPasswordEncoder.
     *
     * @return a new BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
