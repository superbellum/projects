package around.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TrafficFortuneService
{
    public String getFortune(boolean tripWire)
    {
        if (tripWire)
        {
            throw new RuntimeException("Fortune service runtime exception thrown.");
        }

        try
        {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return "Heavy traffic expected.";
    }
}
