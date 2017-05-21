package com.onlineExam.controller.background.statistic;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import com.onlineExam.pojo.persons.SelectCourse;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by niceyuanze on 16-12-24.
 */
@Controller
@RequestMapping("/statistic/ScoreList")
public class ScoreList {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TeacherDaoImpl teacherDao;



    @ResponseBody
    @RequestMapping(value = "getStudentScores", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getStudentScores(){
        return statisticService.getStudentScores();
    }

    @RequestMapping(value = "getHaveMarkedPaperDispatcher", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getHaveMarkedPaperDispatcher(int id){
        SelectCourse selectCourse = teacherDao.getSelectCourseById(id);
        request.setAttribute("paperId",id);
        request.setAttribute("paperScore", selectCourse.getScore());
        request.setAttribute("paperName", selectCourse.getCourse().getName());
        request.setAttribute("teacherName", selectCourse.getCourse().getTeacher().getName());

        return "questionBase/paper";
    }



    @ResponseBody
    @RequestMapping(value = "getHaveMarkedPaper", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getHaveMarkedPaper(int id) throws DocumentException {
        return statisticService.getHaveMarkedPaper(id);
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

    public StatisticService getStatisticService() {
        return statisticService;
    }

    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }
}
