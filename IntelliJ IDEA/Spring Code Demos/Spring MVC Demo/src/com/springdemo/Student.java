package com.springdemo;


public class Student
{
    private String firstName;
    private String lastName;
    private String country;

    public Student()
    {
        //
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getCountry()
    {
        return country;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}
