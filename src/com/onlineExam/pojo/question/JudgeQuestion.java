package com.onlineExam.pojo.question;

/**
 * Created by niceyuanze on 16-12-4.
 */
public class JudgeQuestion {
    private int id;
    private String description;
    private Boolean answer = null;
    private int difficultyIndex;
    private String belongToQuestionBase;
    private int score;
    private int teacherScore;
    private Boolean teacherAnswer;



    public JudgeQuestion() {
    }


    public int getTeacherScore() {
        return teacherScore;
    }

    public Boolean getTeacherAnswer() {
        return teacherAnswer;
    }

    public void setTeacherAnswer(Boolean teacherAnswer) {
        this.teacherAnswer = teacherAnswer;
    }

    public void setTeacherScore(int teacherScore) {
        this.teacherScore = teacherScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getBelongToQuestionBase() {
        return belongToQuestionBase;
    }

    public void setBelongToQuestionBase(String belongToQuestionBase) {
        this.belongToQuestionBase = belongToQuestionBase;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public int getDifficultyIndex() {
        return difficultyIndex;
    }

    public void setDifficultyIndex(int difficultyIndex) {
        this.difficultyIndex = difficultyIndex;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
