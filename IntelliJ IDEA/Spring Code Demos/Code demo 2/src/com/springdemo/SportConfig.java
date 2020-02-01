package com.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// @ComponentScan("com.springdemo")
@PropertySource("classpath:sport.properties")
public class SportConfig
{
    // define bean for service
    @Bean
    public FortuneService sadFortuneService()
    {
        return new SadFortuneService();
    }

    // define bean for swim coach
    @Bean
    public Coach swimCoach()
    {
        return new SwimCoach(sadFortuneService());
    }
}
