package com.zurad.java.spring.eazybytes.examples.aop.components.tires;

public class AbstractTires implements Tires {

    private final String name;

    public AbstractTires(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void rotate() {
        System.out.println("Vehicle moving with help of " + name + " tires.");
    }

    @Override
    public void stop() {
        System.out.println("Vehicle stopping with help of " + name + " tires.");
    }
}
