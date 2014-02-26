package quiz;
import java.util.*;
import java.sql.Statement;


public class Quiz {
	
	public static final int TOPSCORER_MAX = 5;
	
	//instance variables
	
	//stay the same across sessions, stored in database
	private String quizName;
	private String quizLink;
	private String descriptionOfQuiz;
	private boolean isRandom;
	private boolean isMultiplePage;
	private boolean isImmediateCorrection;
	private boolean canBeTakenInPracticeMode;
	private ArrayList<Question> questions;
	private ArrayList<HistoryObject> allHistories;
	
	//different across sessions
	private long lengthOfCompletion;
	private double usersScore;
	
	//updated across different sessions, stored in database
	private ArrayList<TopScorer> topScorers;
	
	//connect to database
	private DBConnection con;
	private Statement stmt;
	
	private void initializeArrayLists(){
		questions = new ArrayList<Question>();
		topScorers = new ArrayList<TopScorer>();
		allHistories = new ArrayList<HistoryObject>();
	}
	
	private void setupDB(DBConnection con){
		this.con = con;
		this.stmt = con.getStatement();
	}
	
	//constructor for creating a quiz, adds quiz to database
	public Quiz(DBConnection con){
		initializeArrayLists();
		setupDB(con);
		
	}
	
	//constructor for taking a quiz, handles querying of database
	public Quiz(DBConnection con, String quizName){
		initializeArrayLists();
		setupDB(con);
		
	}
	
	
	/* Getters */
	
	public String getQuizName(){
		return quizName;
	}
	
	public String getQuizLink() {
		return quizLink;
	}
	
	public String getDescriptionOfQuiz(){
		return descriptionOfQuiz;
	}
	
	public boolean isRandom(){
		return isRandom;
	}
	
	public boolean isMultiplePage(){
		return isMultiplePage;
	}
	
	public boolean isImmediateCorrection(){
		return isImmediateCorrection;
	}
	
	public boolean canBeTakenInPracticeMode(){
		return canBeTakenInPracticeMode;
	}
	
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	
	public long getLengthOfCompletion(){
		return lengthOfCompletion;
	}
	
	public double getUsersScore(){
		return usersScore;
	}
	
	public ArrayList<TopScorer> getTopScorers(){
		return topScorers;
	}
	
	public ArrayList<HistoryObject> getAllHistories() {
		return allHistories;
	}
	
	/* Setters */
	
	public void setQuizName(String quizName){
		this.quizName = quizName;
	}
	
	public void setQuizLink(String quizLink) {
		this.quizLink = quizLink;
	}
	
	public void setDescriptionOfQuiz(String descriptionOfQuiz){
		this.descriptionOfQuiz = descriptionOfQuiz;
	}
	
	public void setIsRandom(boolean isRandom){
		this.isRandom = isRandom;
	}
	
	public void setIsMultiplePage(boolean isMultiplePage){
		this.isMultiplePage = isMultiplePage;
	}
	
	public void setIsImmediateCorrection(boolean isImmediateCorrection){
		this.isImmediateCorrection = isImmediateCorrection;
	}
	
	public void setCanBeTakenInPracticeMode(boolean canBeTakenInPracticeMode){
		this.canBeTakenInPracticeMode = canBeTakenInPracticeMode;
	}
	
	public void setLengthOfCompletion(long lengthOfCompletion){
		this.lengthOfCompletion = lengthOfCompletion;
	}
	
	public void setUsersScore(double usersScore){
		this.usersScore = usersScore;
	}
	
	public void addQuestion(Question question){
		questions.add(question);
	}
	
	public void removeQuestion(Question question){
		if (questions.contains(question)){
			questions.remove(question);
		}
	}
	
	public void addTopScorer(TopScorer topScorer){
		topScorers.add(topScorer);
		sortTopScorers();
		
		//cap top scorers at 5
		int numExtra = topScorers.size() - TOPSCORER_MAX;
		if (numExtra > 0){
			for (int i = 0; i < numExtra; i++){
				this.removeTopScorer(topScorers.get(TOPSCORER_MAX+i));
			}
		}
	}
	
	public void removeTopScorer(TopScorer topScorer){
		if (topScorers.contains(topScorer)){
			topScorers.remove(topScorer);
		}
	}
	
	/** Sort top scorers first by number of correct questions, then by
	 * amount of time taken. */
	private void sortTopScorers(){
		
		//Sort top scorers
		Collections.sort(topScorers, new Comparator<TopScorer>(){
			
			@Override
			public int compare(TopScorer topScorer1, TopScorer topScorer2){
				
				//sort first by number of correct questions
				int diffNumCorrectQuestions = topScorer2.getNumCorrectQuestions() - topScorer1.getNumCorrectQuestions();
				if (diffNumCorrectQuestions != 0) {
					return diffNumCorrectQuestions;
				}else{
					
					//sort next by time taken
					double diffTimeTaken = topScorer1.getTimeTaken() - topScorer2.getTimeTaken();
					if (diffTimeTaken < 0) return -1;
					else if (diffTimeTaken > 0) return 1;
					else return 0;
				}
			}
		});
	}
	
}
