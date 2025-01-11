package com.zurad.java.spring.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zurad.java.spring.eazybytes.beans.Vehicle;

@Configuration
public class ProjectConfig {

    @Bean
    Vehicle vehicle() {
        return new Vehicle("Tesla Model 3");
    }

    @Bean
    String hello() {
        return "Hello World";
    }

    @Bean
    Integer number() {
        return 16;
    }
}
