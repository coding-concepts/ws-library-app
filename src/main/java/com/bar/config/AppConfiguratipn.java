package com.bar.config;


import com.bar.service.UserService;
import com.bar.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguratipn {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
