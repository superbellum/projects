package joinpoints.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(3)
@Component
public class AnalyticsAspect
{
    @Before("joinpoints.aspect.AopExpressions.forDaoPackageNoSetterNoGetter()")
    public void analytics()
    {
        System.out.println("==> @Before: analytics");
    }
}
