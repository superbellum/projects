package com.springdemo;

public class TrackCoach implements Coach
{
    private FortuneService fortuneService;

    public TrackCoach()
    {
        //
    }

    public TrackCoach(FortuneService fortuneService)
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
        return "- track workout";
    }
}
