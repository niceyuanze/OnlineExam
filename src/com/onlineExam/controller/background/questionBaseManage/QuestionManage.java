package com.onlineExam.controller.background.questionBaseManage;

import com.onlineExam.pojo.question.ChoiceQuestion;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by niceyuanze on 16-12-10.
 */

@Controller
@RequestMapping("/questionBaseManage/questionManage")
public class QuestionManage {
    @Autowired
    QuestionBaseManageService questionBaseManageService;


    @ResponseBody
    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getQuestions(int firstPage, int maxResult){
        System.out.println("已经到达获取题目位置");
        System.out.println(firstPage);
        System.out.println(maxResult);

        return questionBaseManageService.getQuestions(firstPage,maxResult);
    }

    @ResponseBody
    @RequestMapping(value = "/changeChoiceQuestion", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String changeChoiceQuestion(ChoiceQuestion choiceQuestion,int questionBaseId) throws DocumentException, IOException {
        System.err.println("到达修改位置");
        System.out.println("题库id"+questionBaseId);
        System.out.println(choiceQuestion.getId());
        questionBaseManageService.changeChoiceQuestion(choiceQuestion, questionBaseId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","选择题修改成功");
        return jsonObject.toString();
    }



    @ResponseBody
    @RequestMapping(value = "/getQuestion", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getQuestion(int id, String type) throws DocumentException {
        return questionBaseManageService.getChoiceQuestion(id);
    }








    public QuestionBaseManageService getQuestionBaseManageService() {
        return questionBaseManageService;
    }

    public void setQuestionBaseManageService(QuestionBaseManageService questionBaseManageService) {
        this.questionBaseManageService = questionBaseManageService;
    }
}
