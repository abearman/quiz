package quiz;
import java.util.*;

public class Quiz {
	
	//instance variables
	
	//stay the same across sessions, stored in database
	private String quizName;
	private String descriptionOfQuiz;
	private boolean isRandom;
	private boolean isMultiplePage;
	private boolean isImmediateCorrection;
	private boolean canBeTakenInPracticeMode;
	private ArrayList<Question> questions;
	
	//different across sessions
	private double lengthOfCompletion;
	private double usersScore;
	
	//updated across different sessions, stored in database
	private ArrayList<TopScorer> topScorers;
	
	//constructor initializes arraylists
	public Quiz(){
		questions = new ArrayList<Question>();
		topScorers = new ArrayList<TopScorer>();
	}
	
	/* Getters */
	
	public String getQuizName(){
		return quizName;
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
	
	public double getLengthOfCompletion(){
		return lengthOfCompletion;
	}
	
	public double getUsersScore(){
		return usersScore;
	}
	
	public ArrayList<TopScorer> getTopScorers(){
		return topScorers;
	}
	
	/* Setters */
	
	public void setQuizName(String quizName){
		this.quizName = quizName;
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
	
	public void setLengthOfCompletion(double lengthOfCompletion){
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
	}
	
	public void removeTopScorer(TopScorer topScorer){
		if (topScorers.contains(topScorer)){
			topScorers.remove(topScorer);
		}
	}
	
}
