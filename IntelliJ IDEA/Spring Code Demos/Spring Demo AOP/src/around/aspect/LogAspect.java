package around.aspect;

import around.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Order(2)
@Component
public class LogAspect
{
    @Around("execution(* around.service.*.getFortune(..))")
    public Object aroundFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        String method = proceedingJoinPoint.getSignature().toShortString();

        System.out.println("==> @Around : " + method);

        long begin = System.currentTimeMillis();

        Object result = null;

        try
        {
            result = proceedingJoinPoint.proceed();
        }
        catch (Exception e)
        {
            //e.printStackTrace();

            result = "==> @Around: Exception handled: " + e;

            throw e;
        }


        long end = System.currentTimeMillis();

        long duration = end - begin;
        System.out.println("Duration: " + duration / 1000.0 + " seconds");

        return result;
    }


    @After("execution(* around.dao.AccountDAO.findAccounts(..))")
    public void afterFindaccounts(JoinPoint joinPoint)
    {
        String method = joinPoint.getSignature().toShortString();

        System.out.println("==> @After : " + method);
    }



    @AfterThrowing(pointcut = "execution(* around.dao.AccountDAO.findAccounts(..))", throwing = "exception")
    public void afterThrowingFindAccounts(JoinPoint joinPoint, Exception exception)
    {
        String method = joinPoint.getSignature().toShortString();

        System.out.println("==> @AfterThrowing : " + method);

        System.out.println("==> Exception handled: " + exception);
    }

    @AfterReturning(pointcut = "execution(* around.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccounts(JoinPoint joinPoint, List<Account> result)
    {
        String method = joinPoint.getSignature().toShortString();

        System.out.println("==> @AfterReturning : " + method);

        System.out.println("==> Cought: " + result);

        convertToUpperCase(result);
    }

    private void convertToUpperCase(List<Account> result)
    {
        for (Account account : result)
        {
            account.setName(account.getName().toUpperCase());

            account.setLevel(account.getLevel().toUpperCase());
        }
    }
}
