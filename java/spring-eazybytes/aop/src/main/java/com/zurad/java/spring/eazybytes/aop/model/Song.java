package com.zurad.java.spring.eazybytes.aop.model;

public class Song {

    private final String title;
    private final String singerName;

    public Song(String title, String singerName) {
        this.title = title;
        this.singerName = singerName;
    }

    public String getTitle() {
        return title;
    }

    public String getSingerName() {
        return singerName;
    }
}