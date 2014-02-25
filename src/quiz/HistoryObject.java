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

	public HistoryObject(String userName, Quiz quiz) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		dateString = dateFormat.format(date);
		this.timeElapsed = quiz.getLengthOfCompletion();
		this.score = quiz.getUsersScore();
		this.userName = userName;
		this.quizName = quiz.getQuizName();
	}
	
	public String getDate() {
		return dateString;
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
