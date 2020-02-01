package com.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleDemoApp
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanLifecycle_applicationContext.xml");

        Coach coach = context.getBean("coach", Coach.class);

        System.out.println("Memory location coach b: " + coach);

        context.close();
    }
}
