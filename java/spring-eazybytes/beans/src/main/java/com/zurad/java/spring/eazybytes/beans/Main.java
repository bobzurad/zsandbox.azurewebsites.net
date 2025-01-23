package com.zurad.java.spring.eazybytes.beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zurad.java.spring.eazybytes.beans.object.Vehicle;

public class Main {

    public static void main(String[] args) {
        // this Vehicle object was not created from the IoC container
        var notABean = new Vehicle();
        notABean.setName("VW Atlas");
        System.out.println("Vehicle name from non-spring context: " + notABean.getName());

        // create the application context for Bean examples
        var context = new AnnotationConfigApplicationContext(com.zurad.java.spring.eazybytes.beans.config.BeanConfig.class);

        // now we can create beans from the IoC container, which we configured in ProjectConfig.java
        // we use the BeansExample object to load and run the beans.
        runBeanExamples(new BeanExamples(context));

        // close context
        context.close();
    }

    private static void runBeanExamples(BeanExamples beanExamples) {
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

        // create lazy beans
        beanExamples.lazyBeans();

        // show that by default, beans are singletons
        beanExamples.singletonBeans();

        // show an example of prototype scope bean
        beanExamples.prototypeScopeBeans();
    }
}
