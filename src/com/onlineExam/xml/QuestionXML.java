package com.onlineExam.xml;

import com.onlineExam.pojo.persons.Question;
import com.onlineExam.pojo.question.ChoiceQuestion;
import com.onlineExam.pojo.question.JudgeQuestion;
import com.onlineExam.pojo.question.SubjectiveCompletionQuestion;
import com.onlineExam.pojo.question.SubjectiveQuestion;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niceyuanze on 16-12-4.
 */
@Component
public class QuestionXML {
    private SAXReader sax;

    public QuestionXML(){
        sax=new SAXReader();
    }
    public void addChoiceQuestion(ChoiceQuestion choiceQuestion) throws DocumentException, IOException {
        File xmlFile = new File(""+choiceQuestion.getBelongToQuestionBase());
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement().element("choiceQuestions");


        now = now.addElement("choiceQuestion").addAttribute("id",choiceQuestion.getId()+"");



        now.addElement("description").addCDATA(choiceQuestion.getDescription());
        now.addElement("difficultyIndex").addCDATA(choiceQuestion.getDifficultyIndex()+"");


        Element options = now.addElement("options");
        for(String option: choiceQuestion.getOption()){
            options.addElement("option").addCDATA(option);
        }

        Element answers = now.addElement("answers");
        for(String answer: choiceQuestion.getAnswer()){
            answers.addElement("answer").addCDATA(answer);
        }


        createXML(choiceQuestion.getBelongToQuestionBase(),document);
    }
    public void addJudgeQuestion(JudgeQuestion judgeQuestion) throws DocumentException, IOException {
        File xmlFile = new File(""+judgeQuestion.getBelongToQuestionBase());
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement().element("judgeQuestions");
        now = now.addElement("judgeQuestion").addAttribute("id",judgeQuestion.getId()+"");



        now.addElement("description").addCDATA(judgeQuestion.getDescription());
        now.addElement("answer").setText(judgeQuestion.getAnswer()+"");
        now.addElement("difficultyIndex").setText(judgeQuestion.getDifficultyIndex()+"");



        createXML(judgeQuestion.getBelongToQuestionBase(),document);


    }
    public void addSubjectiveQuestion(SubjectiveQuestion subjectiveQuestion) throws DocumentException, IOException {
        File xmlFile = new File(""+subjectiveQuestion.getBelongToQuestionBase());
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement().element("subjectiveQuestions");
        now = now.addElement("subjectiveQuestion").addAttribute("id",subjectiveQuestion.getId()+"");


        now.addElement("description").addCDATA(subjectiveQuestion.getDescription());
        now.addElement("difficultyIndex").addCDATA(subjectiveQuestion.getDifficultyIndex()+"");
        createXML(subjectiveQuestion.getBelongToQuestionBase(),document);

    }

    public void addSubjectiveCompletionQuestion(SubjectiveCompletionQuestion completionQuestion) throws DocumentException, IOException {
        File xmlFile = new File(completionQuestion.getBelongToQuestionBase());
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement().element("subjectiveCompletionQuestions");

        now = now.addElement("subjectiveCompletionQuestion").addAttribute("id",""+completionQuestion.getId());
        now.addElement("description").addCDATA(completionQuestion.getDescription());
        now.addElement("difficultyIndex").setText(completionQuestion.getDifficultyIndex()+"");
        createXML(completionQuestion.getBelongToQuestionBase(),document);
    }

    public Map<String, ArrayList> getQuestions(String path) throws DocumentException {
        Map<String, ArrayList> questions = new LinkedHashMap<>();
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement();
        List<Element> elements = now.elements();
        for(Element element:elements){
            if (element.getName().equals("choiceQuestions") && element.elements("choiceQuestion").size() != 0){
                questions.put("choiceQuestion",getChoiceQuesions(element));
            }else if(element.getName().equals("judgeQuestions") && element.elements("judgeQuestion").size() != 0){
                questions.put("judgeQuestion",getJudgeQuestions(element));
            }else if(element.getName().equals("subjectiveCompletionQuestions") && element.elements("subjectiveCompletionQuestion").size() != 0){
                questions.put("subjectiveCompletionQuestion",getSubjectiveCompletions(element));
            }else if(element.getName().equals("subjectiveQuestions") && element.elements("subjectiveQuestion").size() != 0){
                questions.put("subjectiveQuestion",getSubjectiveQuestions(element));
            }
        }




        return questions;
    }



