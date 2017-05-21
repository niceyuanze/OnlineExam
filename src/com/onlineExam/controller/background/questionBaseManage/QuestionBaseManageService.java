package com.onlineExam.controller.background.questionBaseManage;

import com.onlineExam.dao.impls.TeacherDaoImpl;
import com.onlineExam.pojo.persons.Question;
import com.onlineExam.pojo.persons.QuestionBase;
import com.onlineExam.pojo.persons.Teacher;
import com.onlineExam.pojo.question.ChoiceQuestion;
import com.onlineExam.pojo.question.JudgeQuestion;
import com.onlineExam.pojo.question.SubjectiveCompletionQuestion;
import com.onlineExam.pojo.question.SubjectiveQuestion;
import com.onlineExam.xml.QuestionXML;
import com.onlineExam.xml.TeacherXML;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by niceyuanze on 16-12-2.
 */
@Component
public class QuestionBaseManageService {

    @Autowired
    private TeacherXML teacherXML;

    @Autowired
    private HttpSession session;

    @Autowired
    private TeacherDaoImpl teacherDaoImpl;

    @Autowired
    private QuestionXML questionXML;

    /*
    * 在question table添加问题
    * 添加选择题
    * 添加判断题
    * 添加主观题
    *
    *
    *
    *
    *
    *
     */


    //-----------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------添加题目区
    //添加问题


    //在question table添加问题
    public String addQuestion(Question question){
        teacherDaoImpl.saveQuestion(question);
        return "success";
    }
    //添加选择题
    public String addChoiceQuestion(ChoiceQuestion choiceQuestion, int questionBaseId) throws DocumentException, IOException {
        //准备阶段
        Question question = new Question();
        question.setDescription(choiceQuestion.getDescription());
        question.setDifficultyIndex(choiceQuestion.getDifficultyIndex());
        question.setType("选择题");
        Teacher teacher = new Teacher();
        teacher.setId((Integer)session.getAttribute("id"));
        QuestionBase questionBase = new QuestionBase();
        questionBase.setId(questionBaseId);
//        question.setTeacher(teacher);
        question.setQuestionBase(questionBase);
        //在question表中添加问题呢，然后返回的id进行赋值

        choiceQuestion.setId(teacherDaoImpl.saveQuestion(question));
        questionXML.addChoiceQuestion(choiceQuestion);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","选择题上传成功");
        return jsonObject.toString();
    }


        //添加判断题
    public String addJudgeQuestion(JudgeQuestion judgeQuestion, int questionBaseId) throws IOException, DocumentException {
        //存储准备
        Question question = new Question();
        question.setDescription(judgeQuestion.getDescription());
        question.setDifficultyIndex(judgeQuestion.getDifficultyIndex());
        question.setType("判断题");
        Teacher teacher = new Teacher();
        teacher.setId((Integer)session.getAttribute("id"));
        QuestionBase questionBase = new QuestionBase();
        questionBase.setId(questionBaseId);
//        question.setTeacher(teacher);
        question.setQuestionBase(questionBase);


        //在question表中添加问题呢，然后返回的id进行赋值
        judgeQuestion.setId(teacherDaoImpl.saveQuestion(question));
        //在xml中添加问题
        questionXML.addJudgeQuestion(judgeQuestion);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","判断题上传成功");
        return jsonObject.toString();
    }
    //添加主观填空题
    public String addSubjectiveCompletionQuestion(SubjectiveCompletionQuestion subjectiveCompletionQuestion, int questionBaseId) throws IOException, DocumentException {
        //存储准备
        Question question = new Question();
        question.setDescription(subjectiveCompletionQuestion.getDescription());
        question.setDifficultyIndex(subjectiveCompletionQuestion.getDifficultyIndex());
        question.setType("主观填空题");
        Teacher teacher = new Teacher();
        teacher.setId((Integer)session.getAttribute("id"));
        QuestionBase questionBase = new QuestionBase();
        questionBase.setId(questionBaseId);
//        question.setTeacher(teacher);
        question.setQuestionBase(questionBase);


        //在question表中添加问题呢，然后返回的id进行赋值
        subjectiveCompletionQuestion.setId(teacherDaoImpl.saveQuestion(question));
        //在xml中添加问题
        questionXML.addSubjectiveCompletionQuestion(subjectiveCompletionQuestion);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","主观填空题上传成功");
        return jsonObject.toString();
    }
    //添加主观题
    public String addSubjectiveQuestion(SubjectiveQuestion subjectiveQuestion, int questionBaseId) throws DocumentException, IOException {
        //存储准备
        Question question = new Question();
        question.setDescription(subjectiveQuestion.getDescription());
        question.setDifficultyIndex(subjectiveQuestion.getDifficultyIndex());
        question.setType("主观题");
        Teacher teacher = new Teacher();
        teacher.setId((Integer)session.getAttribute("id"));
        QuestionBase questionBase = new QuestionBase();
        questionBase.setId(questionBaseId);
//        question.setTeacher(teacher);
        question.setQuestionBase(questionBase);


        //在question表中添加问题呢，然后返回的id进行赋值
        subjectiveQuestion.setId(teacherDaoImpl.saveQuestion(question));
        //在xml中添加问题
        questionXML.addSubjectiveQuestion(subjectiveQuestion);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","主观题上传成功");
        return jsonObject.toString();

    }





