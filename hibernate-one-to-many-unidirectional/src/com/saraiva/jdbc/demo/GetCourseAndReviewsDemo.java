package com.saraiva.jdbc.demo;

import com.saraiva.jdbc.entity.Course;
import com.saraiva.jdbc.entity.Instructor;
import com.saraiva.jdbc.entity.InstructorDetail;
import com.saraiva.jdbc.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Course course = session.get(Course.class, 10);
            System.out.println("Curso: " + course);
            System.out.println("Reviews: " + course.getReviews());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
            factory.close();
        }

    }
}
