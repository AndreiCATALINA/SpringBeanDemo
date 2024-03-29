package org.example;

import org.example.autowiring.Employee;
import org.example.config.AppConfig;
import org.example.service.EmailService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

      //  SpringApplication.run(Main.class,args);
        System.out.println("Hello world!");
//        EmailService emailService = new EmailService();
//        emailService.sendEmail("Tarom","Ieftiniti biletele!");

        //Putem sa accesam un bean din beans.xml prin intermediul a doua containere: BeanFactory si ApplicationContext
        //1. BeanFactory -> este o interfata predefinita in Spring care nu suporta configurari pe baza de adnotari ci doar pe baza de fisiere xml
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("beans.xml");
        EmailService emailService = beanFactory.getBean("emailService", EmailService.class);
        System.out.println(emailService.toString());
        emailService.sendEmail("Tarom", "Ieftiniti biletele!");

        //2. ApplicationContext -> este o subinterfata a lui BeanFactory care foloseste atat configurari pe baza de adnotari cat si configurari la nivel de xml
        // varianta cu xml
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        EmailService emailService1 = applicationContext.getBean("emailService", EmailService.class);
        System.out.println(emailService1);
        emailService1.sendEmail("Cristi", "Maine ploua.");
        //varianta config din java cu @Bean
        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(AppConfig.class);
        EmailService emailService2 = applicationContext1.getBean("emailServiceBean", EmailService.class);
        System.out.println(emailService2);
        emailService2.sendEmail("Radu", "Mesaj pentru Radu.");

        //testing department and employee beans for autowiring
        ApplicationContext applicationContext3 = new ClassPathXmlApplicationContext("beans.xml");
        Employee employee = applicationContext3.getBean("emp", Employee.class);
        employee.setId("14J");
        employee.setName("Bogdan");
        employee.employeeInfo();
    }

}