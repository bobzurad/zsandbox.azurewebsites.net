package com.zurad.java.spring.eazybytes.beans.speakers;

import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers {

    private String name = "Bose";

    public String getName() {
        return name;
    }

    public void makeSound() {
        System.out.println("Sound coming from " + name + " speakers");
    }
}
