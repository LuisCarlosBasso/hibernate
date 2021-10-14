package com.saraiva.jdbc.demo;

import com.saraiva.jdbc.entity.Instructor;
import com.saraiva.jdbc.entity.InstructorDetail;
import com.saraiva.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Instructor instructor = new Instructor("Luis", "Saraiva", "lucarbass@live.com");
            InstructorDetail detail = new InstructorDetail("www.youtube.com", "playing games");
            instructor.setInstructorDetail(detail);

            session.beginTransaction();
            session.save(instructor);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            factory.close();
        }

    }
}
