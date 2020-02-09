package com.jdbc.demo;

import com.jdbc.entity.Instructor;
import com.jdbc.entity.InstructorDetail;
import com.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo
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
            //Instructor instructor = new Instructor("Patrik", "Gugh", "gugh.patrik@email.com");

            //InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code !");

            Instructor instructor = new Instructor("Balazs", "Nagy", "nagy.balazs@email.com");

            InstructorDetail instructorDetail = new InstructorDetail("http://www.balazsnagy.com/youtube", "Machine Learning !");

            instructor.setInstructorDetail(instructorDetail);


            session.beginTransaction();

            session.save(instructor); // this saves instructor BUT also intructorDetail, because of cascading


            session.getTransaction().commit();
        }
        finally
        {
            factory.close();
        }
    }
}
