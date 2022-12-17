package com.black.mulberry.rest.controller;

import com.black.mulberry.rest.config.PostgresTestContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EnableConfigurationProperties
@TestPropertySource("classpath:application-test.properties")
public class UserEndpointTest extends PostgresTestContainerConfig {

    @Test
    void contextLoads() {
    }
}
