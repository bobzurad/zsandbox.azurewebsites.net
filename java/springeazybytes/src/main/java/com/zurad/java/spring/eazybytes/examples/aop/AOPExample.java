package com.zurad.java.spring.eazybytes.examples.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zurad.java.spring.eazybytes.examples.aop.model.Song;
import com.zurad.java.spring.eazybytes.examples.aop.services.VehicleServices;

public class AOPExample {

    private final AnnotationConfigApplicationContext context;

    public AOPExample(AnnotationConfigApplicationContext context) {
        this.context = context;
    }

    public void run() {
        var vehicleServices = context.getBean(VehicleServices.class);
        System.out.println(vehicleServices.getClass());
        var song = new Song("Off He Goes", "Pearl Jam");
        var vehicleStarted = true;

        vehicleServices.playMusic(vehicleStarted, song);
        vehicleServices.moveVehicle(vehicleStarted);
        vehicleServices.stopVehicle(vehicleStarted);
    }
}
