package com.simple.user;

import com.simple.user.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SimpleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleServiceApplication.class, args);
    }

    @Bean
    UserService userService() {
        return new UserService();
    }
}
