package com.luv2code.springsecurity.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.luv2code.springsecurity")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig
{
    private Environment environment;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public AppConfig(Environment environment)
    {
        this.environment = environment;
    }

    // view resolver
    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    // security data source
    @Bean
    public DataSource securityDataSource()
    {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try
        {
            dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        }
        catch (PropertyVetoException e)
        {
            throw new RuntimeException(e);
        }

        logger.info(">>>> jdbc.url  = " + environment.getProperty("jdbc.url"));
        logger.info(">>>> jdbc.user = " + environment.getProperty("jdbc.user"));

        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));

        dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return dataSource;
    }

    private int getIntProperty(String propName)
    {
        String propVal = environment.getProperty(propName);

        assert propVal != null;
        return Integer.parseInt(propVal);
    }
}
