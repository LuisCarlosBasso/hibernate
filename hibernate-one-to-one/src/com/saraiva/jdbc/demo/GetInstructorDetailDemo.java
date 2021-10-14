package com.saraiva.jdbc.demo;

import com.saraiva.jdbc.entity.Instructor;
import com.saraiva.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, 1);
            System.out.println("Os detalhes: " + instructorDetail);
            System.out.println("O instrutor: " + instructorDetail.getInstructor());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            factory.close();
        }

    }
}
