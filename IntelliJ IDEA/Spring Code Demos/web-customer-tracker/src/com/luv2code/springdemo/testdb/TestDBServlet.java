package com.luv2code.springdemo.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "TestDBServlet", value = "/TestDBServlet")
public class TestDBServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        String user = "springstudent";
        String pass = "springstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
        String driver = "com.mysql.cj.jdbc.Driver";

        try
        {
            PrintWriter out = response.getWriter();

            out.println("Connecting to database: " + jdbcUrl);

            Class.forName(driver);

            Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

            out.println("\nConnection successful.");

            conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
