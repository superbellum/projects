package after;

import after.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterDemoApp
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        List<Account> accounts = null;

        try
        {

            accounts = accountDAO.findAccounts(true);

            System.out.println("main app: Accounts found: " + accounts);

        }
        catch (Exception e)
        {
            System.out.println("Exception caught: " + e);
        }

        context.close();
    }
}
