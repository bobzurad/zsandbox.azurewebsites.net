package com.zurad.java.spring.eazybytes.aop.components.speakers;

import com.zurad.java.spring.eazybytes.aop.model.Song;

public class AbstractSpeakers implements Speakers {

    private final String name;

    public AbstractSpeakers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void makeSound(Song song) {
        System.out.println(
            "Playing " + song.getTitle() + " by " + song.getSingerName() + " with " + name + " speakers");
    }
}
