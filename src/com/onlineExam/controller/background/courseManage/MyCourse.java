package com.onlineExam.controller.background.courseManage;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by niceyuanze on 16-12-12.
 */
@Controller
@RequestMapping("/courseManage/myCourse")
public class MyCourse {

    @Autowired
    private TeacherDaoImpl teacherDao;

    @Autowired
    private CourseManageService courseManageService;


    @ResponseBody
    @RequestMapping(value="getMyCourses", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getCourses(int firstPage, int maxResult){
       return courseManageService.getMyCourses(firstPage, maxResult);
    }


    public CourseManageService getCourseManageService() {
        return courseManageService;
    }

    public void setCourseManageService(CourseManageService courseManageService) {
        this.courseManageService = courseManageService;
    }

    public TeacherDaoImpl getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDaoImpl teacherDao) {
        this.teacherDao = teacherDao;
    }
}
