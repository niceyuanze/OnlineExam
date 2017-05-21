package com.onlineExam.dao;

import com.onlineExam.pojo.persons.SelectCourse;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class Dao {
	private Connection connection;
	private Statement statement;
	private static final String URL="jdbc:mysql://127.0.0.1:3306/OnlineExam?characterEncoding=utf-8";
	private static final String USER="root";
	private static final String PASSWORD="Ze960311";
	
	public Dao() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		statement = connection.createStatement();
		
		
		
		
	}

	public void insert(SelectCourse selectCourse) throws SQLException{
		String sql = "insert into selectCourse(student_id,course_id,checkStatus,examStatus) values( "+"\'"+selectCourse.getStudent().getId()+"','"+selectCourse.getCourse().getId()+"','"+"2"+"'"+" ,'"+"1"+"')";
		System.out.println(sql);
		statement.execute(sql);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
}
