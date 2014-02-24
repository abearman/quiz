package quiz;

public class FriendRecentActivity {

	/* Instance variables */
	private String friendName;
	private int recentAchievement;
	private String recentlyCreatedQuiz;
	private String recentlyTakenQuiz;
	
	/* Constructor */
	public FriendRecentActivity(String friendName) {
		this.friendName = friendName; 
		this.recentAchievement = -1;
		this.recentlyCreatedQuiz = null;
		this.recentlyTakenQuiz = null;
	}
	
	/* Getter methods */
	
	/* Returns the index of the most recent achievement (0 through 5), or -1
	 * if the friend has not achieved anything */
	public int getRecentAchievement() {
		return this.recentAchievement; 
	}
	
	/* Returns a String representing the name of the Quiz the friend most recently
	 * created, or null if they haven't created any Quizzes */
	public String getRecentlyCreatedQuiz() {
		return this.recentlyCreatedQuiz;
	}
	
	/* Returns a String representing the name of the Quiz the friend most recently
	 * took, or null if they haven't taken any Quizzes */
	public String getRecentlyTakenQuiz() {
		return this.recentlyTakenQuiz;
	}
	
	/* Setter methods */
	
	public void setRecentAchievement(int achievement) {
		this.recentAchievement = achievement;
	}
	
	public void setRecentlyCreatedQuiz(String recentlyCreatedQuiz) {
		this.recentlyCreatedQuiz = recentlyCreatedQuiz;
	}
	
	public void setRecentlyTakenQuiz(String recentlyTakenQuiz) {
		this.recentlyTakenQuiz = recentlyTakenQuiz;
	}
	
}
