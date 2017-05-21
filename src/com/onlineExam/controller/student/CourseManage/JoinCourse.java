package com.onlineExam.controller.student.CourseManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Created by niceyuanze on 16-12-21.
 */
@Controller
@RequestMapping("student/CourseManage/joinCourse")
public class JoinCourse
{
    @Autowired
    private StudentCourseManageService studentCourseManageService;

    @ResponseBody
    @RequestMapping(value = "/getCouldBeSelectedCourses", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getCouldBeSelectedCourses(){
        return studentCourseManageService.getCouldBeSelectedCourses();
    }

    @ResponseBody
    @RequestMapping(value = "/joinCourse", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String joinCourse(int id) throws SQLException, ClassNotFoundException {
        return studentCourseManageService.joinCourse(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getMyHaveSelectCourses", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getMyHaveSelectCourses() throws SQLException, ClassNotFoundException {
        return studentCourseManageService.getMyHaveSelectCourses();
    }


    public StudentCourseManageService getStudentCourseManageService() {
        return studentCourseManageService;
    }

    public void setStudentCourseManageService(StudentCourseManageService studentCourseManageService) {
        this.studentCourseManageService = studentCourseManageService;
    }
}
