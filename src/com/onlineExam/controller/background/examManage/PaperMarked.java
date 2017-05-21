package com.onlineExam.controller.background.examManage;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by niceyuanze on 16-12-18.
 */
@Controller
@RequestMapping("/examManage/paperMarked")

public class PaperMarked {

    @Autowired
    private ExamManageService examManageService;

    @ResponseBody
    @RequestMapping(value="/getStudentPapers",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String getStudentPapers(int firstPage, int maxResult){
        return examManageService.getStudentPapers(firstPage, maxResult);
    }

    @ResponseBody
    @RequestMapping(value="/prepareMarkPaper",method = RequestMethod.POST ,produces = "text/json;charset=UTF-8")
    public String prepareMarkPaper(int id){
        return examManageService.pepareMarkPaper(id);
    }

    @RequestMapping(value="/markPaperDispatcher",method = RequestMethod.GET)
    public String markPaperDispatcher(){
        return "exam/mark";
    }

    @ResponseBody
    @RequestMapping(value="/beginMarkPaper",method = RequestMethod.GET ,produces = "text/json;charset=UTF-8")
    public String beginMarkPaper() throws DocumentException {
        return examManageService.beginMarkPaper();
    }

    @ResponseBody
    @RequestMapping(value="/setSubjectiveQuestionScore",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String setSubjectiveQuestionScore(int id, int score) throws IOException, DocumentException {
        return examManageService.setSubjectiveQuestionScore(id, score);
    }
    @ResponseBody
    @RequestMapping(value="/setSubjectiveCompletionQuestionScore",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String setSubjectiveCompletionQuestionScore(int id, int score) throws IOException, DocumentException {
        return examManageService.setSubjectiveCompletionQuestionScore(id, score);
    }

    @ResponseBody
    @RequestMapping(value="/getPaperScore",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String getPaperScore() throws IOException, DocumentException {
        return examManageService.getPaperScore();
    }

    @ResponseBody
    @RequestMapping(value="/setPaperScore",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String setPaperScore() throws IOException, DocumentException {
        return examManageService.setPaperScore();
    }

}
