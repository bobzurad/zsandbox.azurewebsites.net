package com.zurad.java.spring.eazybytes.examples.aop.services;

import org.springframework.stereotype.Component;

import com.zurad.java.spring.eazybytes.examples.aop.aspects.LogAspect;
import com.zurad.java.spring.eazybytes.examples.aop.components.speakers.Speakers;
import com.zurad.java.spring.eazybytes.examples.aop.components.tires.Tires;
import com.zurad.java.spring.eazybytes.examples.aop.model.Song;

@Component
public class VehicleServices {

    private final Speakers speakers;
    private final Tires tires;

    public VehicleServices(Speakers speakers, Tires tires) {
        this.speakers = speakers;
        this.tires = tires;
    }

    @LogAspect
    public void playMusic(boolean vehicleStarted, Song song) {
        speakers.makeSound(song);
    }

    public void moveVehicle(boolean vehicleStarted) {
        tires.rotate();
    }

    public void stopVehicle(boolean vehicleStopped) {
        tires.stop();
    }
}
