package com.onlineExam.pojo.persons;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by niceyuanze on 16-12-24.
 */
@Entity
public class Teacher {
    private Integer id;
    private String username;
    private String password;
    private String institute;
    private String teacherId;
    private String name;
    private String mobilePhone;
    private String position;
    private Set<QuestionBase> questionBases;
    private Set<Course> courses;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "institute", nullable = false, length = 20)
    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Basic
    @Column(name = "teacherId", nullable = false, length = 20)
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "mobilePhone", nullable = false, length = 20)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "position", nullable = false, length = 20)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != null ? !id.equals(teacher.id) : teacher.id != null) return false;
        if (username != null ? !username.equals(teacher.username) : teacher.username != null) return false;
        if (password != null ? !password.equals(teacher.password) : teacher.password != null) return false;
        if (institute != null ? !institute.equals(teacher.institute) : teacher.institute != null) return false;
        if (teacherId != null ? !teacherId.equals(teacher.teacherId) : teacher.teacherId != null) return false;
        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(teacher.mobilePhone) : teacher.mobilePhone != null) return false;
        if (position != null ? !position.equals(teacher.position) : teacher.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (institute != null ? institute.hashCode() : 0);
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "teacher")
    public Set<QuestionBase> getQuestionBases() {
        return questionBases;
    }

    public void setQuestionBases(Set<QuestionBase> questionBases) {
        this.questionBases = questionBases;
    }

    @OneToMany(mappedBy = "teacher")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
