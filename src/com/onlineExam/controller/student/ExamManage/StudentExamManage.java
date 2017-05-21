package com.onlineExam.controller.student.ExamManage;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by niceyuanze on 16-12-14.
 */
@Controller
@RequestMapping("/ExamManage/MyExam")
public class StudentExamManage {

    @Autowired
    private StudentExamManageService studentExamManageService;


    @ResponseBody
    @RequestMapping(value="/getMyExams",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String getMyExams(){
        System.out.println(studentExamManageService.getMyExams());
        return studentExamManageService.getMyExams();
    }

    @ResponseBody
    @RequestMapping(value="/prepareExam",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String prepareExam(int id) throws DocumentException, IOException {
        System.out.println("??????????????????????????????????????????????");
        return studentExamManageService.prepareExam(id);
    }

    @ResponseBody
    @RequestMapping(value="/getPaper",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String getPaper() throws DocumentException {
        return studentExamManageService.getPaper();
    }

    @ResponseBody
    @RequestMapping(value="/submitPaper",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String submitPaper() throws DocumentException {
        return studentExamManageService.submitPaper();
    }






    @RequestMapping(value = "/examDispatcher",method = RequestMethod.GET)
    public String examDispatcher(){
        return "exam/exam";
    }



    public StudentExamManageService getStudentExamManageService() {
        return studentExamManageService;
    }

    public void setStudentExamManageService(StudentExamManageService studentExamManageService) {
        this.studentExamManageService = studentExamManageService;
    }
}
