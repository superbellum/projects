package com.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HockeyCoach implements Coach
{
    private FortuneService fortuneService;

    public HockeyCoach()
    {
        //
    }

    @Override
    public String getDailyFortune()
    {
        return fortuneService.getFortune();
    }

    @Override
    public String getDailyWorkout()
    {
        return "- hockey workout";
    }

    @Autowired
    public void setFortuneService(@Qualifier("RESTFortuneService") FortuneService fortuneService)
    {
        this.fortuneService = fortuneService;
    }
}
