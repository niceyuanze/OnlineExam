package com.onlineExam.controller.student.answerManage;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by niceyuanze on 16-12-17.
 */
@Controller
@RequestMapping("/answerManage/answerQuestion")
public class AnswerQuestion {

    @Autowired
    private AnswerQuestionService answerQuestionService;

    @ResponseBody
    @RequestMapping(value="/answerChoiceQuestion",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String answerChoiceQuestion(String id,String answer) throws IOException, DocumentException {
       return answerQuestionService.answerChoiceQuestion(id,answer);
    }

    @ResponseBody
    @RequestMapping(value="/answerJudgeQuestion",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String answerJudgeQuestion(String id,String answer) throws IOException, DocumentException {
        return answerQuestionService.answerJudgeQuestion(id,answer);
    }

    @ResponseBody
    @RequestMapping(value="/answerSubjectiveCompletionQuestion",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String answerSubjectiveCompletionQuestion(String id,String[] answer) throws IOException, DocumentException {
        return answerQuestionService.answerSubjectiveCompletionQuestion(id, answer);
    }

    @ResponseBody
    @RequestMapping(value="/answerSubjectiveQuestion",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String answerSubjectiveQuestion(String id,String answer) throws IOException, DocumentException {
        return answerQuestionService.answerSubjectiveQuestion(id,answer);
    }






    public AnswerQuestionService getAnswerQuestionService() {
        return answerQuestionService;
    }

    public void setAnswerQuestionService(AnswerQuestionService answerQuestionService) {
        this.answerQuestionService = answerQuestionService;
    }
}
