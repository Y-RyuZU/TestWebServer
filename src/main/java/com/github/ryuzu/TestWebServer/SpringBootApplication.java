package com.github.ryuzu.TestWebServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@org.springframework.boot.autoconfigure.SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}
