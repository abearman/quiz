package quiz;
import java.util.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Quiz {

	public static final int TOPSCORER_MAX = 5;

	//instance variables

	//stay the same across sessions, stored in database
	private String quizName;
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
	private DAL dal;

	private void initializeArrayLists() {
		questions = new ArrayList<Question>();
		topScorers = new ArrayList<TopScorer>();
		allHistories = initializeAllHistories();
	}

	private ArrayList<HistoryObject> initializeAllHistories() { 
		return dal.getAllHistoryLists();
	}

	//constructor for creating a quiz, adds quiz to database
	
	//reads database to find this quiz and populates instance variables
	private void readDatabase(String givenQuizName) { //TODO: Add to DAL
		//dal.populateQuiz(this, givenQuizName);
		
		try {
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet != null){
				quizResultSet.first();
				this.quizName = (String)quizResultSet.getObject(1);
				this.descriptionOfQuiz = (String)quizResultSet.getObject(2);
				this.isRandom = (Boolean) quizResultSet.getObject(3);
				this.isMultiplePage = (Boolean) quizResultSet.getObject(4);
				this.isImmediateCorrection = (Boolean) quizResultSet.getObject(5);
				this.canBeTakenInPracticeMode = (Boolean) quizResultSet.getObject(6);
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
	}

	//TODO: Delete this constructor?
	public Quiz(DAL dal) {
		
	}
	
	//constructor for creating a quiz, adds quiz to database
	public Quiz(DAL dal, String quizName, String descriptionOfQuiz,
			boolean isRandom, boolean isMultiplePage,
			boolean isImmediateCorrection, boolean canBeTakenInPracticeMode) {
		
		initializeArrayLists();
		this.dal = new DAL();
		dal.insertQuiz(quizName, descriptionOfQuiz, isRandom, isMultiplePage, isImmediateCorrection, canBeTakenInPracticeMode);
	}

	//constructor for taking a quiz, handles querying of database
	public Quiz(DBConnection con, String givenQuizName) {
		initializeArrayLists();
		this.dal = new DAL();
		readDatabase(givenQuizName);
		getQuestionsFromDB(givenQuizName);
	}
	
	private void getQuestionsFromDB(String quizName) { //TODO: Add to DAL
		try{
			//for question response
			ResultSet qrs = stmt.executeQuery("SELECT * FROM questionResponse WHERE quizName = \"" 
				+ quizName + "\";");
			if (qrs!=null){
				qrs.beforeFirst();
				while(qrs.next())
				{
					questions.add(new QuestionResponse(qrs.getString(2),Question.createArray(qrs.getString(3)),
							qrs.getInt(5)));
				}
			}
			
			//for fill-in-the-blank
			qrs = stmt.executeQuery("SELECT * FROM fillInTheBlank WHERE quizName = \"" 
				+ quizName + "\";");
			if (qrs!=null){
				qrs.beforeFirst();
				while(qrs.next())
				{
					questions.add(new FillInTheBlank(qrs.getString(2),Question.createArray(qrs.getString(3)),
							qrs.getInt(5)));
				}
			}
			
			//for multiple choice
			qrs = stmt.executeQuery("SELECT * FROM multipleChoice WHERE quizName = \"" 
				+ quizName + "\";");
			if (qrs!=null){
				qrs.beforeFirst();
				while(qrs.next())
				{
					questions.add(new MultipleChoice(qrs.getString(2),Question.createArray(qrs.getString(3)),
							qrs.getInt(5), Question.createArray(qrs.getString(6))));
				}
			}
			
			//for picture response
			qrs = stmt.executeQuery("SELECT * FROM pictureResponse WHERE quizName = \"" 
				+ quizName + "\";");
			if (qrs!=null){
				qrs.beforeFirst();
				while(qrs.next())
				{
					questions.add(new PictureResponse(qrs.getString(2),Question.createArray(qrs.getString(3)),
							qrs.getInt(5), qrs.getString(6)));
				}
			}
		} catch (SQLException e){
			e.printStackTrace(); //TODO How do we want to handle this?
		}
		if (isRandom) {
			Collections.shuffle(questions);
		}
		else 
		{
			//sorts according to question number
			Collections.sort(questions, new Comparator<Question>(){
				public int compare(Question q1, Question q2){
					return q1.getQuestionNumber()-q2.getQuestionNumber();
				}
			});
		}
	}

	/* Getters */

	public String getQuizName() {
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

	public long getLengthOfCompletion(){
		return lengthOfCompletion;
	}

	public double getUsersScore(){
		return usersScore;
	}

	//Returns sorted array of TopScorer's by reading from database
	public ArrayList<TopScorer> getTopScorers() { 
		topScorers = dal.getAllTopScorersForQuiz(quizName);
		sortTopScorers(); //Sort the array
		return topScorers;		
	}

	public ArrayList<HistoryObject> getAllHistories() {
		return allHistories;
	}

	/* Setters */

	/*
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

	}*/
	
	public void setLengthOfCompletion(long lengthOfCompletion) { //TODO: Update database?
		this.lengthOfCompletion = lengthOfCompletion;
	}

	public void setUsersScore(double usersScore) { //TODO: Update database?
		this.usersScore = usersScore;
	}

	public void addQuestion(Question question) { //TODO: Add to DAL 
		questions.add(question);
		//adds the question to the corresponding database table
		try {
			String update = "";
			if(question.getQuestionType() == Question.QUESTION_RESPONSE)
			{
				update = "INSERT INTO questionResponse VALUES(\""+quizName+"\",\""+question.getQuestion()
				+"\",\""+question.createAnswerString()+"\","+question.getQuestionType()+","
				+question.getQuestionNumber()+");";
			}
			if(question.getQuestionType() == Question.FILL_IN_THE_BLANK)
			{
				update = "INSERT INTO fillInTheBlank VALUES(\""+quizName+"\",\""+question.getQuestion()
				+"\",\""+question.createAnswerString()+"\","+question.getQuestionType()+","
				+question.getQuestionNumber()+");";
			}
			if(question.getQuestionType() == Question.MULTIPLE_CHOICE)
			{
				update = "INSERT INTO multipleChoice VALUES(\""+quizName+"\",\""+question.getQuestion()
				+"\",\""+question.createAnswerString()+"\","+question.getQuestionType()+","
				+question.getQuestionNumber()+",\""+((MultipleChoice)question).createChoicesString()+"\");";
			}
			if(question.getQuestionType() == Question.PICTURE_RESPONSE)
			{
				update = "INSERT INTO pictureResponse VALUES(\""+quizName+"\",\""+question.getQuestion()
				+"\",\""+question.createAnswerString()+"\","+question.getQuestionType()+","
				+question.getQuestionNumber()+",\""+((PictureResponse)question).getImageURL()+"\");";
			}
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
	}

	public void removeQuestion(Question question) { //TODO: Update the database?
		if (questions.contains(question)){
			questions.remove(question);
		}
	}

	public void addTopScorer(TopScorer topScorer) {
		
		//add this top scorer and update database
		topScorers.add(topScorer);
		dal.addTopScorer(topScorer, quizName);
		
		//sort top scorers
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
			
			//remove this top scorer
			//update database to delete entry with this quiz and this user
			topScorers.remove(topScorer);
			dal.removeTopScorer(topScorer.getLoginName(), quizName);
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
