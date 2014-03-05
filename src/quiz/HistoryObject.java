package quiz;

import java.util.Date;

public class HistoryObject {
	
	private long timeElapsed;
	private int numQuestionsCorrect;
	private String userName;
	private String quizName;
	private java.util.Date date;
	private DAL dal;

	public HistoryObject(String userName, Quiz quiz, DAL dal) {
		date = new Date();
		this.timeElapsed = quiz.getLengthOfCompletion();
		this.numQuestionsCorrect = quiz.getNumQuestionsCorrect();
		this.userName = userName;
		this.quizName = quiz.getQuizName();
		this.dal = dal;
		dal.addToHistoryListForUser(userName, quizName, numQuestionsCorrect, timeElapsed, date);
	}
	
	public HistoryObject(String userName, String quizName, int numQuestionsCorrect, long timeElapsed, java.util.Date dateValue, DAL dal) {
		this.date = dateValue;
		this.timeElapsed = timeElapsed;
		this.numQuestionsCorrect = numQuestionsCorrect;
		this.userName = userName;
		this.quizName = quizName;
		this.dal = dal;
	}

	public java.util.Date getDate(){
		return date; 
	}
	
	public long getElapsedTime() {
		return timeElapsed;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getNumQuestionsCorrect(){
		return numQuestionsCorrect;
	}
	
	public String getQuizName() {
		return quizName;
	}
	
}
