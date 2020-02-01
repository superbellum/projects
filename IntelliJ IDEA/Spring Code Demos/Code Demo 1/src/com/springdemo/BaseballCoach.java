package com.springdemo;

public class BaseballCoach implements Coach
{
    private FortuneService fortuneService;

    public BaseballCoach(FortuneService fortuneService)
    {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyFortune()
    {
        return fortuneService.getFortune();
    }

    @Override
    public String getDailyWorkout()
    {
        return "- baseball workout";
    }
}
