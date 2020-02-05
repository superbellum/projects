package com.jdbc.demo;

import com.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo
{
    public static void main(String[] args)
    {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try
        {
            Student student = new Student("Patrik", "Gugh", "gugh.patrik@gmail.com");

            session.beginTransaction();

            session.save(student);

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
