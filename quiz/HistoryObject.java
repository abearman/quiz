package quiz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryObject {
	
	private String dateString;
	private long timeElapsed;
	private double score;
	private String userName;

	public HistoryObject(String userName, Quiz quiz) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		dateString = dateFormat.format(date);
		this.timeElapsed = quiz.lengthOfCompletion();
		this.score = quiz.usersScore();
		this.userName = userName;
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
	
}
