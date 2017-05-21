package com.onlineExam.controller.student.ExamManage;

import com.onlineExam.dao.impls.StudentDaoImpl;
import com.onlineExam.pojo.persons.Question;
import com.onlineExam.pojo.persons.SelectCourse;
import com.onlineExam.pojo.question.*;
import com.onlineExam.xml.ExamXML;
import com.onlineExam.xml.QuestionXML;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by niceyuanze on 16-12-14.
 */

@Component
public class StudentExamManageService {

    private final String PAPERBASE="/home/niceyuanze/desktop/OnlineExamBase/paper/";

    @Autowired
    private HttpSession session;

    @Autowired
    private StudentDaoImpl studentDaoImpl;

    @Autowired
    private ExamXML examXML;

    @Autowired
    private QuestionXML questionXML;


    public String getMyExams(){
        Integer id = (Integer)session.getAttribute("id");
        System.out.println(id);
        System.out.println(studentDaoImpl);
        List<SelectCourse> selectCourses= studentDaoImpl.getExamCourses(id);

        JSONArray jsonArray = new JSONArray();

        for(SelectCourse selectCourse: selectCourses){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",selectCourse.getId());
            jsonObject.put("courseName", selectCourse.getCourse().getName());
            jsonObject.put("institute", selectCourse.getCourse().getInstitute());
            jsonObject.put("major", selectCourse.getCourse().getMajor());
            jsonObject.put("teacherName", selectCourse.getCourse().getTeacher().getName());
            jsonObject.put("examStatus",selectCourse.getExamStatus());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();


    }

    private void solveChoiceQuestion(List<Question> questions, int index, Strategy strategy, String path) throws DocumentException, IOException {
        int count1 = strategy.getDifficultyIndex1Count().get(index);
        int count2 = strategy.getDifficultyIndex2Count().get(index);
        int count3 = strategy.getDifficultyIndex3Count().get(index);
        int score1 = strategy.getDifficultyIndex1Score().get(index);
        int score2 = strategy.getDifficultyIndex2Score().get(index);
        int score3 = strategy.getDifficultyIndex3Score().get(index);
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);
        ArrayList<ChoiceQuestion> choiceQuestions = new ArrayList<>();
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()){
            Question question = iterator.next();
            System.out.println("---------------------------");
            System.out.println(count1);
            System.out.println(count2);
            System.out.println(count3);
            if(count1 == 0 && count2 == 0 && count3 == 0){
                break;
            }
            if(Math.random() < 0.5 && question.getDifficultyIndex() == 1 && count1 !=0){
                System.out.println("11111111111111");
                System.out.println(question.getId());
                ChoiceQuestion choiceQuestion = questionXML.getChoiceQuestion(question.getQuestionBase().getPath(),question.getId());
                choiceQuestion.setScore(score1);
                choiceQuestions.add(choiceQuestion);
                iterator.remove();
                count1--;
            }else if(Math.random() < 0.5 && question.getDifficultyIndex() == 2 && count2 !=0){
                System.out.println("222222222222222222");
                System.out.println(question.getId());
                ChoiceQuestion choiceQuestion = questionXML.getChoiceQuestion(question.getQuestionBase().getPath(),question.getId());
                choiceQuestion.setScore(score2);
                choiceQuestions.add(choiceQuestion);
                iterator.remove();
                count2--;
            }else if(Math.random() < 0.5 && question.getDifficultyIndex() == 3 && count3 !=0){
                System.out.println("33333333333333333333");
                System.out.println(question.getId());
                ChoiceQuestion choiceQuestion = questionXML.getChoiceQuestion(question.getQuestionBase().getPath(),question.getId());
                choiceQuestion.setScore(score3);
                choiceQuestions.add(choiceQuestion);
                iterator.remove();
                count3--;
            }

        }
        System.out.println(">???????????????????????????????");
        iterator = questions.iterator();

