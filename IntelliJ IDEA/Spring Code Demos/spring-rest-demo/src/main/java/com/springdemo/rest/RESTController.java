package com.springdemo.rest;

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
}
