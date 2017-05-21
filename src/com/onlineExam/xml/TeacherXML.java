package com.onlineExam.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by niceyuanze on 16-12-2.
 */
@Component
public class TeacherXML {
    private SAXReader sax;

    public TeacherXML(){
        sax=new SAXReader();
    }

    public String generateQuestionBase(String path, Integer id,String questionBaseName) throws IOException {
        Document doc = DocumentHelper.createDocument();
        Element now = doc.addElement("questionBase");
        now.addElement("choiceQuestions");
        now.addElement("judgeQuestions");
        now.addElement("subjectiveCompletionQuestions");
        now.addElement("objectiveCompletionQuestons");
        now.addElement("subjectiveQuestions");


        String path1 = path + "/" + id + "/" + questionBaseName + ".xml";
        //实例化输出格式对象
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setIndent(true);
        format.setIndent("	");
        //设置输出编码
        format.setEncoding("UTF-8");
        //创建需要写入的File对象
        File file = new File(path + "/" + id + "/" + questionBaseName + ".xml");
        //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        //开始写入，write方法中包含上面创建的Document对象
        writer.write(doc);



        return path1;
    }

}
