package com.mulberry.blackmulberry;

import com.mulberry.blackmulberry.entity.User;
import com.mulberry.blackmulberry.entity.model.UserRole;
import com.mulberry.blackmulberry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class BlackMulberryApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlackMulberryApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

        Optional<User> byEmail = userRepository.findByEmail("admin@mail.com");

        if (byEmail.isEmpty()) {
            userRepository.save(User.builder()
                    .name("admin")
                    .surname("admin")
                    .email("admin")
                    .password(passwordEncoder().encode("admin"))
                    .phone("+37444444444")
                    .role(UserRole.ADMIN)
                    .build());
        }
    }
}
