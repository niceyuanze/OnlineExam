package com.onlineExam.test;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by niceyuanze on 16-11-29.
 */
public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext("com.onlineExam");
//        TeacherDaoImpl teacherDaoImpl = context.getBean(TeacherDaoImpl.class);
//        List<Course> courses = teacherDaoImpl.getCourses(1);
//        for(Course course: courses){
//            System.out.println(course.getName());
//        }
//        System.out.println(teacherDaoImpl);
//        Teacher teacher = new Teacher("1","2","3","4","5","6");
//        teacher.setId("1");
//        teacherDaoImpl.save(teacher);

//        Configuration configuration = null;
//        SessionFactory sessionFactory = null;
//        Transaction transaction = null;
//        try{
//            configuration = new Configuration().configure();
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            Session session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//            Function function = (Function )session.get(Function.class, 1);
//            function.getId();
//            function.getName();
//            function.getUrl();
//            Set<Role> roles = function.getRoles();
//            for(Iterator<Role> iterator = function.getRoles().iterator();iterator.hasNext();){
//                Role role = iterator.next();
//                System.out.println(role.getName());
//
//            }
//            transaction.commit();
//        }catch(Exception e){
//            transaction.rollback();
//            e.printStackTrace();
//        }
//    }
    }
}
