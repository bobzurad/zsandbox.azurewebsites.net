package com.zurad.java.spring.eazybytes.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// this bean is lazy, so it won't into the Spring context until it's first being used

@Component
@Lazy
public class LazyBean {

    public LazyBean() {
        System.out.println("Creating LazyBean");
    }
}
