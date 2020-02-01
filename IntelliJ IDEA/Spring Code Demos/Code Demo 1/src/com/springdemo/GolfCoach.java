package com.springdemo;

public class GolfCoach implements Coach
{
    @Override
    public String getDailyFortune()
    {
        return null;
    }

    @Override
    public String getDailyWorkout()
    {
        return "- golf workout";
    }
}
