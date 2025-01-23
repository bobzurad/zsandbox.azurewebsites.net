package com.zurad.java.spring.eazybytes.beans.object.beans.tires;

import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTires implements Tires {

    private final String name = "BridgeStone";

    public String getName() {
        return name;
    }

    public void rotate() {
        System.out.println("Using " + name + " tires.");
    }
}
