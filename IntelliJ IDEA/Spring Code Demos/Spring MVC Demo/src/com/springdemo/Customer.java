package com.springdemo;

import javax.validation.constraints.*;

public class Customer
{
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Min(value = 0, message = "Must be greater than or equal to 0")
    @Max(value = 10, message = "Must be less than or equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "5 chars/nums")
    private String postalCode;

    public String getPostalCode()
    {
        return postalCode;
    }

    public Integer getFreePasses()
    {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses)
    {
        this.freePasses = freePasses;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }
}
