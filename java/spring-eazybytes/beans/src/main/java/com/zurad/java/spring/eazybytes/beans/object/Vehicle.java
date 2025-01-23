package com.zurad.java.spring.eazybytes.beans.object;

public class Vehicle {

    private String name;

    public Vehicle() {
    }

    public Vehicle(String name) {
        this.name = name;
    }

    // this is only used by the bean defined in beans.xml
    private void postConstruct() {
        System.out.println("Hello from Vehicle.postConstruct()");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // this is only used by the bean defined in beans.xml
    private void preDestroy() {
        System.out.println("Hello from Vehicle.preDestroy()");
    }
}
