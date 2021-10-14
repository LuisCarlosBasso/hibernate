package com.saraiva.jdbc.demo;

import com.saraiva.jdbc.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Course course = new Course("JavaScript");
            Student student = new Student("John", "Doe", "john@live.com");
            Student student1 = new Student("Mary", "Public", "mary@gmail.com");

            session.save(course);

            course.addStudent(student);
            course.addStudent(student1);

            session.save(student);
            session.save(student1);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
            factory.close();
        }

    }
}
