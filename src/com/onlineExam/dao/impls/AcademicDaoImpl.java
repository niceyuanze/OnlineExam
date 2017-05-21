package com.onlineExam.dao.impls;

import com.onlineExam.pojo.persons.Course;
import com.onlineExam.pojo.persons.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by niceyuanze on 16-12-26.
 */
@Component
public class AcademicDaoImpl {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Teacher> getTeachers(){
        String hql = "from  Teacher ";
        Session session = sessionFactory.openSession();
        List<Teacher> teachers = session.createQuery(hql).list();
        session.close();
        return teachers;

    }

    public Teacher getTeacher( int id ) {
        Session session = sessionFactory.openSession();

        Teacher teacher = (Teacher) session.get(Teacher.class, id);
        session.close();
        return teacher;
    }
    public boolean saveCourse(Course course){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(course);
        transaction.commit();
        session.close();
        return true;
    }

    public List<Course> getCourses(){
        String hql = "from  Course ";
        Session session = sessionFactory.openSession();
        List<Course> courses = session.createQuery(hql).list();
        session.close();
        return courses;
    }
    public Course getCourse( int id ) {
        Session session = sessionFactory.openSession();

        Course course = (Course) session.get(Course.class, id);
        session.close();
        return course;
    }

    public boolean setExamDate(int id, Date examDate){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Course set examDate=:examDate where id=:id";

        session.createQuery(hql).setParameter("id",id).setParameter("examDate",examDate).executeUpdate();

        transaction.commit();
        return true;

    }




    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