    public ArrayList<ChoiceQuestion> getChoiceQuesions(Element choiceQuestionElement) throws DocumentException {
        ArrayList<ChoiceQuestion> choiceQuestions1 = new ArrayList<>();

        List<Element> choiceQuestions = choiceQuestionElement.elements("choiceQuestion");
        for(Element element: choiceQuestions){
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setId(Integer.valueOf(element.attributeValue("id")));
            choiceQuestion.setDescription(element.elementText("description").replaceAll("<p>|</p>",""));
            choiceQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
            choiceQuestion.setOption(new ArrayList<String>());
            List<Element> options = element.element("options").elements("option");
            for(Element option: options){
                choiceQuestion.getOption().add(option.getText());
            }
            choiceQuestion.setAnswer(new ArrayList<String>());
            List<Element> answers = element.element("answers").elements("answer");
            for(Element answer:answers){
                choiceQuestion.getAnswer().add(answer.getText().toUpperCase());
            }
            choiceQuestions1.add(choiceQuestion);
        }


        return choiceQuestions1;
    }





    public ArrayList<JudgeQuestion> getJudgeQuestions(Element judgeQuestionElement) throws DocumentException {
        ArrayList<JudgeQuestion> judgeQuestions1 = new ArrayList<>();

        List<Element> judgeQuestions = judgeQuestionElement.elements("judgeQuestion");

        for(Element element: judgeQuestions){
            JudgeQuestion judgeQuestion = new JudgeQuestion();
            judgeQuestion.setId((Integer.valueOf(element.attributeValue("id"))));
            judgeQuestion.setDescription(element.elementText("description").replaceAll("<p>|</p>",""));
            judgeQuestion.setAnswer(Boolean.valueOf(element.elementText("answer")));
            judgeQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
            judgeQuestions1.add(judgeQuestion);
        }

        return judgeQuestions1;

    }

    public ArrayList<SubjectiveCompletionQuestion> getSubjectiveCompletions(Element subjectiveCompletionElement) throws DocumentException {

        ArrayList<SubjectiveCompletionQuestion> subjectiveCompletionQuestions = new ArrayList<>();

        List<Element> judgeQuestions = subjectiveCompletionElement.elements("subjectiveCompletionQuestion");

        for(Element element: judgeQuestions){

            SubjectiveCompletionQuestion subjectiveCompletionQuestion = new SubjectiveCompletionQuestion();

            subjectiveCompletionQuestion.setId((Integer.valueOf(element.attributeValue("id"))));
            subjectiveCompletionQuestion.setDescription(element.elementText("description"));
            subjectiveCompletionQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));

