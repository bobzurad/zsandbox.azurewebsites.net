package com.zurad.java.spring.eazybytes.beans.object.beans.speakers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SonySpeakers implements Speakers {

    private final String name = "Sony";

    public String getName() {
        return name;
    }

    public void makeSound() {
        System.out.println("Sound coming from " + name + " speakers");
    }
}
