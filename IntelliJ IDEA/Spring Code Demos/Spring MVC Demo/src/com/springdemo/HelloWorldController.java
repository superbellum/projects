package com.springdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hello")
public class HelloWorldController
{
    // html form
    @RequestMapping("/showForm")
    public String showForm()
    {
        return "helloworld-form";
    }

    // process form
    @RequestMapping("/processFormVersion1")
    public String processForm()
    {
        return "helloworld";
    }


    // controller data + model
    @RequestMapping("/processFormVersion2")
    public String letsShoutDude(HttpServletRequest request, Model model)
    {
        String theName = request.getParameter("studentName");

        theName = theName.toUpperCase();

        String result = "Yo! " + theName;

        model.addAttribute("message", result);

        return "helloworld";
    }

    // controller data + model
    @RequestMapping("/processFormVersion3")
    public String letsShoutDude(@RequestParam("studentName") String theName, Model model)
    {
        theName = theName.toUpperCase();

        String result = "v3: " + theName;

        model.addAttribute("message", result);

        return "helloworld";
    }
}
