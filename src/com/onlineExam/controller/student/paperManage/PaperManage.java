package com.onlineExam.controller.student.paperManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by niceyuanze on 16-12-26.
 */
@Controller
@RequestMapping("/student/paperManage")
public class PaperManage {

    @Autowired
    private StudentPaperManageService studentPaperManageService;

    @ResponseBody
    @RequestMapping(value="/getMyExams",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String getMyCourses(){
        return studentPaperManageService.getMyPapers();
    }

    public StudentPaperManageService getStudentPaperManageService() {
        return studentPaperManageService;
    }

    public void setStudentPaperManageService(StudentPaperManageService studentPaperManageService) {
        this.studentPaperManageService = studentPaperManageService;
    }
}
