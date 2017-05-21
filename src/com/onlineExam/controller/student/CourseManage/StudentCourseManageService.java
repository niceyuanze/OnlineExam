package com.onlineExam.controller.student.CourseManage;

import com.onlineExam.dao.Dao;
import com.onlineExam.dao.impls.StudentDaoImpl;
import com.onlineExam.pojo.persons.Course;
import com.onlineExam.pojo.persons.SelectCourse;
import com.onlineExam.pojo.persons.Student;
import com.sun.javafx.image.IntPixelGetter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by niceyuanze on 16-12-21.
 */
@Component
public class StudentCourseManageService {

    @Autowired
    private StudentDaoImpl studentDaoImpl;

    @Autowired
    private HttpSession session;


    public String getCouldBeSelectedCourses(){
        Integer studentId= (Integer)session.getAttribute("id");

        List<Course> courses = studentDaoImpl.getCoubleBeSelectedCourses(studentId);

        JSONArray jsonArray = new JSONArray();

        for(Course course: courses) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", course.getId());
            jsonObject.put("name", course.getName());
            jsonObject.put("institute", course.getInstitute());
            jsonObject.put("major", course.getMajor());
            jsonObject.put("teacherName", course.getTeacher().getName());

            jsonArray.add(jsonObject);

        }

        return jsonArray.toString();

    }

    public String joinCourse(int course_id) throws SQLException, ClassNotFoundException {
        System.out.println("主人我们已经到达了");

        Integer studentId = (Integer)session.getAttribute("id");

        SelectCourse selectCourse = new SelectCourse();
        Course course = studentDaoImpl.getCourse(course_id);
        Student student = studentDaoImpl.getStudent(studentId);
        selectCourse.setCourse(course);
        selectCourse.setStudent(student);
        selectCourse.setPaperPath("");
        selectCourse.setStudentId(studentId);
        selectCourse.setCourseId(course_id);
        selectCourse.setCheckStatus(Byte.valueOf("0"));
        selectCourse.setExamStatus(Byte.valueOf("0"));
        selectCourse.setStudentId(student.getId());
        selectCourse.setCourseId(course.getId());
        Dao dao = new Dao();
        dao.insert(selectCourse);
//        studentDaoImpl.saveSelectCourse(selectCourse);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",111);


        return jsonObject.toString();
    }

    public String getMyHaveSelectCourses(){
        Integer studentId = (Integer) session.getAttribute("id");
        List<SelectCourse> selectCourses = studentDaoImpl.getMySelectCourses(studentId);
        JSONArray jsonArray = new JSONArray();
        for(SelectCourse selectCourse: selectCourses){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", selectCourse.getId());
            jsonObject.put("courseName", selectCourse.getCourse().getName());
            jsonObject.put("institute", selectCourse.getCourse().getInstitute());
            jsonObject.put("major", selectCourse.getCourse().getMajor());
            jsonObject.put("teacherName", selectCourse.getCourse().getTeacher().getName());
            jsonObject.put("status", selectCourse.getCheckStatus());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }




    public StudentDaoImpl getStudentDaoImpl() {
        return studentDaoImpl;
    }

    public void setStudentDaoImpl(StudentDaoImpl studentDaoImpl) {
        this.studentDaoImpl = studentDaoImpl;
    }
}
