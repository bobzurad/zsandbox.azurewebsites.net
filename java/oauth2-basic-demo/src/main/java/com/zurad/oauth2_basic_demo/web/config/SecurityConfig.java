package com.zurad.oauth2_basic_demo.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(request -> request
                .requestMatchers("/secure").authenticated()
                .anyRequest().permitAll())
            .formLogin(Customizer.withDefaults())
            .oauth2Login(Customizer.withDefaults());

        return http.build();
    }
}
