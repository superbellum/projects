package com.springdemo;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomFortuneService implements FortuneService
{
    private String[] data = {

            "random 1",
            "random 2",
            "random 3"
    };

    @Override
    public String getFortune()
    {
        return data[new Random().nextInt(data.length)];
    }
}
