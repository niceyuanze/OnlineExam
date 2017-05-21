package com.onlineExam.pojo.persons;

import javax.persistence.*;

/**
 * Created by niceyuanze on 16-12-24.
 */
@Entity
public class Question {
    private Integer id;
    private String type;
    private String description;
    private Integer difficultyIndex;
    private QuestionBase questionBase;

    @Id
    @Column(name = "Id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "difficultyIndex", nullable = false)
    public Integer getDifficultyIndex() {
        return difficultyIndex;
    }

    public void setDifficultyIndex(Integer difficultyIndex) {
        this.difficultyIndex = difficultyIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (type != null ? !type.equals(question.type) : question.type != null) return false;
        if (description != null ? !description.equals(question.description) : question.description != null)
            return false;
        if (difficultyIndex != null ? !difficultyIndex.equals(question.difficultyIndex) : question.difficultyIndex != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (difficultyIndex != null ? difficultyIndex.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "questionBase_id", referencedColumnName = "id", nullable = false)
    public QuestionBase getQuestionBase() {
        return questionBase;
    }

    public void setQuestionBase(QuestionBase questionBase) {
        this.questionBase = questionBase;
    }
}
