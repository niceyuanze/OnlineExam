package com.onlineExam.controller.background.courseManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by niceyuanze on 16-12-13.
 */
@Controller
@RequestMapping("/courseManage/selectCourseManage")
public class SelectCourseManage {

    @Autowired
    private CourseManageService courseManageService;

    @ResponseBody
    @RequestMapping(value="getSelectCourses", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getSelectCourses(int firstPage, int maxResult){

        return courseManageService.getSelectCourses(firstPage, maxResult);
    }
    @ResponseBody
    @RequestMapping(value ="accept", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
    public String accept(int id){
        System.out.println("jasdfasdfasdfsadf");
        System.out.println("idididididi"+id);
        return courseManageService.accept(id);
    }

    @ResponseBody
    @RequestMapping(value ="refuse", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
    public String refuse(int id){
        System.out.println("jasdfasdfasdfsadf");
        System.out.println("idididididi"+id);
        return courseManageService.refuse(id);
    }





    public CourseManageService getCourseManageService() {
        return courseManageService;
    }

    public void setCourseManageService(CourseManageService courseManageService) {
        this.courseManageService = courseManageService;
    }
}
