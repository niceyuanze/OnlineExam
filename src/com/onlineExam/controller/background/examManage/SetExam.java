package com.onlineExam.controller.background.examManage;

import com.onlineExam.pojo.question.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by niceyuanze on 16-12-11.
 */
@RequestMapping("/examManage")
@Controller
public class SetExam {
    @Autowired
    private ExamManageService examManageService;

    @ResponseBody
    @RequestMapping(value="/getCourses",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String getCourses(){
        return examManageService.getCourses();
    }

    @ResponseBody
    @RequestMapping(value="/getQuestionBases",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String getQuestionBases(){
        return examManageService.getQuestionBases();
    }




    @ResponseBody
    @RequestMapping(value="/setExam",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String setExam(Strategy strategy) throws IOException {
        return examManageService.setExam(strategy);

    }



    public ExamManageService getExamManageService() {
        return examManageService;
    }

    public void setExamManageService(ExamManageService examManageService) {
        this.examManageService = examManageService;
    }


}
