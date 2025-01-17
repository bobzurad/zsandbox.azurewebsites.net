package com.zurad.java.spring.eazybytes.examples.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)   // order that the aspect will be triggered
public class VehicleStartCheckAspect {

    @Before("execution(* com.zurad.java.spring.eazybytes.examples.aop.services.*.*(..)) && args(vehicleStarted,..)")
    public void checkVehicleStarted(JoinPoint joinPoint, boolean vehicleStarted) {
        if (!vehicleStarted) {
            throw new RuntimeException("Vehicle not started");
        }
    }
}
