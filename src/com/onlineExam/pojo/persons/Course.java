package com.onlineExam.pojo.persons;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by niceyuanze on 16-12-24.
 */
@Entity
public class Course {
    private Integer id;
    private String name;
    private String institute;
    private String major;
    private Date startDate;
    private Date endDate;
    private Date examDate;
    private String strategy;
    private Teacher teacher;
    private Set<SelectCourse> selectCourses;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "institute", nullable = false, length = 20)
    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Basic
    @Column(name = "major", nullable = false, length = 20)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "startDate", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "endDate", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "examDate", nullable = true)
    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    @Basic
    @Column(name = "strategy", nullable = true, length = 200)
    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != null ? !id.equals(course.id) : course.id != null) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (institute != null ? !institute.equals(course.institute) : course.institute != null) return false;
        if (major != null ? !major.equals(course.major) : course.major != null) return false;
        if (startDate != null ? !startDate.equals(course.startDate) : course.startDate != null) return false;
        if (endDate != null ? !endDate.equals(course.endDate) : course.endDate != null) return false;
        if (examDate != null ? !examDate.equals(course.examDate) : course.examDate != null) return false;
        if (strategy != null ? !strategy.equals(course.strategy) : course.strategy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (institute != null ? institute.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (examDate != null ? examDate.hashCode() : 0);
        result = 31 * result + (strategy != null ? strategy.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "course")
    public Set<SelectCourse> getSelectCourses() {
        return selectCourses;
    }

    public void setSelectCourses(Set<SelectCourse> selectCourses) {
        this.selectCourses = selectCourses;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", major='" + major + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", examDate=" + examDate +
                ", strategy='" + strategy + '\'' +
                ", teacher=" + teacher +
                ", selectCourses=" + selectCourses +
                '}';
    }
}
