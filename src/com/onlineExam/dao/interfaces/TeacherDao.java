package com.onlineExam.dao.interfaces;

import com.onlineExam.pojo.persons.Teacher;

/**
 * Created by niceyuanze on 16-11-29.
 */
public interface TeacherDao {

    Teacher login(String username, String password);
}
