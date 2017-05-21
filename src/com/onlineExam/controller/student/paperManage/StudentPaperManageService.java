package com.onlineExam.controller.student.paperManage;

import com.onlineExam.dao.impls.StudentDaoImpl;
import com.onlineExam.pojo.persons.SelectCourse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by niceyuanze on 16-12-26.
 */
@Component
public class StudentPaperManageService {

    @Autowired
    private HttpSession session;

    @Autowired
    private StudentDaoImpl studentDao;


    public String getMyPapers(){
        Integer studentId= (Integer)session.getAttribute("id");
        List<SelectCourse> selectCourses = studentDao.getSelectCourseByStatus(studentId,5);
        JSONArray jsonArray = new JSONArray();

        for(SelectCourse selectCourse: selectCourses){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", selectCourse.getId());
            jsonObject.put("courseName", selectCourse.getCourse().getName());
            jsonObject.put("institute", selectCourse.getCourse().getInstitute());
            jsonObject.put("major", selectCourse.getCourse().getMajor());
            jsonObject.put("teacherName", selectCourse.getCourse().getTeacher().getName());
            jsonObject.put("score", selectCourse.getScore());
            jsonArray.add(jsonObject);
        }



        return jsonArray.toString();
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public StudentDaoImpl getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDaoImpl studentDao) {
        this.studentDao = studentDao;
    }
}
