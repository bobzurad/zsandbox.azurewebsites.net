package com.zurad.java.spring.eazybytes.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {
    "com.zurad.java.spring.eazybytes.aop.aspects",
    "com.zurad.java.spring.eazybytes.aop.components.*",
    "com.zurad.java.spring.eazybytes.aop.services"
})
@EnableAspectJAutoProxy
public class AOPConfig {

}
