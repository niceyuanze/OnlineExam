package com.onlineExam.controller.background.questionBaseManage;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import com.onlineExam.pojo.persons.QuestionBase;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by niceyuanze on 16-12-6.
 */
@Controller
@RequestMapping("/questionBaseManage/addQuestionBase")
public class AddQuestionBase {

    @Autowired
    private QuestionBaseManageService questionBaseManageService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TeacherDaoImpl teacherDao;

    @ResponseBody
    @RequestMapping(value="/changeQuestionBase",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String changeQuestionBase(QuestionBase questionBase){
        return questionBaseManageService.changeQuestionBase(questionBase);
    }

    @ResponseBody
    @RequestMapping(value="/createQuestionBase",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String getQuestionBaseTable(int firstPage,int maxResult){
        System.out.println("题库列表已经到达");
        System.out.println(firstPage);
        System.out.println(maxResult);
        return questionBaseManageService.getQuestionBasesTable(firstPage, maxResult);
    }

    @ResponseBody
    @RequestMapping(value="/deleteQuestionBase",method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String deleteQuestionBase(int id){
        System.out.println("已经进入删除");
        System.out.println(id);
        return  questionBaseManageService.deleteQuestionBase(id);
    }


    @ResponseBody
    @RequestMapping(value="/getQuestionsByQuestionBase",method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getQuestionsByQuestionBase(int id) throws DocumentException {
        System.out.println("获取题目");
        System.out.println(id);
        return  questionBaseManageService.getQuestionsByQuestionBase(id);
    }

    @RequestMapping(value="/getQuestionsDispatcher",method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String getQuestionsDispatcher(int id){
        request.setAttribute("questionBaseName", teacherDao.getQuestionBase(id).getName());
        request.setAttribute("questionBaseId", id);
        return "/questionBase/questions";
    }






    public QuestionBaseManageService getQuestionBaseManageService() {
        return questionBaseManageService;
    }

    public void setQuestionBaseManageService(QuestionBaseManageService questionBaseManageService) {
        this.questionBaseManageService = questionBaseManageService;
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public TeacherDaoImpl getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDaoImpl teacherDao) {
        this.teacherDao = teacherDao;
    }
}
