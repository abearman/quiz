package quiz;

public class NewsfeedObject {

	//Newsfeed object: String loginName, String action, int type, String quizName 
	
	/* Constants */
	
	public static final String CREATED_A_QUIZ_STRING = " created the quiz: ";
	public static final String TOOK_A_QUIZ_STRING = " took the quiz: ";
	public static final String EARNED_ACHIEVEMENT_STRING = " earned the achievement of: ";
	public static final String POSTED_STATUS_STRING = " posted the status: ";
	
	/* Instance variables */
	private String loginName;
	private String action;
	private boolean hasQuiz;
	private String quizName;
	private java.sql.Date date;
	
	/* Constructor */
	public NewsfeedObject(String loginName, String action, boolean hasQuiz, String quizName, java.sql.Date date) {
		this.loginName = loginName;
		this.action = action;
		this.hasQuiz = hasQuiz;
		this.quizName = quizName;
		this.date = date;
	}
	
	public String getLoginName() {
		return this.loginName;
	}
	
	public String getAction() {
		return this.action;
	}
	
	public boolean hasQuiz() {
		return this.hasQuiz;
	}
	
	public String getQuizName() {
		return this.quizName;
	}
	
	public java.sql.Date getDate() {
		return this.date;
	}
	
}
