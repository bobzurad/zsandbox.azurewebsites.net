package com.zurad.java.spring.eazybytes.examples.aop.components.speakers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SonySpeakers extends AbstractSpeakers {

    public SonySpeakers() {
        super("Sony");
    }
}
