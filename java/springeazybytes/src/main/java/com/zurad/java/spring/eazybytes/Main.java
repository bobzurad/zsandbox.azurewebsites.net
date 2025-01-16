package com.zurad.java.spring.eazybytes;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zurad.java.spring.eazybytes.beans.Vehicle;
import com.zurad.java.spring.eazybytes.config.ProjectConfig;

public class Main {

    public static void main(String[] args) {
        // this Vehicle object was not created from the IoC container
        var notABean = new Vehicle();
        notABean.setName("VW Atlas");
        System.out.println("Vehicle name from non-spring context: " + notABean.getName());

        // create the application context
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        // now we can create beans from the IoC container, which we configured in ProjectConfig.java
        // we use the BeansExample object to load and run the beans.
        var beanExamples = new BeanExamples(context);

        // create some beans
        beanExamples.beans();

        // create our components that were registered as beans with @ComponentScan in ProjectConfig.java
        beanExamples.components();

        // create beans using a Supplier
        beanExamples.supplierBeans();

        // create beans from the XmlContext
        beanExamples.xmlBeans();

        // run our services
        beanExamples.services();

        // close context
        context.close();
    }
}
