package com.saraiva.jdbc.demo;

import com.saraiva.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // all students
            List<Student> students = session.createQuery("from Student").getResultList();
            students.forEach(System.out::println);

            // last name = Doe
            students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
            students.forEach(System.out::println);

            // last name = Doe OR first name is Daffy
            students = session.createQuery("from Student s where s.lastName='Doe' or s.firstName='Daffy'").getResultList();
            students.forEach(System.out::println);

            // email like '%gmail.com'
            students = session.createQuery("from Student  s where s.email like '%gmail.com'").getResultList();
            students.forEach(System.out::println);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            factory.close();
        }

    }
}
