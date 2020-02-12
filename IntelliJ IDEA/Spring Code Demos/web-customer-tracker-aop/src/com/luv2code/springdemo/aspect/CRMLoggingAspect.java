package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect
{
    private Logger logger = Logger.getLogger(getClass().getName());


    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}


    // @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint)
    {
        // display method
        String method = joinPoint.getSignature().toShortString();
        logger.info("----> @Before method: " + method);

        // display arguments
        Object[] args = joinPoint.getArgs();

        for (Object arg : args)
        {
            logger.info("--------> Arg: " + arg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result)
    {
        // display method
        String method = joinPoint.getSignature().toShortString();
        logger.info("----> @AfterRetuning method: " + method);

        // display args
        logger.info("--------> Result: " + result);
    }
}
