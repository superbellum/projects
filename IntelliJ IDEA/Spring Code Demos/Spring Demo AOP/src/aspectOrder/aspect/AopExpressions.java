package aspectOrder.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExpressions
{
    @Pointcut("execution(* aspectOrder.dao.*.*(..))")
    public void forDaoPackage()
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

    @Pointcut("forDaoPackage() && !(getterPointcut() || setterPointcut())")
    public void forDaoPackageNoSetterNoGetter()
    {
        //
    }




    @After("forDaoPackage()")
    public void foo()
    {
        System.out.println("------------------------------------------------------------");
    }
}
