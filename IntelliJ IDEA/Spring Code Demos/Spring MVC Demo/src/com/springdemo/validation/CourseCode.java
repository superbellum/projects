package com.springdemo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode
{
    // default value
    String value() default "LUV";

    // default error message
    String message() default "must start with LUV";

    // default groups
    public Class<?>[] groups() default {};


    // default payload
    public Class<? extends Payload>[] payload() default {};
}
