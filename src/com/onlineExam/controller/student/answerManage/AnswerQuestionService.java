package com.onlineExam.controller.student.answerManage;

import com.onlineExam.dao.impls.StudentDaoImpl;
import com.onlineExam.pojo.persons.SelectCourse;
import com.onlineExam.xml.ExamXML;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by niceyuanze on 16-12-17.
 */
@Component
public class AnswerQuestionService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StudentDaoImpl studentDaoImpl;

    @Autowired
    private ExamXML examXML;

    public String answerChoiceQuestion(String id, String answer) throws IOException, DocumentException {
        Integer studentId = (Integer)httpSession.getAttribute("id");
        SelectCourse selectCourse = studentDaoImpl.getSelectCourseByStatus(studentId,2).get(0);

        System.out.println("这里是选择题回答场所");
        System.out.println(selectCourse.getPaperPath());
        System.out.println(id);
        System.out.println(answer);
        examXML.setChoiceQuestionAnswer(selectCourse.getPaperPath(),id,answer);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","选择题回答成功");
        return jsonObject.toString();
    }
    public String answerJudgeQuestion(String id, String answer) throws IOException, DocumentException {
        Integer studentId = (Integer)httpSession.getAttribute("id");
        SelectCourse selectCourse = studentDaoImpl.getSelectCourseByStatus(studentId,2).get(0);

        System.out.println("这里是判断题回答场所");
        System.out.println(selectCourse.getPaperPath());
        System.out.println(id);
        System.out.println(answer);
        examXML.setJudgeQuestionAnswer(selectCourse.getPaperPath(),id,answer);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","判断题回答成功");
        return jsonObject.toString();
    }

    public String answerSubjectiveCompletionQuestion(String id, String[] answer) throws IOException, DocumentException {
        Integer studentId = (Integer)httpSession.getAttribute("id");
        SelectCourse selectCourse = studentDaoImpl.getSelectCourseByStatus(studentId,2).get(0);

        System.out.println("这里是主观填空题回答场所");
        System.out.println(selectCourse.getPaperPath());
        System.out.println(id);
        System.out.println(answer);
        examXML.setSubjectiveCompletionAnswer(selectCourse.getPaperPath(),id,answer);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","主观填空题回答成功");
        return jsonObject.toString();
    }

    public String answerSubjectiveQuestion(String id, String answer) throws IOException, DocumentException {
        Integer studentId = (Integer)httpSession.getAttribute("id");
        SelectCourse selectCourse = studentDaoImpl.getSelectCourseByStatus(studentId,2).get(0);

        System.out.println("这里是主观题回答场所");
        System.out.println(selectCourse.getPaperPath());
        System.out.println(id);
        System.out.println(answer);
        examXML.setSubjectiveAnswer(selectCourse.getPaperPath(),id,answer);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","主观题回答成功");
        return jsonObject.toString();
    }



    public HttpSession getHttpSession() {
        return httpSession;
    }

    public StudentDaoImpl getStudentDaoImpl() {
        return studentDaoImpl;
    }

    public void setStudentDaoImpl(StudentDaoImpl studentDaoImpl) {
        this.studentDaoImpl = studentDaoImpl;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public ExamXML getExamXML() {
        return examXML;
    }

    public void setExamXML(ExamXML examXML) {
        this.examXML = examXML;
    }
}
