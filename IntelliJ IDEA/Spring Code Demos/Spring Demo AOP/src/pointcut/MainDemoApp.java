package pointcut;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pointcut.dao.AccountDAO;
import pointcut.dao.MembershipDAO;

public class MainDemoApp
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        accountDAO.addSillyMember();
        accountDAO.goToSleep();
        accountDAO.getCode();
        accountDAO.getName();

        //membershipDAO.addMembershipAccount(9);

        context.close();
    }
}
