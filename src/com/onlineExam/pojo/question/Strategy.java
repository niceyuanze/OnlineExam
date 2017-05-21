package com.onlineExam.pojo.question;

import java.util.ArrayList;

/**
 * Created by niceyuanze on 16-12-12.
 */
public class Strategy {
    private int courseId;
    private ArrayList<Integer> questionBaseId;
    private ArrayList<String> paperDescription;//ArrayList<String>//ArrayList<Integer>
    private ArrayList<String> questionType;
    private ArrayList<String> paperType;
    private ArrayList<Integer> difficultyIndex1Count;
    private ArrayList<Integer> difficultyIndex1Score;

    private ArrayList<Integer> difficultyIndex2Count;
    private ArrayList<Integer> difficultyIndex2Score;

    private ArrayList<Integer> difficultyIndex3Count;
    private ArrayList<Integer> difficultyIndex3Score;

    public Strategy(){
          questionBaseId = new ArrayList<>();
          paperDescription = new ArrayList<>();
          questionType = new ArrayList<>();
          paperType = new ArrayList<>();
          difficultyIndex1Count = new ArrayList<>();
          difficultyIndex1Score = new ArrayList<>();

          difficultyIndex2Count = new ArrayList<>();
          difficultyIndex2Score = new ArrayList<>();

          difficultyIndex3Count = new ArrayList<>();
          difficultyIndex3Score = new ArrayList<>();
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public ArrayList<Integer> getQuestionBaseId() {
        return questionBaseId;
    }

    public void setQuestionBaseId(ArrayList<Integer> questionBaseId) {
        this.questionBaseId = questionBaseId;
    }

    public ArrayList<String> getPaperDescription() {
        return paperDescription;
    }

    public void setPaperDescription(ArrayList<String> paperDescription) {
        this.paperDescription = paperDescription;
    }

    public ArrayList<String> getQuestionType() {
        return questionType;
    }

    public void setQuestionType(ArrayList<String> questionType) {
        this.questionType = questionType;
    }

    public ArrayList<String> getPaperType() {
        return paperType;
    }

    public void setPaperType(ArrayList<String> paperType) {
        this.paperType = paperType;
    }

    public ArrayList<Integer> getDifficultyIndex1Count() {
        return difficultyIndex1Count;
    }

    public void setDifficultyIndex1Count(ArrayList<Integer> difficultyIndex1Count) {
        this.difficultyIndex1Count = difficultyIndex1Count;
    }

    public ArrayList<Integer> getDifficultyIndex1Score() {
        return difficultyIndex1Score;
    }

    public void setDifficultyIndex1Score(ArrayList<Integer> difficultyIndex1Score) {
        this.difficultyIndex1Score = difficultyIndex1Score;
    }

    public ArrayList<Integer> getDifficultyIndex2Count() {
        return difficultyIndex2Count;
    }

    public void setDifficultyIndex2Count(ArrayList<Integer> difficultyIndex2Count) {
        this.difficultyIndex2Count = difficultyIndex2Count;
    }

    public ArrayList<Integer> getDifficultyIndex2Score() {
        return difficultyIndex2Score;
    }

    public void setDifficultyIndex2Score(ArrayList<Integer> difficultyIndex2Score) {
        this.difficultyIndex2Score = difficultyIndex2Score;
    }

    public ArrayList<Integer> getDifficultyIndex3Count() {
        return difficultyIndex3Count;
    }

    public void setDifficultyIndex3Count(ArrayList<Integer> difficultyIndex3Count) {
        this.difficultyIndex3Count = difficultyIndex3Count;
    }

    public ArrayList<Integer> getDifficultyIndex3Score() {
        return difficultyIndex3Score;
    }

    public void setDifficultyIndex3Score(ArrayList<Integer> difficultyIndex3Score) {
        this.difficultyIndex3Score = difficultyIndex3Score;
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "courseId=" + courseId +
                ", questionBaseId=" + questionBaseId +
                ", paperDescription=" + paperDescription +
                ", questionType=" + questionType +
                ", paperType=" + paperType +
                ", difficultyIndex1Count=" + difficultyIndex1Count +
                ", difficultyIndex1Score=" + difficultyIndex1Score +
                ", difficultyIndex2Count=" + difficultyIndex2Count +
                ", difficultyIndex2Score=" + difficultyIndex2Score +
                ", difficultyIndex3Count=" + difficultyIndex3Count +
                ", difficultyIndex3Score=" + difficultyIndex3Score +
                '}';
    }
}
