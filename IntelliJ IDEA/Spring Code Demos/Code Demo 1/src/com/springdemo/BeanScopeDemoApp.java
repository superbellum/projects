package com.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope_applicationContext.xml");

        Coach a = context.getBean("coach", Coach.class);

        Coach b = context.getBean("coach", Coach.class);

        boolean result = (a == b);

        System.out.println("result = " + result);
        System.out.println("Memory location coach a: " + a);
        System.out.println("Memory location coach b: " + b);

        context.close();
    }
}