            subjectiveCompletionQuestions.add(subjectiveCompletionQuestion);
        }



        return subjectiveCompletionQuestions;
    }

    public ArrayList<SubjectiveQuestion> getSubjectiveQuestions(Element subjectiveQuestionElement){
        ArrayList<SubjectiveQuestion> subjectiveQuestions1 = new ArrayList<>();

        List<Element> subjectiveQuestions = subjectiveQuestionElement.elements("subjectiveQuestion");
        for(Element element: subjectiveQuestions){
            SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
            subjectiveQuestion.setId((Integer.valueOf(element.attributeValue("id"))));
            subjectiveQuestion.setDescription(element.elementText("description"));
            subjectiveQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
            subjectiveQuestions1.add(subjectiveQuestion);
        }
        return subjectiveQuestions1;
    }



    //获取选择题
    public ChoiceQuestion getChoiceQuestion(String path,int id) throws DocumentException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        ChoiceQuestion choiceQuestion = new ChoiceQuestion();
        Element now = document.getRootElement().element("choiceQuestions");
        List<Element> choiceQuestions = now.elements("choiceQuestion");
        for(Element element: choiceQuestions){
            if(element.attributeValue("id").equals(id+"")){
                choiceQuestion.setId(Integer.valueOf(element.attributeValue("id")));
                choiceQuestion.setDescription(element.elementText("description"));
                choiceQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
                choiceQuestion.setOption(new ArrayList<String>());
                List<Element> options = element.element("options").elements("option");
                for(Element option: options){
                    choiceQuestion.getOption().add(option.getText());
                }
                choiceQuestion.setAnswer(new ArrayList<String>());
                List<Element> answers = element.element("answers").elements("answer");
                for(Element answer:answers){
                    choiceQuestion.getAnswer().add(answer.getText());
                }
                break;
            }
        }



        return choiceQuestion;

    }
    //获取判断题判断题
    public JudgeQuestion getJudgeQuestion(String path, int id) throws DocumentException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        JudgeQuestion judgeQuestion = new JudgeQuestion();
        Element now = document.getRootElement().element("judgeQuestions");
        List<Element> judgeQuestions = now.elements("judgeQuestion");
        for(Element element: judgeQuestions){
            if(element.attributeValue("id").equals(id+"")){
                judgeQuestion.setId(id);
                judgeQuestion.setDescription(element.elementText("description"));
                judgeQuestion.setAnswer(Boolean.valueOf(element.elementText("answer")));
                judgeQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
                judgeQuestion.setBelongToQuestionBase(path);
                break;
            }
        }
        return judgeQuestion;
    }
    //获取主观填空题
    public SubjectiveCompletionQuestion getSubjectiveCompletionQuestion(String path, int id) throws DocumentException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        SubjectiveCompletionQuestion subjectiveCompletionQuestion = new SubjectiveCompletionQuestion();

        Element now = document.getRootElement().element("subjectiveCompletionQuestions");
        List<Element> judgeQuestions = now.elements("subjectiveCompletionQuestion");

        for(Element element: judgeQuestions){
            if(element.attributeValue("id").equals(id+"")){
                subjectiveCompletionQuestion.setId(id);
                subjectiveCompletionQuestion.setDescription(element.elementText("description"));
                subjectiveCompletionQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
                subjectiveCompletionQuestion.setBelongToQuestionBase(path);
                break;
            }
        }



        return subjectiveCompletionQuestion;
    }
    //获取主观题
    public SubjectiveQuestion getSubjectiveQuestion(String path, int id) throws DocumentException {
        File xmlFile = new File(path);
        Document document = sax.read(xmlFile);
        SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();

        Element now = document.getRootElement().element("subjectiveQuestions");
        List<Element> subjectiveQuestions = now.elements("subjectiveQuestion");
        for(Element element: subjectiveQuestions){
            if(element.attributeValue("id").equals(id+"")){
                subjectiveQuestion.setId(id);
                subjectiveQuestion.setDescription(element.elementText("description"));
                subjectiveQuestion.setDifficultyIndex(Integer.valueOf(element.elementText("difficultyIndex")));
                break;
            }
        }
        return subjectiveQuestion;
    }
    //修改选择题
    public boolean changeChoiceQuestion(String path, ChoiceQuestion choiceQuestion) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement().element("choiceQuestions");
        List<Element> choiceQuestions = now.elements("choiceQuestion");
        for(Element element: choiceQuestions){
            System.out.println("-----------------");
            System.out.println(element.attributeValue("id"));
            System.out.println(choiceQuestion.getId());
            if(element.attributeValue("id").equals(choiceQuestion.getId()+"")){
                System.out.println("正确正确");
                System.out.println("asfsadfasdf"+element.element("description").getText());
                element.remove(element.element("description"));
                element.remove(element.element("difficultyIndex "));
                element.remove(element.element("options"));
                element.remove(element.element("answers"));
                now = element;
                now.addElement("description").addCDATA(choiceQuestion.getDescription());
                now.addElement("difficultyIndex").addCDATA(choiceQuestion.getDifficultyIndex()+"");


                Element options = now.addElement("options");
                for(String option: choiceQuestion.getOption()){
                    options.addElement("option").addCDATA(option);
                }

                Element answers = now.addElement("answers");
                for(String answer: choiceQuestion.getAnswer()){
                    answers.addElement("answer").addCDATA(answer);
                }


                break;
            }
        }
        createXML(path,document);
        return true;
    }
    public boolean deleteQuestion(String path, Question question) throws DocumentException, IOException {
        File xmlFile = new File(""+path);
        Document document = sax.read(xmlFile);
        Element now = document.getRootElement();
        List<Element> choiceQuestions = new ArrayList<>();
        if(question.getType().equals("选择题")){
            now = now.element("choiceQuestions");
            choiceQuestions = now.elements("choiceQuestion");
        }

        for(Element element: choiceQuestions){
            if(element.attributeValue("id").equals(question.getId()+"")){
                now.remove(element);
                break;
            }
        }
        createXML(path,document);
        return true;
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
