package com.zurad.java.spring.eazybytes.beans;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// this class is defined as a Component
// we don't need to define it as a Bean in our Configuration, if we use @ComponentScan in ProjectConfig.java
@Component
public class Animal {

    private String name;

    public Animal() {}

    public Animal(String name) {
        this.name = name;
    }

    @PostConstruct
    private void initialize() {
        this.name = "Dog";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("woof woof");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("About to destroy Animal Bean");
    }
}
