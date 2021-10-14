package com.saraiva.jdbc.demo;

import com.saraiva.jdbc.entity.Course;
import com.saraiva.jdbc.entity.Instructor;
import com.saraiva.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, 1);
            Course course = new Course("Spring");
            Course course1 = new Course("ReactJs");
            Course course2 = new Course("JavaScript");
            instructor.add(course);
            instructor.add(course1);
            instructor.add(course2);
            session.save(course);
            session.save(course1);
            session.save(course2);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
            factory.close();
        }

    }
}
