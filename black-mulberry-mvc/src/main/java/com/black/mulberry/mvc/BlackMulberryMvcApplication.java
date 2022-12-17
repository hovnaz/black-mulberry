package com.black.mulberry.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.black.mulberry.core.*","com.black.mulberry.mvc.*"})
@EnableJpaRepositories(basePackages = {"com.black.mulberry.core.repository"})
@EntityScan({"com.black.mulberry.core.entity"})
@SpringBootApplication
public class BlackMulberryMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackMulberryMvcApplication.class, args);
    }
}
