package com.zurad.java.spring.eazybytes.examples.aop.components.tires;

import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTires extends AbstractTires {

    public BridgeStoneTires(String name) {
        super(name);
    }
}
