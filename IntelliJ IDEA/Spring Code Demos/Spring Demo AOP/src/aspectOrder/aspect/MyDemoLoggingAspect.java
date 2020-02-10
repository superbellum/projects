package aspectOrder.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect
{
    @Pointcut("execution(* aspectOrder.dao.*.*(..))")
    private void forDAOPackage()
    {
        //
    }

    @Pointcut("execution(* aspectOrder.dao.*.get*(..))")
    public void getterPointcut()
    {
        //
    }

    @Pointcut("execution(* aspectOrder.dao.*.set*(..))")
    public void setterPointcut()
    {
        //
    }

    @Pointcut("forDAOPackage() && !(getterPointcut() || setterPointcut())")
    public void forDaoPackageNoSetterNoGetter()
    {
        //
    }


    @Before("forDaoPackageNoSetterNoGetter()")
    public void beforeAddAccountAdvise()
    {
        System.out.println("==> @Before");
    }

    @Before("forDaoPackageNoSetterNoGetter()")
    public void analytics()
    {
        System.out.println("==> @Before 2");
    }


    @After("forDAOPackage()")
    public void foo()
    {
        System.out.println("------------------------------------------------------------");
    }
}
