package com.zurad.java.spring.eazybytes.beans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zurad.java.spring.eazybytes.beans.object.Car;
import com.zurad.java.spring.eazybytes.beans.object.Vehicle;

@Configuration
// register components in these packages as beans
@ComponentScan(basePackages = {
    "com.zurad.java.spring.eazybytes.beans.object.beans",
    "com.zurad.java.spring.eazybytes.beans.object.beans.speakers",
    "com.zurad.java.spring.eazybytes.beans.object.beans.tires",
    "com.zurad.java.spring.eazybytes.beans.services" })
public class BeanConfig {

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
    @Bean(name = "Rivian")
    // @Bean(value = "Rivian") and @Bean("Rivian") are also ways to name a bean
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
