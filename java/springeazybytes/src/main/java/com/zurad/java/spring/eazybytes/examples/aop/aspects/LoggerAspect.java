package com.zurad.java.spring.eazybytes.examples.aop.aspects;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)   // order that the aspect will be triggered
public class LoggerAspect {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Around("execution(* com.zurad.java.spring.eazybytes.examples.aop.services.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().toString() + " method execution start");

        Instant start = Instant.now();
        Object returnValue = joinPoint.proceed();
        Instant end = Instant.now();

        long timeElapsed = Duration.between(start, end).toMillis();
        logger.info("method execution time: " + timeElapsed + "ms");
        logger.info(joinPoint.getSignature().toString() + " method execution end");

        return returnValue;
    }
}
