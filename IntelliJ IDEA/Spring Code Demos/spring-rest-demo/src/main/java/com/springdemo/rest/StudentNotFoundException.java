package com.springdemo.rest;

public class StudentNotFoundException extends RuntimeException
{
    public StudentNotFoundException(String message)
    {
        super(message);
    }

    public StudentNotFoundException(Throwable cause)
    {
        super(cause);
    }
}
