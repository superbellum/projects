package aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect
{
    @Before("execution(* add*(..))")
    public void beforeAddAccountAdvise()
    {
        System.out.println("==> @Before");
    }
}
