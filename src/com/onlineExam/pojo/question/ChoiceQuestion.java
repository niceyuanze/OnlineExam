package com.onlineExam.pojo.question;

import java.util.ArrayList;

/**
 * Created by niceyuanze on 16-11-23.
 */
public class ChoiceQuestion {
    private int id;
    private int difficultyIndex;
    private String belongToQuestionBase;
    private String description;
    private ArrayList<String> option;
    private ArrayList<String> answer;
    private ArrayList<String> teacherAnswer;
    private int score;
    private int teacherScore;


    public int getTeacherScore() {
        return teacherScore;
    }

    public void setTeacherScore(int teacherScore) {
        this.teacherScore = teacherScore;
    }

    public ChoiceQuestion(){

    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBelongToQuestionBase() {
        return belongToQuestionBase;
    }

    public void setBelongToQuestionBase(String belongToQuestionBase) {
        this.belongToQuestionBase = belongToQuestionBase;
    }

    public int getDifficultyIndex() {
        return difficultyIndex;
    }

    public void setDifficultyIndex(int difficultyIndex) {
        this.difficultyIndex = difficultyIndex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getOption() {
        return option;
    }

    public void setOption(ArrayList<String> option) {
        this.option = option;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<String> getTeacherAnswer() {
        return teacherAnswer;
    }

    public void setTeacherAnswer(ArrayList<String> teacherAnswer) {
        this.teacherAnswer = teacherAnswer;
    }

    @Override
    public String toString() {
        return "ChoiceQuestion{" +
                "id=" + id +
                ", difficultyIndex=" + difficultyIndex +
                ", belongToQuestionBase='" + belongToQuestionBase + '\'' +
                ", description='" + description + '\'' +
                ", option=" + option +
                ", answer=" + answer +
                '}';
    }
}
