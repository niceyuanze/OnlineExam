package com.onlineExam.controller.background.statistic;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import com.onlineExam.pojo.persons.SelectCourse;
import com.onlineExam.pojo.question.JudgeQuestion;
import com.onlineExam.xml.ExamXML;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by niceyuanze on 16-12-24.
 */
@Component
public class StatisticService {
    @Autowired
    private HttpSession session;

    @Autowired
    private TeacherDaoImpl teacherDao;

    @Autowired
    private ExamXML examXML;



    public String getStudentScores(){
        Integer teacherId = (Integer)session.getAttribute("id");
        List<SelectCourse> selectCourses = teacherDao.getStudentScores(teacherId);
        JSONArray jsonArray = new JSONArray();
        for(SelectCourse selectCourse: selectCourses){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", selectCourse.getId());
            jsonObject.put("courseName", selectCourse.getCourse().getName());
            jsonObject.put("institute", selectCourse.getStudent().getInstitute());
            jsonObject.put("major", selectCourse.getStudent().getMajor());
            jsonObject.put("studentName", selectCourse.getStudent().getName());
            jsonObject.put("score", selectCourse.getScore());
            jsonArray.add(jsonObject);

        }
        return jsonArray.toString();

    }

    public String getHaveMarkedPaper(int id) throws DocumentException {
        SelectCourse selectCourse = teacherDao.getSelectCourseById(id);
        Map<String,ArrayList> paper = examXML.getPaper(selectCourse.getPaperPath());
        JSONObject jsonObject = new JSONObject();
        for(String key: paper.keySet()){
            if(key.equals("判断题")){
                jsonObject.put(key,judgeJSON(paper.get(key)));
            }else {
                jsonObject.put(key,paper.get(key));

            }
        }


        return jsonObject.toString();
    }

    public JSONArray judgeJSON(ArrayList<JudgeQuestion> judgeQuestions){
        JSONArray jsonArray = new JSONArray();

        for(JudgeQuestion judgeQuestion: judgeQuestions){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id",judgeQuestion.getId());
            jsonObject.put("difficultyIndex", judgeQuestion.getDifficultyIndex());
            jsonObject.put("description", judgeQuestion.getDescription());
            if(judgeQuestion.getAnswer()!=null){
                jsonObject.put("answer",judgeQuestion.getAnswer());

            }
            jsonObject.put("score",judgeQuestion.getScore());
            jsonObject.put("teacherAnswer",judgeQuestion.getTeacherAnswer());
            jsonObject.put("teacherScore",judgeQuestion.getTeacherScore());
            System.out.println(judgeQuestion.getAnswer());

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

}
