package com.zurad.java.spring.eazybytes.examples.aop.components.speakers;

import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers extends AbstractSpeakers {

    public BoseSpeakers(String name) {
        super(name);
    }
}
