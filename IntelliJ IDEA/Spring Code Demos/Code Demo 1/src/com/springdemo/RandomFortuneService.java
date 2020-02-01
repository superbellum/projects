package com.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService
{
    private String[] randomFortunes = {

            "random fortune 1",
            "random fortune 2",
            "random fortune 3"
    };

    @Override
    public String getFortune()
    {
        return randomFortunes[new Random().nextInt(randomFortunes.length)];
    }
}
