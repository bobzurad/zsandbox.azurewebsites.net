package com.zurad.java.spring.eazybytes;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zurad.java.spring.eazybytes.beans.Vehicle;
import com.zurad.java.spring.eazybytes.config.ProjectConfig;

public class Main {

    public static void main(String[] args) {
        //this Vehicle object was not created from the IoC container
        var notABean = new Vehicle();
        notABean.setName("VW Atlas");
        System.out.println("Vehicle name from non-spring context: " + notABean.getName());

        //create the application context
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        //now we can create beans from the IoC container, which we configured in ProjectConfig.java
        var bean = context.getBean(Vehicle.class);
        System.out.println("Vehicle name from Spring Context: " + bean.getName());

        //more beans from the IoC container
        var hello = context.getBean("hello", String.class);
        var number = context.getBean("number", Integer.class);
        System.out.println("hello String from Spring Context: " + hello);
        System.out.println("number Integer from Spring Context: " + number);
    }
}