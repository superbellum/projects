package joinpoints.dao;

import joinpoints.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO
{
    private String name;
    private String code;

    public boolean addSillyMember(Account account, int i, boolean open)
    {
        System.out.println(getClass());

        return true;
    }

    public void goToSleep()
    {
        System.out.println(getClass());
    }

    public String getName()
    {
        System.out.println(getClass() + " getter");
        return name;
    }

    public void setName(String name)
    {
        System.out.println(getClass() + " setter");
        this.name = name;
    }

    public String getCode()
    {
        System.out.println(getClass() + " getter");
        return code;
    }

    public void setCode(String code)
    {
        System.out.println(getClass() + " setter");
        this.code = code;
    }
}
