package com.onlineExam.controller.academic;

import com.onlineExam.dao.impls.AcademicDaoImpl;
import com.onlineExam.pojo.persons.Course;
import com.onlineExam.pojo.persons.Teacher;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

/**
 * Created by niceyuanze on 16-12-26.
 */

@Controller
@RequestMapping("/academic/courseManage")
public class AcademicCourseManage {
    @Autowired
    private AcademicService academicService;

    @Autowired
    private AcademicDaoImpl academicDao;

    @ResponseBody
    @RequestMapping(value="getTeachers", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getTeachers(){
        return academicService.getTeachers();
    }

    @ResponseBody
    @RequestMapping(value="openCourse", method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String openCourse(Course course, int teacherId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "successful");
        Teacher teacher = academicDao.getTeacher(teacherId);
        course.setTeacher(teacher);
        academicDao.saveCourse(course);
        System.out.println(course);
        System.out.println(teacherId);
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(value="getCourses", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getCourses(){
        return academicService.getCourses();
    }


    @ResponseBody
    @RequestMapping(value="setExamDate", method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String setExamDate(Date examDate, int courseId){
        academicDao.setExamDate(courseId, examDate);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "successful");
        return jsonObject.toString();

    }






    public AcademicService getAcademicService() {
        return academicService;
    }

    public void setAcademicService(AcademicService academicService) {
        this.academicService = academicService;
    }
}
