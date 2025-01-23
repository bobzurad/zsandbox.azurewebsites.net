package com.zurad.java.spring.eazybytes.beans.object.beans.tires;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MichelinTires implements Tires {

    private final String name = "Michelin";

    public String getName() {
        return name;
    }

    public void rotate() {
        System.out.println("Using " + name + " tires.");
    }
}
