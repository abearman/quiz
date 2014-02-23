package quiz;

public class TopScorer {
	
	/* Instance variables */
	private User user;
	private int numCorrectQuestions;
	private double timeTaken;
	
	/* Constructor */
	public TopScorer(User user, int numCorrect, double time) {
		this.user = user;
		this.numCorrectQuestions = numCorrect;
		this.timeTaken = time;
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
