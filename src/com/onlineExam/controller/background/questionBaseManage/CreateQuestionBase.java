package com.onlineExam.controller.background.questionBaseManage;

import com.onlineExam.xml.TeacherXML;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by niceyuanze on 16-12-2.
 */
@Controller
@RequestMapping("/questionBaseManage")
public class CreateQuestionBase {

    @Autowired
    private TeacherXML teacherXML;

    @Autowired
    private HttpSession session;

    @Autowired
    private QuestionBaseManageService questionBaseManageService;


    @ResponseBody
    @RequestMapping(value="/createQuestionBase",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String createQuestionBase(String questionBaseName) throws IOException {
      String result = questionBaseManageService.createQuestionBase(questionBaseName);
//        System.out.println("sadfasdfsadf");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject.toString();
    }


    public TeacherXML getTeacherXML() {
        return teacherXML;
    }

    public void setTeacherXML(TeacherXML teacherXML) {
        this.teacherXML = teacherXML;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public QuestionBaseManageService getQuestionBaseManageService() {
        return questionBaseManageService;
    }

    public void setQuestionBaseManageService(QuestionBaseManageService questionBaseManageService) {
        this.questionBaseManageService = questionBaseManageService;
    }
}
