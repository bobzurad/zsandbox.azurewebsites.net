package com.zurad.java.spring.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zurad.java.spring.eazybytes.beans.Car;
import com.zurad.java.spring.eazybytes.beans.Vehicle;

@Configuration
@ComponentScan(basePackages = { "com.zurad.java.spring.eazybytes.beans",
        "com.zurad.java.spring.eazybytes.beans.speakers",
        "com.zurad.java.spring.eazybytes.beans.tires", "com.zurad.java.spring.eazybytes.services" }) // register components in this package as beans
public class ProjectConfig {

    // name and value of this bean is vehicle
    @Bean
    Vehicle vehicle() {
        return new Vehicle("Tesla Model 3");
    }

    // name and value of this bean is vehicle2
    @Bean
    Vehicle vehicle2() {
        return new Vehicle("Ford Mustang");
    }

    // a named bean
    @Bean(name = "Rivian") // @Bean(value = "Rivian") and @Bean("Rivian") are also ways to name a bean
    Vehicle vehicle3() {
        return new Vehicle("Rivian R1S");
    }

    @Bean
    String hello() {
        return "Hello World";
    }

    // primary bean the IoC container will choose when multiple types are defined
    @Primary
    @Bean
    String goodbye() {
        return "Goodbye, Cruel World";
    }

    @Bean
    Integer number() {
        return 16;
    }

    @Bean
    Car car() {
        return new Car("Chevrolet Tahoe");
    }
}