        if(count1 != 0 || count2 != 0 || count3 !=0){
            iterator = questions.iterator();
            while (iterator.hasNext()){
                Question question = iterator.next();
                System.out.println("---------------------------");
                System.out.println(count1);
                System.out.println(count2);
                System.out.println(count3);
                if(count1 == 0 && count2 == 0 && count3 == 0){
                    break;
                }
                if(question.getDifficultyIndex() == 1 && count1 !=0){
                    ChoiceQuestion choiceQuestion = questionXML.getChoiceQuestion(question.getQuestionBase().getPath(),question.getId());
                    choiceQuestion.setScore(score1);
                    choiceQuestions.add(choiceQuestion);
                    iterator.remove();
                    count1--;
                }else if( question.getDifficultyIndex() == 2 && count2 !=0){
                    ChoiceQuestion choiceQuestion = questionXML.getChoiceQuestion(question.getQuestionBase().getPath(),question.getId());
                    choiceQuestion.setScore(score2);
                    choiceQuestions.add(choiceQuestion);
                    iterator.remove();
                    count2--;
                }else if(question.getDifficultyIndex() == 3 && count3 !=0){
                    ChoiceQuestion choiceQuestion = questionXML.getChoiceQuestion(question.getQuestionBase().getPath(),question.getId());
                    choiceQuestion.setScore(score3);
                    choiceQuestions.add(choiceQuestion);
                    iterator.remove();
                    count3--;
                }

            }

        }

