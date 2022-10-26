package com.black.mulberry.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.black.mulberry.core.*","com.black.mulberry.rest.*"})
@EnableJpaRepositories(basePackages = {"com.black.mulberry.core.repository"})
@EntityScan({"com.black.mulberry.core.entity"})
@SpringBootApplication
public class BlackMulberryRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackMulberryRestApplication.class, args);
    }
}
