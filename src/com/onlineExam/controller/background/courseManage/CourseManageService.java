package com.onlineExam.controller.background.courseManage;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import com.onlineExam.pojo.persons.Course;
import com.onlineExam.pojo.persons.SelectCourse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by niceyuanze on 16-12-12.
 */
@Component
public class CourseManageService {

    @Autowired
    private TeacherDaoImpl teacherDaoImpl;

    @Autowired
    private HttpSession session;

    public String accept(int id){

        teacherDaoImpl.acceptSelectCourse(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","添加成功");
        return jsonObject.toString();

    }

    public String refuse(int id){

        teacherDaoImpl.refuseSelectCourse(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","拒绝成功");
        return jsonObject.toString();

    }




    public String getMyCourses(int firstPage, int maxResult){
        Integer id = (Integer)session.getAttribute("id");
        List<Course> courses = teacherDaoImpl.getCourses(id,(firstPage-1)*maxResult,maxResult);

        JSONArray jsonArray = new JSONArray();

        for(Course course: courses){
            long accepted = course
                    .getSelectCourses()
                    .stream()
                    .parallel()
                    .filter(selectCourse -> selectCourse.getCheckStatus() == 2)
                    .count();
            long checking = course
                    .getSelectCourses()
                    .stream()
                    .parallel()
                    .filter(selectCourse -> selectCourse.getCheckStatus() == 1)
                    .count();
            long refused = course
                    .getSelectCourses()
                    .stream()
                    .parallel()
                    .filter(selectCourse -> selectCourse.getCheckStatus() == 0)
                    .count();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",course.getId());
            jsonObject.put("name", course.getName());
            jsonObject.put("institute", course.getInstitute());
            jsonObject.put("major", course.getMajor());
            jsonObject.put("startDate", course.getStartDate().toString());
            jsonObject.put("endDate", course.getEndDate().toString());
            jsonObject.put("examDate", course.getExamDate() == null?null:course.getExamDate().toString());
            jsonObject.put("accepted",accepted);
            jsonObject.put("checking",checking);
            jsonArray.add(jsonObject);

        }
        return jsonArray.toString();
    }

    public String getSelectCourses(int firstPage, int maxResult){

        Integer id = (Integer)session.getAttribute("id");

        JSONArray jsonArray = new JSONArray();


        for(SelectCourse selectCourse: teacherDaoImpl.getSelectCourses(id,(firstPage - 1) * maxResult, maxResult,1)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",selectCourse.getId());
            jsonObject.put("courseName", selectCourse.getCourse().getName());
            jsonObject.put("institute", selectCourse.getStudent().getInstitute());
            jsonObject.put("major", selectCourse.getStudent().getMajor());
            jsonObject.put("studentNumber", selectCourse.getStudent().getStudentNumber());
            jsonObject.put("studentName", selectCourse.getStudent().getName());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    public TeacherDaoImpl getTeacherDaoImpl() {
        return teacherDaoImpl;
    }

    public void setTeacherDaoImpl(TeacherDaoImpl teacherDaoImpl) {
        this.teacherDaoImpl = teacherDaoImpl;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
