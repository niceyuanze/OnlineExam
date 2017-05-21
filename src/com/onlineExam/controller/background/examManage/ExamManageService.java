package com.onlineExam.controller.background.examManage;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import com.onlineExam.pojo.persons.Course;
import com.onlineExam.pojo.persons.QuestionBase;
import com.onlineExam.pojo.persons.SelectCourse;
import com.onlineExam.pojo.question.Strategy;
import com.onlineExam.pojo.question.SubjectiveCompletionQuestion;
import com.onlineExam.pojo.question.SubjectiveQuestion;
import com.onlineExam.xml.ExamXML;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by niceyuanze on 16-12-11.
 */
@Component
public class ExamManageService {

    @Autowired
    private TeacherDaoImpl teacherDao;

    @Autowired
    private ExamXML examXML;

    @Autowired
    private HttpSession session;

    public String getCourses(){
        Integer id = (Integer)session.getAttribute("id");
        List<Course> courses = teacherDao.getCourses(id,0,Integer.MAX_VALUE);
        JSONArray jsonArray = new JSONArray();

        for(Course course: courses){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(course.getId(),course.getName());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }
    public String getQuestionBases(){
        Integer id = (Integer)session.getAttribute("id");
        List<QuestionBase> questionBases = teacherDao.getQuestionBases(id,0,Integer.MAX_VALUE);
        JSONArray jsonArray = new JSONArray();
        for(QuestionBase questionBase: questionBases){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(questionBase.getId(),questionBase.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
    public String setExam(Strategy strategy) throws IOException {
        String strategyPath = examXML.generateStrategyXML(strategy);
        teacherDao.setStrategy(strategy.getCourseId(),strategyPath);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","添加成功");
        return jsonObject.toString();
    }

    public String getStudentPapers(int firstPage, int maxResult){
        Integer id = (Integer)session.getAttribute("id");
        System.err.println("获取到了0");

        List<SelectCourse> selectCourses = teacherDao.getSelectCourseByStatus(id,3,(firstPage - 1) * maxResult,maxResult);
        JSONArray jsonArray = new JSONArray();
        System.err.println("获取到了1");
        for(SelectCourse selectCourse: selectCourses){
//            System.out.println(selectCourse.getCourse()+" "+selectCourse.getId() +" "+ selectCourse.getExamStatus());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", selectCourse.getId());
            jsonObject.put("courseName", selectCourse.getCourse().getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    public String pepareMarkPaper(int id){
        teacherDao.setExamStatus(id,4);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","准备成功");
        return jsonObject.toString();
    }

    public String beginMarkPaper() throws DocumentException {
        Integer id = (Integer)session.getAttribute("id");
        SelectCourse selectCourse  = teacherDao.getSelectCourseByStatus(id, 4,0,1).get(0);
//        teacherDao.setExamStatus(selectCourse.getId(),5);
        String path = selectCourse.getPaperPath();
        Map<String,ArrayList> paper=  examXML.getFinishedPaper(path);
        JSONObject jsonObject = new JSONObject();
        for(String key: paper.keySet()){
           if(key.equals("主观填空题")){
                jsonObject.put(key, subjectiveCompletionJSON(paper.get(key)));
            }else if(key.equals("主观题")){
                jsonObject.put(key, subjectiveJSON(paper.get(key)));
            }
        }


        return jsonObject.toString();


    }

    public JSONArray subjectiveCompletionJSON(ArrayList<SubjectiveCompletionQuestion> subjectiveCompletionQuestions){
        JSONArray jsonArray = new JSONArray();
        for(SubjectiveCompletionQuestion subjectiveCompletionQuestion: subjectiveCompletionQuestions){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("answer",subjectiveCompletionQuestion.getAnswer());
            jsonObject.put("score", subjectiveCompletionQuestion.getScore());
            jsonObject.put("id",subjectiveCompletionQuestion.getId());
            jsonObject.put("description", subjectiveCompletionQuestion.getDescription());
            jsonObject.put("difficultyIndex", subjectiveCompletionQuestion.getDifficultyIndex());
            jsonArray.add(jsonObject);
        }


        return  jsonArray;
    }

    public JSONArray subjectiveJSON(ArrayList<SubjectiveQuestion> subjectiveQuestions){
        JSONArray jsonArray = new JSONArray();

        for(SubjectiveQuestion subjectiveQuestion: subjectiveQuestions){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("score", subjectiveQuestion.getScore());
            jsonObject.put("id",subjectiveQuestion.getId());
            jsonObject.put("answer",subjectiveQuestion.getAnswer());
            jsonObject.put("difficultyIndex", subjectiveQuestion.getDifficultyIndex());
            jsonObject.put("description", subjectiveQuestion.getDescription());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    public String setSubjectiveQuestionScore(int id,int score) throws IOException, DocumentException {
        Integer teacherId = (Integer)session.getAttribute("id");
        SelectCourse selectCourse  = teacherDao.getSelectCourseByStatus(teacherId, 4,0,1).get(0);
        String path  = selectCourse.getPaperPath();
        examXML.setSubjectiveQuestionScore(path,id,score);
        System.err.println(path);
        System.err.println(id);

        System.err.println(score);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","主观题分数上传成功");
        return jsonObject.toString();



    }

    public String setSubjectiveCompletionQuestionScore(int id, int score) throws IOException, DocumentException {
        Integer teacherId = (Integer)session.getAttribute("id");
        SelectCourse selectCourse  = teacherDao.getSelectCourseByStatus(teacherId, 4,0,1).get(0);
        String path  = selectCourse.getPaperPath();
        examXML.setSubjectiveCompletionQuestionScore(path,id,score);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","主观填空题分数上传成功");
        return jsonObject.toString();
    }

    

    public String getPaperScore() throws DocumentException, IOException {
        Integer teacherId = (Integer)session.getAttribute("id");
        SelectCourse selectCourse  = teacherDao.getSelectCourseByStatus(teacherId, 4,0,1).get(0);
        String path = selectCourse.getPaperPath();
        JSONObject jsonObject = new JSONObject();
        int score=0;
        score = examXML.getPaperScore(path);
        jsonObject.put("score",score);
        return jsonObject.toString();
    }
    public String setPaperScore() throws DocumentException, IOException {

        Integer teacherId = (Integer)session.getAttribute("id");
        SelectCourse selectCourse = teacherDao.getSelectCourseByStatus(teacherId, 4,0,1).get(0);
        String path = selectCourse.getPaperPath();
        int score = examXML.getPaperScore(path);
        teacherDao.setPaperScore(selectCourse.getId(),score);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","批改成功");
        return jsonObject.toString();
    }




    public ExamXML getExamXML() {
        return examXML;
    }

    public void setExamXML(ExamXML examXML) {
        this.examXML = examXML;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public TeacherDaoImpl getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDaoImpl teacherDao) {
        this.teacherDao = teacherDao;
    }
}
