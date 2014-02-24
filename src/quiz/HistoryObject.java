package quiz;
import java.util.Date;

public class HistoryObject {
	
	/* Instance variables */
	private Date date;
	private double score;
	private double timeTaken;
	private String nameOfQuiz;
	
	/* Constructor */
	public HistoryObject(String quizName, double score, double timeTaken, Date date) {
		this.nameOfQuiz = quizName;
		this.score = score;
		this.timeTaken = timeTaken;
		this.date = date;
	}
	
	/* Getter methods */
	public Date getDate() {
		return this.date;
	}
	
	@SuppressWarnings("deprecation")
	public String getDateString() {
		return date.getMonth() + " " + date.getDay() + ", " + date.getYear() + " at" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
	}
	
	public double getScore() {
		return this.score;
	}
	
	public double getTimeTaken() {
		return this.timeTaken;
	}
	
	public String getNameOfQuiz() {
		return this.nameOfQuiz;
	}
	
	/* Setter methods */
	
	public void setDate() {
		this.date = new Date();
	}
	
	public void setScore(double newScore) {
		this.score = newScore;
	}
	
	public void setTimeTaken(double newTime) {
		this.timeTaken = newTime;
	}

}
