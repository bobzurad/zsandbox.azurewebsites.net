package com.zurad.java.spring.eazybytes.beans.object;

import org.springframework.beans.factory.annotation.Autowired;

import com.zurad.java.spring.eazybytes.beans.services.CarService;

public class Car {

    private final String name;

    @Autowired
    private CarService carService;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void makeSound() {
        carService.getSpeakers().makeSound();
    }

    public void go() {
        carService.getTires().rotate();
    }
}
