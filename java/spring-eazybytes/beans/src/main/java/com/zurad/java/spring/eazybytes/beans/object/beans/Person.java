package com.zurad.java.spring.eazybytes.beans.object.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.zurad.java.spring.eazybytes.beans.object.Vehicle;

// this class is defined as a Component
// we don't need to define it as a Bean in our Configuration, if we use @ComponentScan in ProjectConfig.java
@Component
public class Person {

    private String name = "Lucy";

    @Autowired() // @Autowired on class fields is not production recommended (can't use final, harder to test)
    private Vehicle vehicle;

    private Vehicle vehicle2;

    private final Vehicle vehicle3;

    // since this is the only constructor, using @Autowired annotation is optional, but good to explicitly show it anyway
    @Autowired
    // @Autowired on constructors is production recommended. Can mark fields as final, and makes this class easier to mock
    public Person(@Qualifier("Rivian") Vehicle vehicle) {
        vehicle3 = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Vehicle getVehicle2() {
        return vehicle2;
    }

    @Autowired // @Autowired on setters is not production recommended, can't use final, harder to test
    @Qualifier("vehicle2")
    public void setVehicle2(Vehicle vehicle) {
        vehicle2 = vehicle;
    }

    public Vehicle getVehicle3() {
        return vehicle3;
    }
}
