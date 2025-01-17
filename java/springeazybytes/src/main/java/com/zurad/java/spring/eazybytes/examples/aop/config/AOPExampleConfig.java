package com.zurad.java.spring.eazybytes.examples.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {
    "com.zurad.java.spring.eazybytes.examples.aop.aspects",
    "com.zurad.java.spring.eazybytes.examples.aop.components.*",
    "com.zurad.java.spring.eazybytes.examples.aop.services"
})
@EnableAspectJAutoProxy
public class AOPExampleConfig {

    @Bean
    String name() {
        return "";
    }    // TODO: how do we Autowire without this so we can have our Abstract classes?
}
