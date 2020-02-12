package around.dao;

import around.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<Account> findAccounts(boolean tripWire)
    {
        if (tripWire)
        {
            throw new RuntimeException("No soup for you!");
        }




        List<Account> accounts = new ArrayList<>();

        Account account = new Account("Patrik", "Gold");
        Account account1 = new Account("Alex", "Silver");
        Account account2 = new Account("Geza", "Platinum");

        accounts.add(account);
        accounts.add(account1);
        accounts.add(account2);

        return accounts;
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
