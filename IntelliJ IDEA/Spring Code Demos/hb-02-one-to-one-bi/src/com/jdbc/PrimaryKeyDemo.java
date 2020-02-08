package com.jdbc;

import com.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo
{
    public static void main(String[] args)
    {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try
        {
            Student student1 = new Student("Patrik", "Gugh", "gugh.patrik@gmail.com");
            Student student2 = new Student("Balazs", "Nagy", "nagy.balazs@gmail.com");
            Student student3 = new Student("Zoltan", "Meszaros", "meszaros.zoltan@gmail.com");

            session.beginTransaction();

            session.save(student1);
            session.save(student2);
            session.save(student3);

            session.getTransaction().commit();

            System.out.println("\nSuccess !\n");
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
