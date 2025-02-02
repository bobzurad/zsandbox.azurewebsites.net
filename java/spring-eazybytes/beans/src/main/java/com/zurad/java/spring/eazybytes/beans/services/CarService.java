package com.zurad.java.spring.eazybytes.beans.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zurad.java.spring.eazybytes.beans.object.beans.speakers.Speakers;
import com.zurad.java.spring.eazybytes.beans.object.beans.tires.Tires;

@Service
public class CarService {

    @Autowired
    private Speakers speakers;

    @Autowired
    private Tires tires;

    public CarService() {
    }

    public Speakers getSpeakers() {
        return speakers;
    }

    public Tires getTires() {
        return tires;
    }
}
