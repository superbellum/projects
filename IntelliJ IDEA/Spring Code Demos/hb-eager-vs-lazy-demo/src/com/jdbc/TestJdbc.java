package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc
{
    public static void main(String[] args)
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
        String user = "hbstudent";
        String password = "hbstudent";

        //String jdbcUrl = "jdbc:mysql://e112339-mysql.services.easyname.eu:3306/u175243db1";
        //String user = "u175243db1";
        //String password = "database";

        try
        {
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);



            System.out.println("Connection successful !");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
