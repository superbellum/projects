package joinpoints;

import joinpoints.dao.AccountDAO;
import joinpoints.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        accountDAO.addSillyMember(new Account("Patrik account", "high level"), 3, false);
        //accountDAO.goToSleep();
        //accountDAO.getCode();
        //accountDAO.getName();

        //membershipDAO.addMembershipAccount(9);

        context.close();
    }
}
