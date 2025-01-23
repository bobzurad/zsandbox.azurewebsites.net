package com.zurad.java.spring.eazybytes.aop.aspects;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)   // order that the aspect will be triggered
public class LoggerAspect {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Around("execution(* com.zurad.java.spring.eazybytes.aop.services.*.*(..))")
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

    @Around("@annotation(com.zurad.java.spring.eazybytes.aop.aspects.LogAspect)")
    public Object logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().toString() + " method execution start (annotation)");

        Instant start = Instant.now();
        Object returnValue = joinPoint.proceed();
        Instant end = Instant.now();

        long timeElapsed = Duration.between(start, end).toMillis();
        logger.info("method execution time (annotation): " + timeElapsed + "ms");
        logger.info(joinPoint.getSignature().toString() + " method execution end (annotation)");

        return returnValue;
    }

    @AfterThrowing(value = "execution(* com.zurad.java.spring.eazybytes.aop.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        logger.severe(joinPoint.getSignature().toString() + ": exception was thrown in @AfterThrowing with message: "
            + ex.getMessage());
    }

    @AfterReturning(value = "execution(* com.zurad.java.spring.eazybytes.aop.services.*.*(..))", returning = "returnValue")
    public void logStatus(JoinPoint joinPoint, Object returnValue) {
        logger.info(joinPoint.getSignature().toString() + " method processed successfully with return value: "
            + (returnValue == null ? "null" : returnValue.toString()));
    }
}
