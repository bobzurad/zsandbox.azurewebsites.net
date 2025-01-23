package com.zurad.java.spring.eazybytes.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zurad.java.spring.eazybytes.aop.config.AOPConfig;
import com.zurad.java.spring.eazybytes.aop.model.Song;
import com.zurad.java.spring.eazybytes.aop.services.VehicleServices;

public class Main {

    public static void main(String[] args) {
        //create context
        var context = new AnnotationConfigApplicationContext(AOPConfig.class);

        //create and setup VehicleServices
        var vehicleServices = context.getBean(VehicleServices.class);
        System.out.println(vehicleServices.getClass());
        var song = new Song("Off He Goes", "Pearl Jam");
        var vehicleStarted = true;

        //run vehicleServices
        vehicleServices.playMusic(vehicleStarted, song);
        vehicleServices.moveVehicle(vehicleStarted);
        vehicleServices.stopVehicle(vehicleStarted);

        //close context
        context.close();
    }
}
