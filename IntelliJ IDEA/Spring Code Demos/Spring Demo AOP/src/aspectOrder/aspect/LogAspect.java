package aspectOrder.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class LogAspect
{
    @Before("aspectOrder.aspect.AopExpressions.forDaoPackageNoSetterNoGetter()")
    public void beforeAddAccountAdvise()
    {
        System.out.println("==> @Before: log");
    }
}
