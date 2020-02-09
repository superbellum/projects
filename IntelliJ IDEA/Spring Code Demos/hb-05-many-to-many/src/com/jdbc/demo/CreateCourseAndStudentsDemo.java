package com.jdbc.demo;

import com.jdbc.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo
{
    public static void main(String[] args)
    {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .addAnnotatedClass(Course.class)
                                                    .addAnnotatedClass(Review.class)
                                                    .addAnnotatedClass(Student.class)
                                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();

            Course course = new Course("Pac - man");

            session.save(course);
            System.out.println("Course saved: " + course);


            Student student = new Student("Xx", "Yy", "xx.yy@email.com");
            Student student1 = new Student("Mary", "Public", "mary.public@email.com");


            course.addStudent(student);
            course.addStudent(student1);


            session.save(student);
            session.save(student1);

            System.out.println("Students saved: " + course.getStudents());


            session.getTransaction().commit();

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
