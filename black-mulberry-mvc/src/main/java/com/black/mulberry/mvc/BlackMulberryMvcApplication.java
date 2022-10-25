package com.black.mulberry.mvc;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.data.transfer.model.UserRole;
import com.black.mulberry.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ComponentScan({"com.black.mulberry.core.*","com.black.mulberry.mvc.*"})
@EnableJpaRepositories(basePackages = {"com.black.mulberry.core.repository"})
@EntityScan({"com.black.mulberry.core.entity"})
@SpringBootApplication
public class BlackMulberryMvcApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlackMulberryMvcApplication.class, args);
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
                    .email("admin@mail.com")
                    .password("$2a$10$MSM0ckZ/KkBdUPNtVRfcrezV/0tTSpzl5wxJJmewux6nda7Rh4u5m")
                    .phone("+37444444444")
                    .role(UserRole.ADMIN)
                    .build());
        }
    }
}