        //-------------------------------------------------------------------------------------------------------------------------------------------



    public String createQuestionBase(String questionBaseName) throws IOException {


        Integer id = (Integer) session.getAttribute("id");
        String path = teacherXML.generateQuestionBase("/home/niceyuanze/desktop/OnlineExamBase/questionBase", id, questionBaseName);

        QuestionBase questionBase = new QuestionBase();
        questionBase.setName(questionBaseName);
        Teacher teacher = new Teacher();
        teacher.setId(id);
        questionBase.setTeacher(teacher);
        questionBase.setCreationDate(new Date(System.currentTimeMillis()));
        questionBase.setLastestChangeDate(new Timestamp(System.currentTimeMillis()));
        questionBase.setDifficultyIndex1(0);
        questionBase.setDifficultyIndex2(0);
        questionBase.setDifficultyIndex3(0);
        questionBase.setPath(path);

        teacherDaoImpl.saveQuestionBase(questionBase);


//        questionBase.setCreationDate(new Date());

//        System.out.println(questionBaseName);



        return "创建成功";
    }






    public String getQuestionBases(){
        Integer id = (Integer) session.getAttribute("id");
        List<QuestionBase> questionBases = teacherDaoImpl.getQuestionBases(id,0,Integer.MAX_VALUE);

        if(questionBases.size()==0){
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result","false");
            jsonArray.add(jsonObject);
            System.out.println("没有啊");
            return jsonArray.toString();
        }else {
            JSONArray jsonArray = new JSONArray();
            for(QuestionBase questionBase: questionBases){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(questionBase.getPath()+"&questionBaseId="+questionBase.getId(),questionBase.getName());
                jsonArray.add(jsonObject);
            }
            return jsonArray.toString();
        }
    }

