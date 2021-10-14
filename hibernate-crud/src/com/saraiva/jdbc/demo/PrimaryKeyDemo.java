package com.saraiva.jdbc.demo;

import com.saraiva.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Student student = new Student("John", "Doe", "john@gmail.com");
            Student student1 = new Student("Mary", "Public", "mary@gmail.com");
            Student student2 = new Student("Bonita", "Applebum", "bonita@gmail.com");
            session.beginTransaction();
            session.save(student);
            session.save(student1);
            session.save(student2);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            factory.close();
        }

    }
}
