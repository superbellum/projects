package com.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp
{
    public static void main(String[] args)
    {
        // load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieve bean
        Coach coach = context.getBean("coach", Coach.class);

        // method on bean
        System.out.println(coach.getDailyWorkout());
        System.out.println(coach.getDailyFortune());

        // close context
        context.close();
    }
}
