package com.springdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>
{
    private String coursePrefix;

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext)
    {

        boolean result;

        if (code != null)
        {
            return code.startsWith(coursePrefix);
        }
        else return false;
    }

    @Override
    public void initialize(CourseCode courseCode)
    {
        coursePrefix = courseCode.value();
    }
}
