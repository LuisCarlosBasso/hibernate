package com.saraiva.jdbc.demo;

import com.saraiva.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Student otherStudent = session.get(Student.class, 5);
            System.out.println("Retrived student: " + otherStudent.toString());
            otherStudent.setFirstName("Scooby");
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            factory.close();
        }

    }
}
