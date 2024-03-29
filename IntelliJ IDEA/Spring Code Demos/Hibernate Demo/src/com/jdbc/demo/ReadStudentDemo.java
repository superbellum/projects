package com.jdbc.demo;

import com.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo
{
    public static void main(String[] args)
    {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();

            Student student = session.get(Student.class, 3);

            System.out.println("\nStudent retrieved: " + student);

            session.getTransaction().commit();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            factory.close();
        }
    }
}
