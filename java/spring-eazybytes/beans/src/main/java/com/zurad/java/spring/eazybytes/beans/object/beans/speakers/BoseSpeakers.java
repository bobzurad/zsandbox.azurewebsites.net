package com.zurad.java.spring.eazybytes.beans.object.beans.speakers;

import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers {

    private final String name = "Bose";

    public String getName() {
        return name;
    }

    public void makeSound() {
        System.out.println("Sound coming from " + name + " speakers");
    }
}
