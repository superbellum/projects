package pointcut.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO
{
    public boolean addMembershipAccount(int i)
    {
        System.out.println(getClass());

        return true;
    }
}
