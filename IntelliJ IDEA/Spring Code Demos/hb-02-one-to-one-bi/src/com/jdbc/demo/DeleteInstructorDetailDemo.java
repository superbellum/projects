package com.jdbc.demo;

import com.jdbc.entity.Instructor;
import com.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo
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

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, 1);


            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);

            session.getTransaction().commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            session.close();

            factory.close();
        }
    }
}
