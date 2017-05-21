package com.onlineExam.pojo.question;

/**
 * Created by niceyuanze on 16-12-5.
 */
public class SubjectiveQuestion {
    private int id;
    private String description;
    private int difficultyIndex;
    private String belongToQuestionBase;
    private int score;
    private String answer;
    private int teacherScore;


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public SubjectiveQuestion() {
    }

    public int getTeacherScore() {
        return teacherScore;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficultyIndex() {
        return difficultyIndex;
    }

    public void setDifficultyIndex(int difficultyIndex) {
        this.difficultyIndex = difficultyIndex;
    }

    public String getBelongToQuestionBase() {
        return belongToQuestionBase;
    }

    public void setBelongToQuestionBase(String belongToQuestionBase) {
        this.belongToQuestionBase = belongToQuestionBase;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
