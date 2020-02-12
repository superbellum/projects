package around;

import around.dao.AccountDAO;
import around.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AroundHandleExceptionDemoApp
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("Calling traffic fortune service...");


        try
        {
            System.out.println(trafficFortuneService.getFortune(true));
        }
        catch (Exception e)
        {
            System.out.println("Exc: " + e);
        }



        context.close();
    }
}
