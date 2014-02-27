package quiz;


public class TopScorer {
	
	/* Instance variables */
	//private User user;
	private String loginName;
	private int numCorrectQuestions;
	private double timeTaken;
	private DAL dal;
	
	/* Constructor */
	public TopScorer(String loginName, int numCorrect, double time, DAL dal) {
		this.loginName = loginName;
		//this.user = user;
		this.numCorrectQuestions = numCorrect;
		this.timeTaken = time;
		this.dal = dal;
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
