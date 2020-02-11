package joinpoints.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class CloudAspect
{
    @Before("joinpoints.aspect.AopExpressions.forDaoPackageNoSetterNoGetter()")
    public void cloudAsync()
    {
        System.out.println("==> @Before: cloud");
    }
}
