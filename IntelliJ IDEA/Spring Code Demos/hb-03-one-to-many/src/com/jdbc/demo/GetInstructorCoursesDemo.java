package com.jdbc.demo;

import com.jdbc.entity.Course;
import com.jdbc.entity.Instructor;
import com.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo
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

            Instructor instructor = session.get(Instructor.class, 1);

            System.out.println("Instructor: " + instructor);

            System.out.println("Courses: " + instructor.getCourses());


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
