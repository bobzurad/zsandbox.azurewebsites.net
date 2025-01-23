package com.zurad.java.spring.eazybytes.aop.components.tires;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MichelinTires extends AbstractTires {

    public MichelinTires() {
        super("Michelin");
    }
}
