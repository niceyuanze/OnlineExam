package com.onlineExam.controller.academic;

import com.onlineExam.dao.impls.AcademicDaoImpl;
import com.onlineExam.pojo.persons.Course;
import com.onlineExam.pojo.persons.Teacher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by niceyuanze on 16-12-26.
 */
@Component
public class AcademicService {
    @Autowired
    private AcademicDaoImpl academicDao;


    public String getTeachers(){
        List<Teacher> teachers = academicDao.getTeachers();
        JSONArray jsonArray = new JSONArray();
        for(Teacher teacher: teachers){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", teacher.getId());
            jsonObject.put("name", teacher.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();

    }

    public String getCourses(){
        List<Course> courses = academicDao.getCourses();
        JSONArray jsonArray = new JSONArray();

        for(Course course: courses){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", course.getId());
            jsonObject.put("name", course.getName());
            jsonObject.put("institute", course.getInstitute());
            jsonObject.put("major", course.getMajor());
            jsonObject.put("teacherName", course.getTeacher().getName());
            if(course.getExamDate() == null){
                jsonObject.put("examDate","");

            }else{
                jsonObject.put("examDate", course.getExamDate().toString());

            }
            jsonArray.add(jsonObject);


        }

        return  jsonArray.toString();

    }



    public AcademicDaoImpl getAcademicDao() {
        return academicDao;
    }

    public void setAcademicDao(AcademicDaoImpl academicDao) {
        this.academicDao = academicDao;
    }
}