        examXML.addChoiceQuestions(path,choiceQuestions,strategy.getPaperDescription().get(index),strategy.getPaperType().get(index));

    }


    private void solveJudgeQuestion(List<Question> questions, int index, Strategy strategy, String path) throws DocumentException, IOException {
        int count1 = strategy.getDifficultyIndex1Count().get(index);
        int count2 = strategy.getDifficultyIndex2Count().get(index);
        int count3 = strategy.getDifficultyIndex3Count().get(index);
        int score1 = strategy.getDifficultyIndex1Score().get(index);
        int score2 = strategy.getDifficultyIndex2Score().get(index);
        int score3 = strategy.getDifficultyIndex3Score().get(index);
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);
        ArrayList<JudgeQuestion> judgeQuestions = new ArrayList<>();
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()){
            Question question = iterator.next();
            System.out.println("---------------------------");
            System.out.println(count1);
            System.out.println(count2);
            System.out.println(count3);
            if(count1 == 0 && count2 == 0 && count3 == 0){
                break;
            }
            if(Math.random() < 0.5 && question.getDifficultyIndex() == 1 && count1 !=0){
                System.out.println("11111111111111");
                System.out.println(question.getId());
                System.out.println(path);
                JudgeQuestion judgeQuestion = questionXML.getJudgeQuestion(question.getQuestionBase().getPath(),question.getId());
                judgeQuestion.setScore(score1);
                judgeQuestions.add(judgeQuestion);
                iterator.remove();
                count1--;
            }else if(Math.random() < 0.5 && question.getDifficultyIndex() == 2 && count2 !=0){
                System.out.println("2222222222222222222222222");
                System.out.println(question.getId());
                JudgeQuestion judgeQuestion = questionXML.getJudgeQuestion(question.getQuestionBase().getPath(),question.getId());
                judgeQuestion.setScore(score2);
                judgeQuestions.add(judgeQuestion);
                iterator.remove();
                count2--;

            }else if(Math.random() < 0.5 && question.getDifficultyIndex() == 3 && count3 !=0){
                System.out.println("3333333333333333333333333333");
                System.out.println(question.getId());
                JudgeQuestion judgeQuestion = questionXML.getJudgeQuestion(question.getQuestionBase().getPath(),question.getId());
                judgeQuestion.setScore(score3);
                judgeQuestions.add(judgeQuestion);
                iterator.remove();
                count3--;
            }

        }
        System.err.println("??????????????????????????????????????????");
        if(count1 != 0 || count2 != 0 || count3 !=0){
            iterator = questions.iterator();
            while (iterator.hasNext()){
                Question question = iterator.next();
                System.out.println("---------------------------");
                System.out.println(count1);
                System.out.println(count2);
                System.out.println(count3);
                if(count1 == 0 && count2 == 0 && count3 == 0){
                    break;
                }
                if( question.getDifficultyIndex() == 1 && count1 !=0){
                    System.out.println("x11111111111111");
                    System.out.println(question.getId());
                    JudgeQuestion judgeQuestion = questionXML.getJudgeQuestion(question.getQuestionBase().getPath(),question.getId());
                    judgeQuestion.setScore(score1);
                    judgeQuestions.add(judgeQuestion);
                    iterator.remove();
                    count1--;
                }else if( question.getDifficultyIndex() == 2 && count2 !=0){
                    System.out.println("x22222222222222222222");
                    System.out.println(question.getId());
                    JudgeQuestion judgeQuestion = questionXML.getJudgeQuestion(question.getQuestionBase().getPath(),question.getId());
                    judgeQuestion.setScore(score2);
                    judgeQuestions.add(judgeQuestion);
                    iterator.remove();
                    count2--;
                }else if( question.getDifficultyIndex() == 3 && count3 !=0){
                    System.out.println("xxxx333333333333333333333333");
                    System.out.println(question.getId());
                    JudgeQuestion judgeQuestion = questionXML.getJudgeQuestion(question.getQuestionBase().getPath(),question.getId());
                    judgeQuestion.setScore(score3);
                    judgeQuestions.add(judgeQuestion);
                    iterator.remove();
                    count3--;
                }

            }

        }
        examXML.addJudgeQuestions(path,judgeQuestions,strategy.getPaperDescription().get(index),strategy.getPaperType().get(index));

    }

    private void solveSubjectiveCompletionQuestions(List<Question> questions, int index, Strategy strategy, String path) throws DocumentException, IOException {
        int count1 = strategy.getDifficultyIndex1Count().get(index);
        int count2 = strategy.getDifficultyIndex2Count().get(index);
        int count3 = strategy.getDifficultyIndex3Count().get(index);
        int score1 = strategy.getDifficultyIndex1Score().get(index);
        int score2 = strategy.getDifficultyIndex2Score().get(index);
        int score3 = strategy.getDifficultyIndex3Score().get(index);
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);
        ArrayList<SubjectiveCompletionQuestion> subjectiveCompletionQuestions = new ArrayList<>();
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()){
            Question question = iterator.next();
            System.out.println("---------------------------");
            System.out.println(count1);
            System.out.println(count2);
            System.out.println(count3);
            if(count1 == 0 && count2 == 0 && count3 == 0){
                break;
            }
            if(Math.random() < 0.5 && question.getDifficultyIndex() == 1 && count1 !=0){
                System.out.println("11111111111111");
                System.out.println(question.getId());
                SubjectiveCompletionQuestion subjectiveCompletionQuestion = questionXML.getSubjectiveCompletionQuestion(question.getQuestionBase().getPath(),question.getId());
                subjectiveCompletionQuestion.setScore(score1);
                subjectiveCompletionQuestions.add(subjectiveCompletionQuestion);
                iterator.remove();
                count1--;
            }else if(Math.random() < 0.5 && question.getDifficultyIndex() == 2 && count2 !=0){
                System.out.println("2222222222222222222222222");
                System.out.println(question.getId());
                SubjectiveCompletionQuestion subjectiveCompletionQuestion = questionXML.getSubjectiveCompletionQuestion(question.getQuestionBase().getPath(),question.getId());
                subjectiveCompletionQuestion.setScore(score2);
                subjectiveCompletionQuestions.add(subjectiveCompletionQuestion);
                iterator.remove();
                count2--;

            }else if(Math.random() < 0.5 && question.getDifficultyIndex() == 3 && count3 !=0){
                System.out.println("3333333333333333333333333333");
                System.out.println(question.getId());
                SubjectiveCompletionQuestion subjectiveCompletionQuestion = questionXML.getSubjectiveCompletionQuestion(question.getQuestionBase().getPath(),question.getId());
                subjectiveCompletionQuestion.setScore(score3);
                subjectiveCompletionQuestions.add(subjectiveCompletionQuestion);
                iterator.remove();
                count3--;
            }

        }
        System.err.println("??????????????????????????????????????????");
        if(count1 != 0 || count2 != 0 || count3 !=0){
            iterator = questions.iterator();
            while (iterator.hasNext()){
                Question question = iterator.next();
                System.out.println("---------------------------");
                System.out.println(count1);
                System.out.println(count2);
                System.out.println(count3);
                if(count1 == 0 && count2 == 0 && count3 == 0){
                    break;
                }
                if( question.getDifficultyIndex() == 1 && count1 !=0){
                    System.out.println("x11111111111111");
                    System.out.println(question.getId());
                    SubjectiveCompletionQuestion subjectiveCompletionQuestion = questionXML.getSubjectiveCompletionQuestion(question.getQuestionBase().getPath(),question.getId());
                    subjectiveCompletionQuestion.setScore(score1);
                    subjectiveCompletionQuestions.add(subjectiveCompletionQuestion);
                    iterator.remove();
                    count1--;
                }else if( question.getDifficultyIndex() == 2 && count2 !=0){
                    System.out.println("x22222222222222222222");
                    System.out.println(question.getId());
                    SubjectiveCompletionQuestion subjectiveCompletionQuestion = questionXML.getSubjectiveCompletionQuestion(question.getQuestionBase().getPath(),question.getId());
                    subjectiveCompletionQuestion.setScore(score2);
                    subjectiveCompletionQuestions.add(subjectiveCompletionQuestion);
                    iterator.remove();
                    count2--;
                }else if( question.getDifficultyIndex() == 3 && count3 !=0){
                    System.out.println("xxxx333333333333333333333333");
                    System.out.println(question.getId());
                    SubjectiveCompletionQuestion subjectiveCompletionQuestion = questionXML.getSubjectiveCompletionQuestion(question.getQuestionBase().getPath(),question.getId());
                    subjectiveCompletionQuestion.setScore(score3);
                    subjectiveCompletionQuestions.add(subjectiveCompletionQuestion);
                    iterator.remove();
                    count3--;
                }

            }

        }
        examXML.addSubjectiveCompletionQuestions(path,subjectiveCompletionQuestions,strategy.getPaperDescription().get(index),strategy.getPaperType().get(index));
    }

    private void solveSubjectiveQuestions(List<Question> questions, int index, Strategy strategy, String path) throws DocumentException, IOException {
        int count1 = strategy.getDifficultyIndex1Count().get(index);
        int count2 = strategy.getDifficultyIndex2Count().get(index);
        int count3 = strategy.getDifficultyIndex3Count().get(index);
        int score1 = strategy.getDifficultyIndex1Score().get(index);
        int score2 = strategy.getDifficultyIndex2Score().get(index);
        int score3 = strategy.getDifficultyIndex3Score().get(index);
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);
        ArrayList<SubjectiveQuestion> subjectiveQuestions = new ArrayList<>();
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()){
            Question question = iterator.next();
            System.out.println("---------------------------");
            System.out.println(count1);
            System.out.println(count2);
            System.out.println(count3);
            if(count1 == 0 && count2 == 0 && count3 == 0){
                break;
            }
            if(Math.random() < 0.5 && question.getDifficultyIndex() == 1 && count1 !=0){
                System.out.println("11111111111111");
                System.out.println(question.getId());
                SubjectiveQuestion subjectiveQuestion = questionXML.getSubjectiveQuestion(question.getQuestionBase().getPath(),question.getId());
                subjectiveQuestion.setScore(score1);
                subjectiveQuestions.add(subjectiveQuestion);
                iterator.remove();
                count1--;
            }else if(Math.random() < 0.5 && question.getDifficultyIndex() == 2 && count2 !=0){
                System.out.println("2222222222222222222222222");
                System.out.println(question.getId());
                SubjectiveQuestion subjectiveQuestion = questionXML.getSubjectiveQuestion(question.getQuestionBase().getPath(),question.getId());
                subjectiveQuestion.setScore(score2);
                subjectiveQuestions.add(subjectiveQuestion);
                iterator.remove();
                count2--;

            }else if(Math.random() < 0.5 && question.getDifficultyIndex() == 3 && count3 !=0){
                System.out.println("3333333333333333333333333333");
                System.out.println(question.getId());
                SubjectiveQuestion subjectiveQuestion = questionXML.getSubjectiveQuestion(question.getQuestionBase().getPath(),question.getId());
                subjectiveQuestion.setScore(score3);
                subjectiveQuestions.add(subjectiveQuestion);
                iterator.remove();
                count3--;
            }

        }
        System.err.println("??????????????????????????????????????????");
        if(count1 != 0 || count2 != 0 || count3 !=0){
            iterator = questions.iterator();
            while (iterator.hasNext()){
                Question question = iterator.next();
                System.out.println("---------------------------");
                System.out.println(count1);
                System.out.println(count2);
                System.out.println(count3);
                if(count1 == 0 && count2 == 0 && count3 == 0){
                    break;
                }
                if( question.getDifficultyIndex() == 1 && count1 !=0){
                    System.out.println("x11111111111111");
                    System.out.println(question.getId());
                    SubjectiveQuestion subjectiveQuestion = questionXML.getSubjectiveQuestion(question.getQuestionBase().getPath(),question.getId());
                    subjectiveQuestion.setScore(score1);
                    subjectiveQuestions.add(subjectiveQuestion);
                    iterator.remove();
                    count1--;
                }else if( question.getDifficultyIndex() == 2 && count2 !=0){
                    System.out.println("x22222222222222222222");
                    System.out.println(question.getId());
                    SubjectiveQuestion subjectiveQuestion = questionXML.getSubjectiveQuestion(question.getQuestionBase().getPath(),question.getId());
                    subjectiveQuestion.setScore(score2);
                    subjectiveQuestions.add(subjectiveQuestion);
                    iterator.remove();
                    count2--;
                }else if( question.getDifficultyIndex() == 3 && count3 !=0){
                    System.out.println("xxxx333333333333333333333333");
                    System.out.println(question.getId());
                    SubjectiveQuestion subjectiveQuestion = questionXML.getSubjectiveQuestion(question.getQuestionBase().getPath(),question.getId());
                    subjectiveQuestion.setScore(score3);
                    subjectiveQuestions.add(subjectiveQuestion);
                    iterator.remove();
                    count3--;
                }

            }

        }
        examXML.addSubjectiveQuestions(path,subjectiveQuestions,strategy.getPaperDescription().get(index),strategy.getPaperType().get(index));
    }





    public String prepareExam(int id) throws DocumentException, IOException {
        SelectCourse selectCourse = studentDaoImpl.getSelectCouse(id);
        System.out.println(selectCourse.getCourse().getStrategy());
        Strategy strategy = examXML.getStrategy(selectCourse.getCourse().getStrategy());
        System.out.println(strategy);
        String paperPath = PAPERBASE+selectCourse.getId()+"paper";
        examXML.generateExamXml(paperPath);
        System.err.println("--------------------------------------------------------");
        for(int i = 0; i < strategy.getQuestionType().size();i++){
            if(strategy.getQuestionType().get(i).equals("选择题")){
                solveChoiceQuestion(studentDaoImpl.getQuestionByQuestionBases(strategy.getQuestionBaseId(),"选择题"),i,strategy,paperPath);
            }else if (strategy.getQuestionType().get(i).equals("判断题")){
                solveJudgeQuestion(studentDaoImpl.getQuestionByQuestionBases(strategy.getQuestionBaseId(),"判断题"),i,strategy,paperPath);
            }else if(strategy.getQuestionType().get(i).equals("主观填空题")){
                solveSubjectiveCompletionQuestions(studentDaoImpl.getQuestionByQuestionBases(strategy.getQuestionBaseId(),"主观填空题"),i,strategy,paperPath);
            }else if(strategy.getQuestionType().get(i).equals("主观题")){
                solveSubjectiveQuestions(studentDaoImpl.getQuestionByQuestionBases(strategy.getQuestionBaseId(),"主观题"),i,strategy,paperPath);
            }
        }
        studentDaoImpl.setPaperPath(id,paperPath);
        studentDaoImpl.setExamStatus(id,2);



//        for(Question question: studentDaoImpl.getQuestionByQuestionBases(strategy.getQuestionBaseId(),)){
//            System.out.println(question);
//        }

//        String answerPath = PAPERBASE+selectCourse.getId()+"answper";
//        JSONArray jsonArray = new JSONArray();
//

//        List<Question> questions = studentDaoImpl.getQuestionByQuestionBases(strategy.getQuestionBaseId());


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","准备成功");
        return jsonObject.toString();

    }
    public String getPaper() throws DocumentException {
        Integer id = (Integer)session.getAttribute("id");
        SelectCourse selectCourse = studentDaoImpl.getSelectCourseByStatus(id,2).get(0);
        System.out.println("试卷地址为"+selectCourse.getPaperPath());



        Map<String, ArrayList> paper = examXML.getPaper(selectCourse.getPaperPath());
        System.out.println(paper);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacher",selectCourse.getCourse().getTeacher().getName());
        jsonObject.put("courseName",selectCourse.getCourse().getName());
        for(String key: paper.keySet()){
            if(key.equals("选择题")){
                jsonObject.put(key, choiceJSON(paper.get(key)));
            }else if(key.equals("判断题")){
                jsonObject.put(key, judgeJSON(paper.get(key)));
            }else if(key.equals("主观填空题")){
                jsonObject.put(key, subjectiveCompletionJSON(paper.get(key)));
            }else if(key.equals("主观题")){
                jsonObject.put(key, subjectiveJSON(paper.get(key)));
            }else if(key.equals("descriptions")){
                jsonObject.put(key,paper.get(key));
            }else if(key.equals("paperTypes")){
                jsonObject.put(key,paper.get(key));
            }
        }


        return jsonObject.toString();


    }

    public JSONArray choiceJSON(ArrayList<ChoiceQuestion> choiceQuestions){
        JSONArray jsonArray = new JSONArray();
        for(ChoiceQuestion choiceQuestion: choiceQuestions){

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",choiceQuestion.getId());
            jsonObject.put("difficultyIndex",choiceQuestion.getDifficultyIndex());
            jsonObject.put("belongToQuestionBase", choiceQuestion.getBelongToQuestionBase());
            jsonObject.put("description",choiceQuestion.getDescription());
            jsonObject.put("option",choiceQuestion.getOption());
            jsonObject.put("answer",choiceQuestion.getAnswer());
            jsonObject.put("score", choiceQuestion.getScore());

            jsonArray.add(jsonObject);
        }


        return jsonArray;
    }
    public JSONArray judgeJSON(ArrayList<JudgeQuestion> judgeQuestions){
        JSONArray jsonArray = new JSONArray();

        for(JudgeQuestion judgeQuestion: judgeQuestions){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id",judgeQuestion.getId());
            jsonObject.put("difficultyIndex", judgeQuestion.getDifficultyIndex());
            jsonObject.put("description", judgeQuestion.getDescription());
            jsonObject.put("answer",judgeQuestion.getAnswer());
            jsonObject.put("score",judgeQuestion.getScore());
            System.out.println(judgeQuestion.getAnswer());

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    public JSONArray subjectiveCompletionJSON(ArrayList<SubjectiveCompletionQuestion> subjectiveCompletionQuestions){
        JSONArray jsonArray = new JSONArray();
        for(SubjectiveCompletionQuestion subjectiveCompletionQuestion: subjectiveCompletionQuestions){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id",subjectiveCompletionQuestion.getId());
            jsonObject.put("description", subjectiveCompletionQuestion.getDescription());
            jsonObject.put("difficultyIndex", subjectiveCompletionQuestion.getDifficultyIndex());
            jsonObject.put("answer",subjectiveCompletionQuestion.getAnswer());
            jsonObject.put("score", subjectiveCompletionQuestion.getScore());
            jsonArray.add(jsonObject);
        }


        return  jsonArray;
    }
    public JSONArray subjectiveJSON(ArrayList<SubjectiveQuestion> subjectiveQuestions){
        JSONArray jsonArray = new JSONArray();

        for(SubjectiveQuestion subjectiveQuestion: subjectiveQuestions){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id",subjectiveQuestion.getId());

            jsonObject.put("difficultyIndex", subjectiveQuestion.getDifficultyIndex());
            jsonObject.put("description", subjectiveQuestion.getDescription());
            jsonObject.put("answer", subjectiveQuestion.getAnswer());
            jsonObject.put("score", subjectiveQuestion.getScore());
            jsonArray.add(jsonObject);


        }
        return jsonArray;
    }


    public String submitPaper(){
        Integer studentId = (Integer)session.getAttribute("id");
        SelectCourse selectCourse = studentDaoImpl.getSelectCourseByStatus(studentId,2).get(0);
        studentDaoImpl.setExamStatus(selectCourse.getId(),3);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","提交成功");
        return jsonObject.toString();
    }


    public QuestionXML getQuestionXML() {
        return questionXML;
    }

    public void setQuestionXML(QuestionXML questionXML) {
        this.questionXML = questionXML;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public ExamXML getExamXML() {
        return examXML;
    }

    public void setExamXML(ExamXML examXML) {
        this.examXML = examXML;
    }

    public StudentDaoImpl getStudentDaoImpl() {
        return studentDaoImpl;
    }

    public void setStudentDaoImpl(StudentDaoImpl studentDaoImpl) {
        this.studentDaoImpl = studentDaoImpl;
    }

    public HttpSession getHttpSession() {
        return session;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.session = httpSession;
    }
}
