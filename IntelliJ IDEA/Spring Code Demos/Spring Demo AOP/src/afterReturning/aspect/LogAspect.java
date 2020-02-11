package afterReturning.aspect;

import afterReturning.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Order(2)
@Component
public class LogAspect
{
    @AfterReturning(pointcut = "execution(* afterReturning.dao.AccountDAO.findAccounts(..))", returning = "result")
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
