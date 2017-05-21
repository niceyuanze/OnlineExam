package com.onlineExam.xml;

import com.onlineExam.pojo.question.*;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by niceyuanze on 16-12-12.
 */
@Component
public class ExamXML {

    private final String STRATEGY_BASE_PATH="/home/niceyuanze/desktop/OnlineExamBase/strategy/";


    private SAXReader sax;



    public ExamXML(){
        sax = new SAXReader();
    }

    public Strategy getStrategy(String path) throws DocumentException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);


        Strategy strategy = new Strategy();

        Element now = document.getRootElement();

        List<Element> questionBases = now.element("questionBaseIds").elements("questionBaseId");
        for(Element element:questionBases){
            strategy.getQuestionBaseId().add(Integer.valueOf(element.getText()));
        }

        List<Element> questions = now.element("questions").elements("question");
        for(Element element: questions){
            strategy.getPaperType().add(element.element("paperType").getText());
            strategy.getPaperDescription().add(element.elementText("paperDescription"));
            strategy.getQuestionType().add(element.elementText("questionType"));
            strategy.getDifficultyIndex1Count().add(Integer.valueOf(element.element("difficultyIndex1").attributeValue("count")));
            strategy.getDifficultyIndex1Score().add(Integer.valueOf(element.element("difficultyIndex1").attributeValue("score")));
            strategy.getDifficultyIndex2Count().add(Integer.valueOf(element.element("difficultyIndex2").attributeValue("count")));
            strategy.getDifficultyIndex2Score().add(Integer.valueOf(element.element("difficultyIndex2").attributeValue("score")));
            strategy.getDifficultyIndex3Count().add(Integer.valueOf(element.element("difficultyIndex3").attributeValue("count")));
            strategy.getDifficultyIndex3Score().add(Integer.valueOf(element.element("difficultyIndex3").attributeValue("score")));
        }
        return strategy;

    }



    public String generateStrategyXML(Strategy strategy) throws IOException {
        Document doc = DocumentHelper.createDocument();
        Element now = doc.addElement("paperStrategy");
        //添加课程id
        now.addElement("courseId").setText(strategy.getCourseId()+"");
        //添加题库

        Element questionBases = now.addElement("questionBaseIds");
        for(int id: strategy.getQuestionBaseId()){
            questionBases.addElement("questionBaseId").setText(id+"");
        }
        //添加大题
        Element questions = now.addElement("questions");
        for(int i = 0; i < strategy.getPaperDescription().size();i++){
            Element question = questions.addElement("question");
            question.addElement("paperType").setText(strategy.getPaperType().get(i));
            question.addElement("paperDescription").setText(strategy.getPaperDescription().get(i));
            question.addElement("questionType").setText(strategy.getQuestionType().get(i));
            question.addElement("difficultyIndex1")
                    .addAttribute("count", strategy.getDifficultyIndex1Count().get(i)+"")
                    .addAttribute("score", strategy.getDifficultyIndex1Score().get(i)+"");
            question.addElement("difficultyIndex2")
                    .addAttribute("count", strategy.getDifficultyIndex2Count().get(i)+"")
                    .addAttribute("score", strategy.getDifficultyIndex2Score().get(i)+"");
            question.addElement("difficultyIndex3")
                    .addAttribute("count", strategy.getDifficultyIndex3Count().get(i)+"")
                    .addAttribute("score", strategy.getDifficultyIndex3Score().get(i)+"");
        }
        String path = STRATEGY_BASE_PATH+strategy.getCourseId();
        createXML(path ,doc);
        return path;


    }

    public String addChoiceQuestions(String path, ArrayList<ChoiceQuestion> choiceQuestions, String description, String paperType) throws DocumentException, IOException {
        File xmlFile = new File(path);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement()
                .addElement("question")
                .addAttribute("type","选择题")
                .addAttribute("description",description)
                .addAttribute("paperType", paperType)
                .addAttribute("isSubjective","true");
        for(int i = 0; i < choiceQuestions.size();i++){
            Element choiceQuestion = now.addElement("choiceQuestion").addAttribute("id",i+"")
                    .addAttribute("score",choiceQuestions.get(i).getScore()+"")
                    .addAttribute("teacherScore","0");
            choiceQuestion.addElement("studentAnswer");
            choiceQuestion.addElement("description").addCDATA(choiceQuestions.get(i).getDescription());
            choiceQuestion.addElement("difficultyIndex").addCDATA(choiceQuestions.get(i).getDifficultyIndex()+"");


            Element options = choiceQuestion.addElement("options");
            System.out.println("刺客id"+choiceQuestions.get(i));
            for(String option: choiceQuestions.get(i).getOption()){
                options.addElement("option").addCDATA(option);
            }

            Element answers = choiceQuestion.addElement("answers");
            for(String answer: choiceQuestions.get(i).getAnswer()){
                answers.addElement("answer").addCDATA(answer);
            }
        }
        createXML(path,document);
        return path;
    }


    public String addJudgeQuestions(String path, ArrayList<JudgeQuestion> judgeQuestions, String description, String paperType) throws DocumentException, IOException {
        File xmlFile = new File(path);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement()
                .addElement("question")
                .addAttribute("type","判断题")
                .addAttribute("description",description)
                .addAttribute("paperType", paperType)
                .addAttribute("isSubjective","true");
        for(int i = 0; i < judgeQuestions.size();i++){
            Element judgeQuestion = now.addElement("judgeQuestion").addAttribute("id",i+"").addAttribute("score",judgeQuestions.get(i).getScore()+"").addAttribute("teacherScore","0");
            judgeQuestion.addElement("studentAnswer");
            judgeQuestion.addElement("description").addCDATA(judgeQuestions.get(i).getDescription());
            judgeQuestion.addElement("answer").setText(judgeQuestions.get(i).getAnswer()+"");
            judgeQuestion.addElement("difficultyIndex").setText(judgeQuestions.get(i).getDifficultyIndex()+"");
        }
        createXML(path,document);
        return path;
    }
    public String addSubjectiveCompletionQuestions(String path, ArrayList<SubjectiveCompletionQuestion> subjectiveCompletionQuestions, String description, String paperType) throws DocumentException, IOException {
        File xmlFile = new File(path);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement()
                .addElement("question")
                .addAttribute("type","主观填空题")
                .addAttribute("description",description)
                .addAttribute("paperType", paperType)
                .addAttribute("isSubjective","true");
        for(int i = 0; i < subjectiveCompletionQuestions.size();i++){
            Element subjectiveCompletionQuestion = now.addElement("subjectiveCompletionQuestion")
                    .addAttribute("id",i+"")
                    .addAttribute("score",subjectiveCompletionQuestions.get(i).getScore()+"")
                    .addAttribute("teacherScore","0");

            subjectiveCompletionQuestion.addElement("studentAnswer");

            subjectiveCompletionQuestion.addElement("description").addCDATA(subjectiveCompletionQuestions.get(i).getDescription());
            subjectiveCompletionQuestion.addElement("difficultyIndex").setText(subjectiveCompletionQuestions.get(i).getDifficultyIndex()+"");
        }
        createXML(path,document);
        return path;
    }
    public String addSubjectiveQuestions(String path, ArrayList<SubjectiveQuestion> subjectiveCompletionQuestions, String description, String paperType) throws DocumentException, IOException {
        File xmlFile = new File(path);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement()
                .addElement("question")
                .addAttribute("type","主观题")
                .addAttribute("description",description)
                .addAttribute("paperType", paperType)
                .addAttribute("isSubjective","true");

        for(int i = 0; i < subjectiveCompletionQuestions.size();i++){
            Element subjectiveQuestion = now.addElement("subjectiveQuestion")
                    .addAttribute("id",i+"")
                    .addAttribute("score",subjectiveCompletionQuestions.get(i).getScore()+"")
                    .addAttribute("teacherScore","0");
            subjectiveQuestion.addElement("studentAnswer");
            subjectiveQuestion.addElement("description").addCDATA(subjectiveCompletionQuestions.get(i).getDescription());
            subjectiveQuestion.addElement("difficultyIndex").setText(subjectiveCompletionQuestions.get(i).getDifficultyIndex()+"");
        }


        createXML(path,document);
        return path;
    }

    public Map<String,ArrayList> getPaper(String paperPath) throws DocumentException {
        Map<String, ArrayList> paperQuestions= new LinkedHashMap<>();
        paperQuestions.put("descriptions",new ArrayList());
        paperQuestions.put("paperTypes",new ArrayList());
        File xmlFile = new File(""+paperPath);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement();
        List<Element> questions = now.elements("question");
        for(int i = 0; i < questions.size(); i++){
            String questionType = questions.get(i).attributeValue("type");
            if(questionType.equals("选择题")){
                paperQuestions.get("descriptions").add(questions.get(i).attributeValue("description"));
                paperQuestions.get("paperTypes").add(questions.get(i).attributeValue("paperType"));
                paperQuestions.put("选择题",getChoiceQuestions(questions.get(i)));
            }else if(questionType.equals("判断题")){
                paperQuestions.get("descriptions").add(questions.get(i).attributeValue("description"));
                paperQuestions.get("paperTypes").add(questions.get(i).attributeValue("paperType"));
                paperQuestions.put("判断题", getJudgeQuestions(questions.get(i)));
            }else if(questionType.equals("主观填空题")){
                paperQuestions.get("descriptions").add(questions.get(i).attributeValue("description"));
                paperQuestions.get("paperTypes").add(questions.get(i).attributeValue("paperType"));
                paperQuestions.put("主观填空题", getSubjectiveCompletionQuestions(questions.get(i)));
            }else if(questionType.equals("主观题")){
                paperQuestions.get("descriptions").add(questions.get(i).attributeValue("description"));
                paperQuestions.get("paperTypes").add(questions.get(i).attributeValue("paperType"));
                paperQuestions.put("主观题", getSubjectiveQuestions(questions.get(i)));
            }
        }


        return paperQuestions;
    }
    public Map<String, ArrayList> getFinishedPaper(String paperPath) throws DocumentException {
        Map<String, ArrayList> paperQuestions= new LinkedHashMap<>();
        File xmlFile = new File(""+paperPath);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement();
        List<Element> questions = now.elements("question");
        for(int i = 0; i < questions.size(); i++){
            String questionType = questions.get(i).attributeValue("type");
           if(questionType.equals("主观填空题")){
                paperQuestions.put("主观填空题", getSubjectiveCompletionQuestions(questions.get(i)));
            }else if(questionType.equals("主观题")){
                paperQuestions.put("主观题", getSubjectiveQuestions(questions.get(i)));
            }
        }
        return paperQuestions;
    }

    private ArrayList<ChoiceQuestion> getChoiceQuestions(Element questionElement){
        ArrayList<ChoiceQuestion> choiceQuestions = new ArrayList<>();
        List<Element> elements = questionElement.elements("choiceQuestion");

        for(Element element:elements){
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setScore(Integer.valueOf(element.attributeValue("score")));
            choiceQuestion.setId(Integer.valueOf(element.attributeValue("id")));
            choiceQuestion.setTeacherScore(Integer.valueOf(element.attributeValue("teacherScore")));
            choiceQuestion.setDescription(element.elementText("description").replaceAll("<p>|</p>",""));
            choiceQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));

            choiceQuestion.setOption(new ArrayList<String>());
            List<Element> options = element.element("options").elements("option");
            for(Element option: options){
                choiceQuestion.getOption().add(option.getText().replaceAll("\t|\n","").replaceAll(" ",""));
            }
            choiceQuestion.setAnswer(new ArrayList<String>());
            List<Element> answers = element.element("studentAnswer").elements("answer");
            for(Element answer:answers){
                choiceQuestion.getAnswer().add(answer.getText());
            }
            choiceQuestion.setTeacherAnswer(new ArrayList<>());
            List<Element> teachersAnswers = element.element("answers").elements("answer");
            for(Element answer:teachersAnswers){
                choiceQuestion.getTeacherAnswer().add(answer.getText().toUpperCase());
            }

            choiceQuestions.add(choiceQuestion);
        }

        return choiceQuestions;
    }

    private ArrayList<JudgeQuestion> getJudgeQuestions(Element judgeElement){
        ArrayList<JudgeQuestion> judgeQuestions = new ArrayList<>();

        List<Element> elements = judgeElement.elements("judgeQuestion");

        elements.stream().forEach( element -> {

            JudgeQuestion judgeQuestion = new JudgeQuestion();
            judgeQuestion.setScore(Integer.valueOf(element.attributeValue("score")));
            judgeQuestion.setId(Integer.valueOf(element.attributeValue("id")));
            judgeQuestion.setTeacherScore(Integer.valueOf(element.attributeValue("teacherScore")));
            judgeQuestion.setDescription(element.elementText("description").replaceAll("<p>|</p>",""));
            judgeQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
            judgeQuestion.setTeacherAnswer(Boolean.valueOf(element.elementText("answer")));

            if(!(element.elementText("studentAnswer")=="")){
                judgeQuestion.setAnswer(Boolean.valueOf(element.elementText("studentAnswer")));
            }
            System.out.println(judgeQuestion.getAnswer());
//            System.err.println("高能预警"+(element.elementText("studentAnswer")==""));
//            if(element.element("studentAnswer") == null){
//
//            }else{
//
//            }
//            judgeQuestion.
//            if(!element.elementText("studentAnswer").equals("true") && !element.elementText("studentAnswer").equals("true")){
//                judgeQuestion.setAnswer(null);
//            }else{

//            }

//            System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+"   "+element.elementText("studentAnswer") +" " + judgeQuestion.getAnswer());

            judgeQuestions.add(judgeQuestion);

        });

        return judgeQuestions;
    }

    public ArrayList<SubjectiveCompletionQuestion> getSubjectiveCompletionQuestions(Element subjectiveCompletionElement){
        ArrayList<SubjectiveCompletionQuestion> subjectiveCompletionQuestions = new ArrayList<>();
        List<Element> elements = subjectiveCompletionElement.elements("subjectiveCompletionQuestion");


        elements.stream().forEach( element -> {
            SubjectiveCompletionQuestion subjectiveCompletionQuestion = new SubjectiveCompletionQuestion();

            List<Element> answers = element.element("studentAnswer").elements("answer");
            if(answers.size() != 0){
                for(Element answer:answers){
                    subjectiveCompletionQuestion.getAnswer().add(answer.getText());
                }
            }
            subjectiveCompletionQuestion.setTeacherScore(Integer.valueOf(element.attributeValue("teacherScore")));
            subjectiveCompletionQuestion.setScore(Integer.valueOf(element.attributeValue("score")));
            subjectiveCompletionQuestion.setId(Integer.valueOf(element.attributeValue("id")));
            subjectiveCompletionQuestion.setDescription(element.elementText("description")
                    .replaceAll("<p>|</p>|\t|\n","")
                    );//.replaceAll("\\[:place:\\]","<input type='text' name='answer'>")
            subjectiveCompletionQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
            subjectiveCompletionQuestions.add(subjectiveCompletionQuestion);

        });

        return  subjectiveCompletionQuestions;

    }
    public ArrayList<SubjectiveQuestion> getSubjectiveQuestions(Element subjectiveQuestionElement){
        ArrayList<SubjectiveQuestion> subjectiveQuestions = new ArrayList<>();
        List<Element> elements = subjectiveQuestionElement.elements("subjectiveQuestion");


        elements.stream().forEach( element -> {
            SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
            subjectiveQuestion.setScore(Integer.valueOf(element.attributeValue("score")));
            subjectiveQuestion.setId(Integer.valueOf(element.attributeValue("id")));
            subjectiveQuestion.setTeacherScore(Integer.valueOf(element.attributeValue("teacherScore")));
            subjectiveQuestion.setDescription(element.elementText("description")
                    );//.replaceAll("<p>|</p>|\t|\n","")
            subjectiveQuestion.setAnswer(element.elementText("studentAnswer"));
            subjectiveQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
            subjectiveQuestions.add(subjectiveQuestion);

        });

        return  subjectiveQuestions;

    }

    public boolean setChoiceQuestionAnswer(String path, String id, String answer) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        List<Element> questions = document.getRootElement().elements("question");
        for(Element question: questions){
            if(question.attributeValue("type").equals("选择题")){
                List<Element> choiceQuestions = question.elements("choiceQuestion");
                for(Element choiceQuestion: choiceQuestions){
                    if(choiceQuestion.attributeValue("id").equals(id)){
                        choiceQuestion.remove(choiceQuestion.element("studentAnswer"));
                        choiceQuestion = choiceQuestion.addElement("studentAnswer");
                        choiceQuestion.addElement("answer").setText(answer);
                    }
                }
            }
        }
        createXML(path,document);

        return true;
    }

    public boolean setJudgeQuestionAnswer(String path, String id, String answer) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        List<Element> questions = document.getRootElement().elements("question");
        for(Element question: questions){
            if(question.attributeValue("type").equals("判断题")){
                List<Element> choiceQuestions = question.elements("judgeQuestion");
                for(Element choiceQuestion: choiceQuestions){
                    if(choiceQuestion.attributeValue("id").equals(id)){
                        choiceQuestion.remove(choiceQuestion.element("studentAnswer"));
                        choiceQuestion.addElement("studentAnswer").setText(answer);
                    }
                }
            }
        }
        createXML(path,document);

        return true;
    }


    public boolean setSubjectiveCompletionAnswer(String path, String id, String answer[]) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        List<Element> questions = document.getRootElement().elements("question");
        for(Element question: questions){
            if(question.attributeValue("type").equals("主观填空题")){
                List<Element> choiceQuestions = question.elements("subjectiveCompletionQuestion");
                for(Element choiceQuestion: choiceQuestions){
                    if(choiceQuestion.attributeValue("id").equals(id)){
                        choiceQuestion.remove(choiceQuestion.element("studentAnswer"));
                        choiceQuestion = choiceQuestion.addElement("studentAnswer");
                        for(String string: answer){
                            choiceQuestion.addElement("answer").setText(string);
                        }

                    }
                }
            }
        }
        createXML(path,document);

        return true;
    }

    public boolean setSubjectiveAnswer(String path, String id, String answer) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        List<Element> questions = document.getRootElement().elements("question");
        for(Element question: questions){
            if(question.attributeValue("type").equals("主观题")){
                List<Element> choiceQuestions = question.elements("subjectiveQuestion");
                for(Element choiceQuestion: choiceQuestions){
                    if(choiceQuestion.attributeValue("id").equals(id)){
                        choiceQuestion.remove(choiceQuestion.element("studentAnswer"));
                        choiceQuestion = choiceQuestion.addElement("studentAnswer");
                        choiceQuestion.setText(answer);
                    }
                }
            }
        }
        createXML(path,document);

        return true;
    }
    public boolean setSubjectiveQuestionScore(String path, int id, int score) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        List<Element> questions = document.getRootElement().elements("question");
        for(Element question: questions){
            if(question.attributeValue("type").equals("主观题")){
                List<Element> choiceQuestions = question.elements("subjectiveQuestion");
                for(Element choiceQuestion: choiceQuestions){
                    System.out.println(choiceQuestion.attributeValue("id"));
                    if(choiceQuestion.attributeValue("id").equals(id+"")){
                        System.err.println("asdffffffffffffffffffffffffffffffffffffffZfffffff");
                        Attribute attribute = choiceQuestion.attribute("teacherScore");
                        attribute.setValue(score+"");
                    }
                }
            }
        }
        createXML(path,document);

        return true;
    }

    public boolean setSubjectiveCompletionQuestionScore(String path, int id, int score) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        List<Element> questions = document.getRootElement().elements("question");
        for(Element question: questions){
            if(question.attributeValue("type").equals("主观填空题")){
                List<Element> choiceQuestions = question.elements("subjectiveCompletionQuestion");
                for(Element choiceQuestion: choiceQuestions){
                    System.out.println(choiceQuestion.attributeValue("id"));
                    if(choiceQuestion.attributeValue("id").equals(id+"")){
                        System.err.println("asdfffffffffffffffffffffffffffffffffffffffffffff");
                        Attribute attribute = choiceQuestion.attribute("teacherScore");
                        attribute.setValue(score+"");
                    }
                }
            }
        }
        createXML(path,document);

        return true;
    }

    public int getPaperScore(String path) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        List<Element> questions = document.getRootElement().elements("question");
        int score=0;
        for(Element question: questions){
            if(question.attributeValue("type").equals("选择题")){
                score += getChoiceQuestionScore(question);
            }else if(question.attributeValue("type").equals("判断题")){
                score += getJudgeQuestionScore(question);
            }else if(question.attributeValue("type").equals("主观题")){
                score += getSubjectiveQuestionScore(question);
            }else if(question.attributeValue("type").equals("主观填空题")){
                score += getSubjectiveCompletionQuestionScore(question);
            }
        }
        createXML(path,document);

        return score;
    }

    public int getChoiceQuestionScore(Element choiceQuestionElement){
        List<Element> choiceQuestions = choiceQuestionElement.elements("choiceQuestion");
        int score = 0;
        for(Element choiceQuestion: choiceQuestions){
            List<Element> tmpElements = choiceQuestion
                    .element("studentAnswer")
                    .elements("answer");

            if(tmpElements.size() == 0){
                score+=0;
                continue;
            }

            ArrayList<String> studentAnswers = new ArrayList<>();
            for(Element answerElement:tmpElements){
                String string = answerElement.getText().toUpperCase();
                studentAnswers.add(string);
            }

            tmpElements = choiceQuestion
                    .element("answers")
                    .elements("answer");
            for(int i = 0; i < tmpElements.size();i++){
                String string = tmpElements.get(i).getText().toUpperCase();
                if(!studentAnswers.contains(string)){
                    score+=0;
                    break;
                }
                if(i == tmpElements.size() -1){
                    Attribute attribute = choiceQuestion.attribute("teacherScore");
                    attribute.setValue(choiceQuestion.attributeValue("score"));

                    System.out.println("-----------------------------------------------");
                    System.out.println(choiceQuestion.attributeValue("score"));
                    System.out.println("-------------------------------------");
                    score+=Integer.valueOf(choiceQuestion.attributeValue("score"));
                }
            }
        }

        return score;
    }
    public int getJudgeQuestionScore(Element judgeQuestionElement){
        List<Element> judgeQuestions = judgeQuestionElement.elements("judgeQuestion");
        int score = 0;
        for(Element element: judgeQuestions){

            String answer = element.element("answer").getText().toUpperCase();
            String studentAnswer = element.element("studentAnswer").getText().toUpperCase();
            if(answer.equals(studentAnswer)){
                Attribute attribute = element.attribute("teacherScore");
                attribute.setValue(element.attributeValue("score"));
                score += Integer.valueOf(element.attributeValue("score"));
            }

        }
        return score;
    }
    public int getSubjectiveQuestionScore(Element subjectiveQuestionElement){
        List<Element> subjectiveQuestions = subjectiveQuestionElement.elements("subjectiveQuestion");
        int score = 0;



        for(Element element: subjectiveQuestions){
            score += Integer.valueOf(element.attributeValue("teacherScore"));
        }


        return score;
    }
    public int getSubjectiveCompletionQuestionScore(Element subjectiveQuestionElement){
        List<Element> subjectiveQuestions = subjectiveQuestionElement.elements("subjectiveCompletionQuestion");
        int score = 0;



        for(Element element: subjectiveQuestions){
            score += Integer.valueOf(element.attributeValue("teacherScore"));
        }


        return score;
    }






    public String generateExamXml(String path) throws IOException {
        Document doc = DocumentHelper.createDocument();
        Element now = doc.addElement("paper");

        createXML(path,doc);
        return path;

    }


    private void createXML(String path, Document document) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setIndent(true);
        format.setIndent("	");
        format.setEncoding("UTF-8");
        File file = new File(path);
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        writer.write(document);
    }
}
