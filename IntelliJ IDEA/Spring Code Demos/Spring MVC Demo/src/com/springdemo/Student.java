package com.springdemo;

import java.util.LinkedHashMap;

public class Student
{
    private String firstName;
    private String lastName;
    private String country;
    private String favouriteProgrammingLanguage;

    private String[] operatingSystems;

    private LinkedHashMap<String, String> countryOptions;

    public Student()
    {
        countryOptions = new LinkedHashMap<>();

        countryOptions.put("BR", "Brazil");
        countryOptions.put("FR", "France");
        countryOptions.put("US", "United States of America");
        countryOptions.put("UK", "United Kingdom and Northern Ireland");
        countryOptions.put("SK", "Slovakia");
        countryOptions.put("HU", "Hungary");
    }

    public LinkedHashMap<String, String> getCountryOptions()
    {
        return countryOptions;
    }

    public String[] getOperatingSystems()
    {
        return operatingSystems;
    }

    public String getFavouriteProgrammingLanguage()
    {
        return favouriteProgrammingLanguage;
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

    public void setFavouriteProgrammingLanguage(String favouriteProgrammingLanguage)
    {
        this.favouriteProgrammingLanguage = favouriteProgrammingLanguage;
    }

    public void setOperatingSystems(String[] operatingSystems)
    {
        this.operatingSystems = operatingSystems;
    }
}
