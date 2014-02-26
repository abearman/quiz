package quiz;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryObject {
	
	private String dateString;
	private long timeElapsed;
	private double score;
	private String userName;
	private String quizName;
	private Date date;
	private DBConnection con;

	public HistoryObject(String userName, Quiz quiz, DBConnection con) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		dateString = dateFormat.format(date);
		this.timeElapsed = quiz.getLengthOfCompletion();
		this.score = quiz.getUsersScore();
		this.userName = userName;
		this.quizName = quiz.getQuizName();
		this.con = con;
		addToHistoryTable();
	}
	
	public HistoryObject(String userName, String quizName, double score, long timeElapsed, String dateString, DBConnection con) {
		this.dateString = dateString;
		this.timeElapsed = timeElapsed;
		this.score = score;
		this.userName = userName;
		this.quizName = quizName;
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
	
	private void addToHistoryTable() {
		Statement stmt = con.getStatement();
		//update MySQL database
		try {
			String update = "INSERT INTO histories VALUES(\"" + userName + "\",\"" + quizName + "\","
										+ "\"" + score + "\",\"" + timeElapsed + "\",\"" + dateString + "\")";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
