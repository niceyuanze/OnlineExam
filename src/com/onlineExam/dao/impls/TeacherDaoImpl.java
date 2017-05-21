package com.onlineExam.dao.impls;

import com.onlineExam.dao.interfaces.TeacherDao;
import com.onlineExam.pojo.persons.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by niceyuanze on 16-11-29.
 */
@Component
public class TeacherDaoImpl implements TeacherDao{

    @Autowired
    private SessionFactory sessionFactory;

    public int saveQuestion(Question quesiton){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(quesiton);
        transaction.commit();
        session.close();
        return quesiton.getId();
    }



    //登陆功能
    public Teacher login(String username, String password) {
        Session session = sessionFactory.openSession();
        String hql = " select t from Teacher t where username=:username and password=:password";
        List<Teacher> teachers = session.createQuery(hql).setParameter("username",username)
                .setParameter("password",password).list();

        System.out.println(teachers.size());
        System.out.println("Congratulations");
        if(teachers.size() == 1){//西部世界
            return teachers.get(0);
        }else{
            return null;
        }
    }


    //对题库进行修改
    public boolean changeQuestionBase(QuestionBase questionBase){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update QuestionBase questionBase set questionBase.name=:name where questionBase.id=:id";
        session.createQuery(hql).setParameter("id",questionBase.getId())
                .setParameter("name",questionBase.getName())
                .executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }


    //获取自己的所有题库，不分页
    public List<QuestionBase> getQuestionBases(int id, int firstResult, int maxResult){
        Session session = sessionFactory.openSession();
        String hql = "select q from QuestionBase q where q.teacher.id=:id";
        List<QuestionBase> questionBases = session.createQuery(hql).setParameter("id",id)
                .setFirstResult((firstResult-1)*maxResult)
                .setMaxResults(maxResult)
                .list();
        return questionBases;
    }
    //获得题库的所用记录数
    public int getQuestionInt(int id){


        Session session = sessionFactory.openSession();
        String hql=  "select count (*) from QuestionBase q where q.teacher.id=:id";
        return ((Number)session.createQuery(hql).setParameter("id",id).uniqueResult()).intValue();

    }

    //通过题库名字获取题库
    public List<QuestionBase> searchQuestionBaseByName(String questionBaseName){
        Session session = sessionFactory.openSession();
        String hql = "select q from QuestionBase q where name=:name";
        List<QuestionBase> questionBases = session.createQuery(hql).setParameter("name",questionBaseName).list();
        return questionBases;

    }
    //删除题库
    public boolean deleteQuestionBase(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from QuestionBase q where q.id=:id";

        session.createQuery(hql).setParameter("id",id).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
    public boolean saveQuestionBase(QuestionBase questionBase){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();



        session.save(questionBase);

        transaction.commit();
        session.close();

        return true;
    }

    public void save(Teacher teacher){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();



        session.save(teacher);

        transaction.commit();
        session.close();


    }
    //通过id获得题库
    public QuestionBase getQuestionBase(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        QuestionBase questionBase = (QuestionBase) session.get(QuestionBase.class,id);
        transaction.commit();
        session.close();
        return questionBase;

    }
    //****************************************************
    //question表操作
    //****************************************************


    //根据的是用户id
    public List<Question> getQuestions(int id, int firstResult, int maxResult){
        System.out.println("这里是dao方法");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Question q where q.questionBase.teacher.id=:id order by q.questionBase.id";
        List<Question> questions = session.createQuery(hql)
                .setParameter("id",id)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult).list();

        System.out.println("数量"+questions.size());
        return questions;//
    }
    //根据的是题目id
    public Question getQuestion(int id){
        Session session = sessionFactory.openSession();
        Question question = (Question)session.get(Question.class,id);
        session.close();
        return question;

    }
    public boolean changeQuestion(Question question){
        System.out.println(question.getId()+question.getDescription()+question.getDifficultyIndex());
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Question q set q.description=:description ,q.difficultyIndex=:difficultyIndex,q.questionBase=:questionBase where q.id=:id";

        session.createQuery(hql)
                .setParameter("id",question.getId())
                .setParameter("description",question.getDescription())
                .setParameter("difficultyIndex",question.getDifficultyIndex())
                .setParameter("questionBase",question.getQuestionBase())
                .executeUpdate();

        transaction.commit();
        session.close();

        return true;
    }
    //---------------------------------------------------------
    //根据用户id获取
    public List<Course> getCourses(int id, int firstResult, int maxResult){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Course c where c.teacher.id=:id";
        List<Course> courses = session.createQuery(hql)
                .setParameter("id",id)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .list();
        return courses;

    }

    public boolean setStrategy(int id, String path){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Course c set c.strategy=:strategy where c.id=:id";
        session.createQuery(hql)
                .setParameter("id",id)
                .setParameter("strategy",path)
                .executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    //------------------------------------------------------选课管理
    //
    //根据id获取teachers
    public List<SelectCourse> getSelectCourses(int id, int firstResult, int maxResult, int checkStatus){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from SelectCourse where course.teacher.id=:id and checkStatus=:checkStatus order by course.id";
        List<SelectCourse> selectCourses = session
                .createQuery(hql)
                .setParameter("id",id)
                .setParameter("checkStatus", Byte.valueOf(""+checkStatus))
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .list();
        transaction.commit();
        session.close();
        return selectCourses;

    }
    public boolean acceptSelectCourse(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update SelectCourse  set checkStatus=2 where id=:id";

        session.createQuery(hql)
                .setParameter("id",id)
                .executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    public boolean refuseSelectCourse(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update SelectCourse  set checkStatus=-1 where id=:id";

        session.createQuery(hql)
                .setParameter("id",id)
                .executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }


    public List<SelectCourse> getSelectCourseByStatus(int id, int examStatus, int firstResult, int maxResult){
        Session session = sessionFactory.openSession();
        String hql = "from SelectCourse where course.teacher.id=:id and examStatus=:examStatus";
        System.err.println("获取到了3");

        List<SelectCourse> selectCourse = session.createQuery(hql)
                .setParameter("id",id)
                .setParameter("examStatus",Byte.valueOf(examStatus+""))
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .list();
        session.close();
        return selectCourse;

    }

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

    public boolean setPaperScore(int id, int score){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update SelectCourse  set examStatus=5 ,score=:score where id=:id";
        session.createQuery(hql)
                .setParameter("score",score)
                .setParameter("id",id)
                .executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
    public List<SelectCourse> getStudentScores(int id){
        Session session = sessionFactory.openSession();
        String hql = "from SelectCourse where examStatus=5 and course.teacher.id=:id";
        List<SelectCourse> selectCourses = session.createQuery(hql).setParameter("id",id).list();
        return selectCourses;
    }
    //根据记录id
    public SelectCourse getSelectCourseById(int id){
        Session session = sessionFactory.openSession();
        String hql = "from SelectCourse where id=:id";
        SelectCourse selectCourse = (SelectCourse) session.createQuery(hql).setParameter("id", id).list().get(0);
        session.close();
        return selectCourse;
    }

//    public boolean deleteQuestionBase(int id){
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        String hql = "delete from QuestionBase q where q.id=:id";
//
//        session.createQuery(hql).setParameter("id",id).executeUpdate();
//        transaction.commit();
//        session.close();
//        return true;
//    }





    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
