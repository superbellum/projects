package aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO
{
    public boolean addMembershipAccount()
    {
        System.out.println(getClass());

        return true;
    }
}
