package com.onlineExam.pojo.question;

import java.util.ArrayList;

/**
 * Created by niceyuanze on 16-12-6.
 */
public class SubjectiveCompletionQuestion {

    private int id;
    private String description;
    private int difficultyIndex;
    private String belongToQuestionBase;
    private int score;
    private ArrayList<String> answer;
    private int teacherScore;



    public SubjectiveCompletionQuestion() {
        answer = new ArrayList<>();
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

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
