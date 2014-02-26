package quiz;

public class TopScorer {
	
	/* Instance variables */
	private User user;
	private int numCorrectQuestions;
	private double timeTaken;
	private DBConnection conn;
	
	/* Constructor */
	public TopScorer(User user, int numCorrect, double time, DBConnection conn) {
		this.user = user;
		this.numCorrectQuestions = numCorrect;
		this.timeTaken = time;
		this.conn = conn;
	}
	
	/* Getter methods*/
	
	public User getUser() {
		return this.user;
	}
	
	public int getNumCorrectQuestions() {
		return this.numCorrectQuestions;
	}
	
	public double getTimeTaken() {
		return this.timeTaken;
	}
	
}
