package com.onlineExam.controller.background.questionBaseManage;

import com.onlineExam.pojo.question.ChoiceQuestion;
import com.onlineExam.pojo.question.JudgeQuestion;
import com.onlineExam.pojo.question.SubjectiveCompletionQuestion;
import com.onlineExam.pojo.question.SubjectiveQuestion;
import com.onlineExam.xml.QuestionXML;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by niceyuanze on 16-12-3.
 */
@Controller
@RequestMapping("/questionBaseManage/addQuestion")
public class AddQuestion {

    @Autowired
    private QuestionXML questionXML;

    @Autowired
    private QuestionBaseManageService questionBaseManageService;


    //获取题库的名字和地址
    @ResponseBody
    @RequestMapping(value="/getQuestionBases",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String getQuestionBase(){
        System.out.println("kaishileme?");
        return  questionBaseManageService.getQuestionBases();
    }




    @ResponseBody
    @RequestMapping(value="/addChoiceQuestion",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String addChoiceQuestion(ChoiceQuestion choiceQuestion, int questionBaseId) throws DocumentException, IOException {
        System.out.println();
        return questionBaseManageService.addChoiceQuestion(choiceQuestion,questionBaseId);
    }

    @ResponseBody
    @RequestMapping(value="/addJudgeQuestion",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String addJudgeQuestion(JudgeQuestion judgeQuestion, int questionBaseId) throws IOException, DocumentException {
       return questionBaseManageService.addJudgeQuestion(judgeQuestion,questionBaseId);

    }
    @ResponseBody
    @RequestMapping(value = "/addSubjectiveCompletionQuestion",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String addSubjectiveCompletionQuestion(SubjectiveCompletionQuestion subjectiveCompletionQuestion, int questionBaseId) throws IOException, DocumentException {
       return questionBaseManageService.addSubjectiveCompletionQuestion(subjectiveCompletionQuestion,questionBaseId);
    }





    @ResponseBody
    @RequestMapping(value = "/addSubjectiveQuestion",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String addSubjectiveQuestion(SubjectiveQuestion subjectiveQuestion, int questionBaseId) throws DocumentException, IOException {
       return questionBaseManageService.addSubjectiveQuestion(subjectiveQuestion, questionBaseId);

    }

    public QuestionXML getQuestionXML() {
        return questionXML;
    }

    public void setQuestionXML(QuestionXML questionXML) {
        this.questionXML = questionXML;
    }

    public QuestionBaseManageService getQuestionBaseManageService() {
        return questionBaseManageService;
    }

    public void setQuestionBaseManageService(QuestionBaseManageService questionBaseManageService) {
        this.questionBaseManageService = questionBaseManageService;
    }
}
