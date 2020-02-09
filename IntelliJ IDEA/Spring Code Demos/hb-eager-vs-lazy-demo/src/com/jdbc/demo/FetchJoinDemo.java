package com.jdbc.demo;

import com.jdbc.entity.Course;
import com.jdbc.entity.Instructor;
import com.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo
{
    public static void main(String[] args)
    {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .addAnnotatedClass(Course.class)
                                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();

            int theId = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i join fetch i.courses where i.id=:theInstructorId", Instructor.class);

            query.setParameter("theInstructorId", theId);

            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor: " + instructor);

            //System.out.println("Courses: " + instructor.getCourses());

            session.getTransaction().commit();

            session.close();

            System.out.println("Session closed...");

            System.out.println("Courses: " + instructor.getCourses());

            System.out.println("Done !");

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
