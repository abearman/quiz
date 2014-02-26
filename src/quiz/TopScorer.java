package quiz;

import java.sql.Statement;

public class TopScorer {
	
	/* Instance variables */
	//private User user;
	private String loginName;
	private int numCorrectQuestions;
	private double timeTaken;
	private DBConnection conn;
	private Statement stmt;
	
	/* Constructor */
	public TopScorer(String loginName, int numCorrect, double time, DBConnection conn) {
		this.loginName = loginName;
		//this.user = user;
		this.numCorrectQuestions = numCorrect;
		this.timeTaken = time;
		this.conn = conn;
		this.stmt = conn.getStatement();
	}
	
	/* Getter methods*/
	
	/*
	public User getUser() {
		return this.user;
	}*/
	
	public String getLoginName(){
		return loginName;
	}
	
	public int getNumCorrectQuestions() {
		return this.numCorrectQuestions;
	}
	
	public double getTimeTaken() {
		return this.timeTaken;
	}
	
}
