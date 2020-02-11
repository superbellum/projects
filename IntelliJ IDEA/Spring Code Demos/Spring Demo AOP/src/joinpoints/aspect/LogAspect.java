package joinpoints.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class LogAspect
{
    @Before("joinpoints.aspect.AopExpressions.forDaoPackageNoSetterNoGetter()")
    public void beforeAddAccountAdvise(JoinPoint joinPoint)
    {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);
        for (Object o : joinPoint.getArgs())
        {
            System.out.println("Argument: " + o);
        }





        System.out.println("==> @Before: log");
    }
}
