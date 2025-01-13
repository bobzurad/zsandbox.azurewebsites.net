package com.zurad.java.spring.eazybytes;

import java.util.function.Supplier;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zurad.java.spring.eazybytes.beans.Animal;
import com.zurad.java.spring.eazybytes.beans.Car;
import com.zurad.java.spring.eazybytes.beans.Person;
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
        beans(context);

        // create our components that were registered as beans with @ComponentScan in ProjectConfig.java
        components(context);

        // create beans using a Supplier
        supplierBeans(context);

        // create beans from the XmlContext
        xmlBeans();

        // run our services
        services(context);

        // close context
        context.close();
    }

    private static void beans(AnnotationConfigApplicationContext context) {
        // we have to specify the bean name because we have more than one bean of type Vehicle defined
        var bean = context.getBean("vehicle", Vehicle.class);
        System.out.println("Vehicle name from Spring Context: " + bean.getName());

        // this will throw a NoUniqueBeanDefinitionException because we have more than one bean of type Vehicle defined
        try {
            var whichBean = context.getBean(Vehicle.class);
            System.out.println("THIS VEHICLE BEAN SHOULD NOT HAVE BEEN RETRIEVED!!!!!: "
                    + whichBean.getName());
        } catch (NoUniqueBeanDefinitionException ex) {
            System.out.println("Caught the following exception: " + ex.getMessage());
        }

        // more beans from the IoC container
        var helloBean = context.getBean("hello", String.class); // we have more than one bean of type String defined
        System.out.println("hello String from Spring Context: " + helloBean);
        var numberBean = context.getBean(Integer.class);
        System.out.println("number Integer from Spring Context: " + numberBean);

        // named bean
        var rivianBean = context.getBean("Rivian", Vehicle.class);
        System.out.println("Vehicle name from Rivian bean: " + rivianBean.getName());

        // primary bean, so don't need to specify it's name
        var goodbyeBean = context.getBean(String.class);
        System.out.println("primary String bean: " + goodbyeBean);
    }

    private static void components(AnnotationConfigApplicationContext context) {
        var dogBean = context.getBean(Animal.class);
        System.out.println("Animal name from dog bean: " + dogBean.getName());
        dogBean.printHello();

        var lucyBean = context.getBean(Person.class);
        System.out.println("Person name from lucy bean: " + lucyBean.getName());
        System.out.println(
                "Autowired Vehicle bean name from lucy bean: " + lucyBean.getVehicle().getName());
        System.out.println(
                "Autowired Vehicle2 bean name from lucy bean: " + lucyBean.getVehicle2().getName());
        System.out.println(
                "Autowired Vehicle3 bean name from lucy bean: " + lucyBean.getVehicle3().getName());
    }

    private static void supplierBeans(AnnotationConfigApplicationContext context) {
        Supplier<Vehicle> subaruSupplier = () -> {
            return new Vehicle("Subaru WRX");
        };

        context.registerBean("Subaru", Vehicle.class, subaruSupplier);

        var subaruBean = context.getBean("Subaru", Vehicle.class);
        System.out.println("Vehicle name from Subaru bean: " + subaruBean.getName());
    }

    private static void xmlBeans() {
        var context = new ClassPathXmlApplicationContext("beans.xml");

        var toyotaBean = context.getBean(Vehicle.class);
        System.out.println("Vehicle name from Toyota bean: " + toyotaBean.getName());

        context.close();
    }

    private static void services(AnnotationConfigApplicationContext context) {
        var car = context.getBean(Car.class);

        System.out.println("Car name from car bean: " + car.getName());
        car.makeSound();
        car.go();
    }
}
