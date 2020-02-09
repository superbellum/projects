package com.jdbc.demo;

import com.jdbc.entity.Instructor;
import com.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo
{
    public static void main(String[] args)
    {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                     .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();

            Instructor instructor = session.get(Instructor.class, 3);

            System.out.println("Found: " + instructor);

            if (instructor != null)
            {
                // will delete the associated instructor detail because of cascading
                session.delete(instructor);
            }


            session.getTransaction().commit();
        }
        finally
        {
            factory.close();
        }
    }
}
