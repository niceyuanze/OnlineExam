package com.onlineExam.dao.impls;

import com.onlineExam.dao.interfaces.TeacherDao;
import com.onlineExam.pojo.persons.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by niceyuanze on 16-11-29.
 */
@Component
public class StudentDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    //登陆功能
    public Student login(String username, String password) {
        Session session = sessionFactory.openSession();
        String hql = " select t from Student t where username=:username and password=:password";
        List<Student> students = session.createQuery(hql).setParameter("username",username)
                .setParameter("password",password).list();

        System.out.println(students.size());
        System.out.println("Congratulations");
        if(students.size() == 1){//西部世界
            return students.get(0);
        }else{
            return null;
        }
    }
    //学生id
    public List<SelectCourse> getExamCourses(int id){
        Session session = sessionFactory.openSession();
        Date date = new Date(System.currentTimeMillis());
        String hql = "from SelectCourse where course.strategy != null and course.examDate=:date";
        List<SelectCourse> selectCourses = session.createQuery(hql)
                .setParameter("date",date)
                .list();
        session.close();
        return selectCourses;
    }
    //获取selectCourse  记录id
    public SelectCourse getSelectCouse(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from SelectCourse where id=:id";
        List<SelectCourse> selectCourse = session.createQuery(hql).setParameter("id",id).list();
//        System.out.println("我这是包围你啊");
//        System.out.println("我这是包围你啊");
//        System.out.println("我这是包围你啊");
//        SelectCourse selectCourse1 = new SelectCourse();
//        selectCourse1.setStudentId(selectCourse.get(0).getStudentId());
//        selectCourse1.setCourseId(selectCourse.get(0).getCourseId());
//        selectCourse1.setPaperPath(selectCourse.get(0).getPaperPath());
//        selectCourse1.setAnswerPaph(selectCourse.get(0).getAnswerPaph());
//        selectCourse1.setCheckStatus(selectCourse.get(0).getCheckStatus());
//        selectCourse1.setExamStatus(selectCourse.get(0).getExamStatus());
//        selectCourse1.setStudent(selectCourse.get(0).getStudent());
//        selectCourse1.setCourse(selectCourse.get(0).getCourse());
//        System.out.println(selectCourse1);
//        //SelectCourse{id=2, studentId=1, courseId=1, paperPath='/home/niceyuanze/desktop/OnlineExamBase/paper/1paper', answerPaph='null', checkStatus=2, examStatus=1, student=com.onlineExam.pojo.persons.Student@e34d8a07, course=com.onlineExam.pojo.persons.Course@e765b6e8}
//        //SelectCourse{id=8, studentId=1, courseId=1, paperPath='/home/niceyuanze/desktop/OnlineExamBase/paper/1paper', answerPaph='null', checkStatus=0, examStatus=0, student=com.onlineExam.pojo.persons.Student@e34d8a07, course=com.onlineExam.pojo.persons.Course@e765b6e8}
//        //SelectCourse{id=null, studentId=1, courseId=1, paperPath='', answerPaph='', checkStatus=0, examStatus=0, student=com.onlineExam.pojo.persons.Student@67e12cdf, course=com.onlineExam.pojo.persons.Course@94446f01}
//        System.out.println(selectCourse.get(0));
////        session.save(selectCourse1);
//        System.out.println(selectCourse1);
//        System.out.println("我这是包围你啊");
//        System.out.println("我这是包围你啊");
//        System.out.println("我这是包围你啊");
        transaction.commit();
        session.close();
        return selectCourse.get(0);
    }

    //根据题库和类型获取题目
    public List<Question> getQuestionByQuestionBases(ArrayList<Integer> questionBasesIds, String type){
        Session session = sessionFactory.openSession();
        String hql="from Question q where q.type=:type and q.questionBase.id in(:questionBaseIds)";
        List<Question> questions = session.createQuery(hql)
                .setParameter("type", type)
                .setParameterList("questionBaseIds",questionBasesIds)
                .list();
        session.close();
        return questions;

    }
    //修改paperPath,id是选课记录id
    public boolean setPaperPath(int id, String paperPath){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update SelectCourse  set paperPath=:paperPath where id=:id";

        session.createQuery(hql)
                .setParameter("id",id)
                .setParameter("paperPath",paperPath)
                .executeUpdate();
        transaction.commit();
        session.close();
        return true;

    }
    //设置考试状态
    public boolean setExamStatus(int id,int examStatus){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update SelectCourse  set examStatus=:examStatus where id=:id";
        session.createQuery(hql)
                .setParameter("id",id)
                .setParameter("examStatus",Byte.valueOf(""+examStatus))
                .executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
    //获取正在考试的科目,根据学生id
    public List<SelectCourse> getSelectCourseByStatus(int id, int examStatus){
        Session session = sessionFactory.openSession();
        String hql = "from SelectCourse where student.id=:id and examStatus=:examStatus";
        List<SelectCourse> selectCourse = session.createQuery(hql)
                .setParameter("id",id)
                .setParameter("examStatus",Byte.valueOf(examStatus+""))
                .list();
        session.close();
        return selectCourse;

    }

    public List<Course> getCoubleBeSelectedCourses(int studentId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.flush();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-5);

        Date date = new Date(calendar.getTimeInMillis());
        transaction.commit();


        String hql = "from Course  where startDate >=:nowDate and id not in (select courseId from SelectCourse where studentId=:studentId)";
        List<Course> courses = session.createQuery(hql)
                .setParameter("nowDate",date)
                .setParameter("studentId",studentId)
                .list();


        session.close();
        return courses;
    }

    public boolean saveSelectCourse(SelectCourse selectCourse){

        selectCourse.setId(8);
        System.out.println(selectCourse);
        Session session = sessionFactory.openSession();
//        String hql="insert into SelectCourse (studentId,courseId,checkStatus,examStatus) values(?,?,?,?)";
//        session.createQuery(hql).executeUpdate();



        Transaction transaction = session.beginTransaction();

        session.save(selectCourse);
        transaction.commit();
        session.close();

        return true;
    }

    public Course getCourse(int id){
        Session session = sessionFactory.openSession();
        Course course = (Course) session.get(Course.class,id);
        session.close();
        return course;
    }
    public Student getStudent(int id){
        Session session = sessionFactory.openSession();
        Student student = (Student) session.get(Student.class,id);
        session.close();
        return student;
    }

    public List<SelectCourse> getMySelectCourses(int id){
        Session session = sessionFactory.openSession();
        String hql = "from SelectCourse  where checkStatus >= 1 and studentId=:id";
        List<SelectCourse> selectCourses = session.createQuery(hql).setParameter("id",id).list();
        session.close();
        return selectCourses;
    }














    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
