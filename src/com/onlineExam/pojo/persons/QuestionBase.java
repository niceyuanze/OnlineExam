package com.onlineExam.pojo.persons;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by niceyuanze on 16-12-24.
 */
@Entity
public class QuestionBase {
    private Integer id;
    private String name;
    private Date creationDate;
    private Timestamp lastestChangeDate;
    private Integer difficultyIndex1;
    private Integer difficultyIndex2;
    private Integer difficultyIndex3;
    private String path;
    private Teacher teacher;
    private Set<Question> questions;

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
    @Column(name = "creationDate", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "lastestChangeDate", nullable = false)
    public Timestamp getLastestChangeDate() {
        return lastestChangeDate;
    }

    public void setLastestChangeDate(Timestamp lastestChangeDate) {
        this.lastestChangeDate = lastestChangeDate;
    }

    @Basic
    @Column(name = "difficultyIndex1", nullable = true)
    public Integer getDifficultyIndex1() {
        return difficultyIndex1;
    }

    public void setDifficultyIndex1(Integer difficultyIndex1) {
        this.difficultyIndex1 = difficultyIndex1;
    }

    @Basic
    @Column(name = "difficultyIndex2", nullable = true)
    public Integer getDifficultyIndex2() {
        return difficultyIndex2;
    }

    public void setDifficultyIndex2(Integer difficultyIndex2) {
        this.difficultyIndex2 = difficultyIndex2;
    }

    @Basic
    @Column(name = "difficultyIndex3", nullable = true)
    public Integer getDifficultyIndex3() {
        return difficultyIndex3;
    }

    public void setDifficultyIndex3(Integer difficultyIndex3) {
        this.difficultyIndex3 = difficultyIndex3;
    }

    @Basic
    @Column(name = "path", nullable = false, length = 200)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionBase that = (QuestionBase) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (lastestChangeDate != null ? !lastestChangeDate.equals(that.lastestChangeDate) : that.lastestChangeDate != null)
            return false;
        if (difficultyIndex1 != null ? !difficultyIndex1.equals(that.difficultyIndex1) : that.difficultyIndex1 != null)
            return false;
        if (difficultyIndex2 != null ? !difficultyIndex2.equals(that.difficultyIndex2) : that.difficultyIndex2 != null)
            return false;
        if (difficultyIndex3 != null ? !difficultyIndex3.equals(that.difficultyIndex3) : that.difficultyIndex3 != null)
            return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastestChangeDate != null ? lastestChangeDate.hashCode() : 0);
        result = 31 * result + (difficultyIndex1 != null ? difficultyIndex1.hashCode() : 0);
        result = 31 * result + (difficultyIndex2 != null ? difficultyIndex2.hashCode() : 0);
        result = 31 * result + (difficultyIndex3 != null ? difficultyIndex3.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "questionBase")
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