    //获取题库
    public String getQuestionBasesTable(int firstResult,int maxResult){
        Integer id = (Integer)session.getAttribute("id");
        List<QuestionBase> questionBases = getTeacherDaoImpl().getQuestionBases(id,firstResult,maxResult);

        if(questionBases.size() == 0){
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "false");
            jsonArray.add(jsonObject);
            return jsonArray.toString();
        }else{
            JSONArray jsonArray = new JSONArray();
            System.out.println("总记录数:"+teacherDaoImpl.getQuestionInt(id));
            for(QuestionBase questionBase: questionBases){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",questionBase.getId());
                jsonObject.put("name",questionBase.getName());



                jsonObject.put("creationDate", questionBase.getCreationDate().toString());
                jsonObject.put("lastestChangeDate", questionBase.getLastestChangeDate().toString());
                jsonObject.put("questionCount",(questionBase.getDifficultyIndex1()+questionBase.getDifficultyIndex1()+questionBase.getDifficultyIndex1())+"");
                jsonObject.put("difficultyIndexRatio",questionBase.getDifficultyIndex1()+":"+questionBase.getDifficultyIndex2()+":"+questionBase.getDifficultyIndex3());
                jsonObject.put("path", questionBase.getPath());
                jsonArray.add(jsonObject);

            }
            JSONObject jsonObject = new JSONObject();
            int all = teacherDaoImpl.getQuestionInt(id);
            if(all % maxResult == 0){
                jsonObject.put("tablePages",all / maxResult);
            }else{
                jsonObject.put("tablePages",all / maxResult + 1);
            }
            jsonArray.add(jsonObject);

            return jsonArray.toString();
        }

    }
    //修改题库

    public String changeQuestionBase(QuestionBase questionBase){
        System.out.println("已经进行题库修改");
        System.out.println(questionBase.getId());
        System.out.println(questionBase.getName());
        teacherDaoImpl.changeQuestionBase(questionBase);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","修改成功");
        return jsonObject.toString();
    }

    //删除题库
    public String deleteQuestionBase(int id){
        teacherDaoImpl.deleteQuestionBase(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","删除成功");
        return jsonObject.toString();
    }
    //------------------------------------------------------------------------------------------------

    //根据用户提供的页码获取问题
    public String getQuestions(int firstPage, int maxResult) {
        System.err.println("已经进入到service方法");

        Integer id = (Integer)session.getAttribute("id");
        List<Question> questions = teacherDaoImpl.getQuestions(id, firstPage, maxResult);

        JSONArray jsonArray = new JSONArray();

        for(Question question: questions){
//            System.out.println(question);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",question.getId());
            jsonObject.put("questionBase_name",question.getQuestionBase().getName());
            jsonObject.put("type",question.getType());
            jsonObject.put("description", question.getDescription());

            jsonObject.put("difficultyIndex",question.getDifficultyIndex()+"");
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }
    public String getChoiceQuestion(int id) throws DocumentException {
        System.out.println("冶金来了");
        Question question = teacherDaoImpl.getQuestion(id);
        ChoiceQuestion choiceQuestion = questionXML.getChoiceQuestion(question.getQuestionBase().getPath(),id);
        System.out.println(question.getQuestionBase().getPath());
        System.out.println(choiceQuestion.getDescription());
        System.out.println(choiceQuestion.getDifficultyIndex());
        for(String string: choiceQuestion.getOption()){
            System.out.println("选项"+string);
        }
        for(String string: choiceQuestion.getAnswer()){
            System.out.println("答案"+string);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("description",choiceQuestion.getDescription());
        jsonObject.put("difficultyIndex",choiceQuestion.getDifficultyIndex());
        jsonObject.put("option",choiceQuestion.getOption());
        jsonObject.put("answer",choiceQuestion.getAnswer());
        jsonObject.put("questionBase_Name",question.getQuestionBase().getPath()+"&questionBaseId="+question.getQuestionBase().getId());
        return jsonObject.toString();
    }
    public String changeChoiceQuestion(ChoiceQuestion choiceQuestion, int questionBaseId) throws DocumentException, IOException {
        Question question = teacherDaoImpl.getQuestion(choiceQuestion.getId());
        if(question.getQuestionBase().getId() == questionBaseId){
            System.out.println("题库没变呀卧槽");
            questionXML.changeChoiceQuestion(question.getQuestionBase().getPath(),choiceQuestion);
            question.setDescription(choiceQuestion.getDescription());
            question.setDifficultyIndex(choiceQuestion.getDifficultyIndex());
        }else{
            QuestionBase questionBase = teacherDaoImpl.getQuestionBase(questionBaseId);
            questionXML.deleteQuestion(question.getQuestionBase().getPath(),question);
            question.setDescription(choiceQuestion.getDescription());
            question.setDifficultyIndex(choiceQuestion.getDifficultyIndex());
            question.setQuestionBase(questionBase);
            questionXML.addChoiceQuestion(choiceQuestion);
        }
        System.out.println(question.getId());
        teacherDaoImpl.changeQuestion(question);
        return null;
    }


    public String getQuestionsByQuestionBase(int id) throws DocumentException {
        QuestionBase questionBase = teacherDaoImpl.getQuestionBase(id);

        Map<String,ArrayList> questions = questionXML.getQuestions(questionBase.getPath());
        JSONObject jsonObject = new JSONObject();
        for(String key: questions.keySet()){
            jsonObject.put(key,questions.get(key));
        }

        return jsonObject.toString();
    }










        public QuestionXML getQuestionXML() {
        return questionXML;
    }

    public void setQuestionXML(QuestionXML questionXML) {
        this.questionXML = questionXML;
    }

    public TeacherDaoImpl getTeacherDaoImpl() {
        return teacherDaoImpl;
    }

    public void setTeacherDaoImpl(TeacherDaoImpl teacherDaoImpl) {
        this.teacherDaoImpl = teacherDaoImpl;
    }

    public TeacherXML getTeacherXML() {
        return teacherXML;
    }

    public void setTeacherXML(TeacherXML teacherXML) {
        this.teacherXML = teacherXML;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
