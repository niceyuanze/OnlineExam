package com.onlineExam.controller.login;

import com.onlineExam.dao.impls.StudentDaoImpl;
import com.onlineExam.pojo.persons.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by niceyuanze on 16-12-14.
 */
@Controller
@RequestMapping("/login")
public class StudentLogin {

    @Autowired
    private StudentDaoImpl studentDaoImpl;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/studentLogin",method = RequestMethod.POST)
    @ResponseBody
    public String teacherLogin(String username, String password, HttpSession session){



        System.out.println(username);
        System.out.println(password);
        Student student =  studentDaoImpl.login(username,password);
        if(student != null){
            session.setAttribute("logined",true);
            session.setAttribute("id",student.getId());
            return "true";
        }else{
            return "false";
        }
    }


    @RequestMapping(value = "/studentDispatcher",method = RequestMethod.GET)
    public String teacherDispatcher(){
        return "back/student/student";
    }


    public StudentDaoImpl getStudentDaoImpl() {
        return studentDaoImpl;
    }

    public void setStudentDaoImpl(StudentDaoImpl studentDaoImpl) {
        this.studentDaoImpl = studentDaoImpl;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
