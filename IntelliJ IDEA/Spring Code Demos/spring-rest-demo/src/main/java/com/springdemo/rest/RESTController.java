package com.springdemo.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class RESTController
{
    @GetMapping("/hello")
    public String sayhello()
    {
        return "Hello World!";
    }





    @GetMapping("/assets")
    public String fun() throws UnirestException
    {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://api.coincap.io/v2/assets").asString();

        return response.getBody().toString();
    }
}
