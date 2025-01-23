package com.zurad.java.spring.eazybytes.aop.services;

import org.springframework.stereotype.Service;

import com.zurad.java.spring.eazybytes.aop.aspects.LogAspect;
import com.zurad.java.spring.eazybytes.aop.components.speakers.Speakers;
import com.zurad.java.spring.eazybytes.aop.components.tires.Tires;
import com.zurad.java.spring.eazybytes.aop.model.Song;

@Service    // there is no functional difference between @Service and @Convention. @Service is provided for convention
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
