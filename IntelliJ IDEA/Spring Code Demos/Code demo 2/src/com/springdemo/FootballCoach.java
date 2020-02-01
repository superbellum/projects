package com.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class FootballCoach implements Coach
{
    @Autowired
    @Qualifier("randomFortuneService")
    private FortuneService fortuneService;

    public FootballCoach()
    {
        //
    }

    @Override
    public String getDailyWorkout()
    {
        return "- football workout";
    }

    @Override
    public String getDailyFortune()
    {
        return fortuneService.getFortune();
    }
}
