package com.onlineExam.controller.login;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import com.onlineExam.pojo.persons.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by niceyuanze on 16-11-29.
 */
@Controller
@RequestMapping("/login")
public class TeacherLogin {


    @Autowired
    private TeacherDaoImpl teacherDaoImpl;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/teacherLogin",method = RequestMethod.POST)
    @ResponseBody
    public String teacherLogin(String username, String password, HttpSession session){
        if(session.getAttribute("logined")!= null){
            return "true";
        }


        System.out.println(username);
        System.out.println(password);
        Teacher teacher =  teacherDaoImpl.login(username,password);
        if(teacher != null){
            session.setAttribute("logined",true);
            session.setAttribute("id",teacher.getId());
            session.setAttribute("position",teacher.getPosition());
            return "true";
        }else{
            return "false";
        }
    }


    @RequestMapping(value = "/teacherDispatcher",method = RequestMethod.GET)
    public String teacherDispatcher(){
        if(((String)session.getAttribute("position")).equals("教师")){
            return "back/teacher/teacher";

        }else if(((String)session.getAttribute("position")).equals("教务")){
            return "back/academic/academic";
        }
        return null;
    }




    public TeacherDaoImpl getTeacherDaoImpl() {
        return teacherDaoImpl;
    }

    public void setTeacherDaoImpl(TeacherDaoImpl teacherDaoImpl) {
        this.teacherDaoImpl = teacherDaoImpl;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
