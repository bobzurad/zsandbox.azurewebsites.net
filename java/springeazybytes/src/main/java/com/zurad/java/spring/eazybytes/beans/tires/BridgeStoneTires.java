package com.zurad.java.spring.eazybytes.beans.tires;

import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTires implements Tires {

    private String name = "BridgeStone";

    public String getName() {
        return name;
    }

    public void rotate() {
        System.out.println("Using " + name + " tires.");
    }
}
