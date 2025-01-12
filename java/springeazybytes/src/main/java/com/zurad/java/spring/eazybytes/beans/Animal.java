package com.zurad.java.spring.eazybytes.beans;

import org.springframework.stereotype.Component;

// this class is defined as a Component
// we don't need to define it as a Bean in our Configuration, if we use @ComponentScan in ProjectConfig.java
@Component
public class Animal {

    public String name;

    public Animal() {}

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("<some sound an Animal makes>");
    }
}
