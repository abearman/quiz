package quiz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryObject {
	
	private String dateString;
	private long timeElapsed;
	private double score;
	private String userName;
	private String quizName;
	private java.util.Date date;
	private DAL dal;

	public HistoryObject(String userName, Quiz quiz, DAL dal) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		date = new Date();
		dateString = dateFormat.format(date);
		this.timeElapsed = quiz.getLengthOfCompletion();
		this.score = quiz.getUsersScore();
		this.userName = userName;
		this.quizName = quiz.getQuizName();
		this.dal = dal;
		dal.addToHistoryListForUser(userName, quizName, score, timeElapsed, dateString, date);
	}
	
	public HistoryObject(String userName, String quizName, double score, long timeElapsed, String dateString, DAL dal) {
		this.dateString = dateString;
		this.timeElapsed = timeElapsed;
		this.score = score;
		this.userName = userName;
		this.quizName = quizName;
		this.dal = dal;
		dal.addToHistoryListForUser(userName, quizName, score, timeElapsed, dateString, date);
	}
	
	public String getDateString() {
		return dateString;
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
	
	public double getScore() {
		return score;
	}
	
	public String getQuizName() {
		return quizName;
	}
	
}
