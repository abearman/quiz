package quiz;
import java.util.*;

public class Quiz {

	public static final int TOPSCORER_MAX = 5;
	public static final int RECENT_TEST_TAKER_MAX = 5;

	//instance variables

	//stay the same across sessions, stored in database
	private String quizName;
	private String descriptionOfQuiz;
	private boolean isRandom;
	private boolean isMultiplePage;
	private boolean isImmediateCorrection;
	private boolean canBeTakenInPracticeMode;
	private String creatorName;
	private java.util.Date creationDate;
	private ArrayList<Question> questions;
	private ArrayList<HistoryObject> allHistories;
	
	//miscellaneous
	private ArrayList<TopScorer> topScorersPastDay;
	private ArrayList<String> recentQuizTakers;

	//different across sessions
	private long lengthOfCompletion;
	private double usersScore;

	//updated across different sessions, stored in database
	private ArrayList<TopScorer> topScorers;
	private int numTimesTaken;
	
	//connect to database
	private DAL dal;

	private void initializeArrayLists() {
		questions = new ArrayList<Question>();
		topScorers = new ArrayList<TopScorer>();
		allHistories = initializeAllHistories();
		
		topScorersPastDay = new ArrayList<TopScorer>();
		recentQuizTakers = new ArrayList<String>();
	}

	private ArrayList<HistoryObject> initializeAllHistories() { 
		return dal.getAllHistoryLists();
	}

	//constructor for creating a quiz, adds quiz to database
	
	//reads database to find this quiz and populates instance variables
	private void readDatabase(String givenQuizName){
		this.quizName = dal.getNameOfQuiz(givenQuizName);
		this.descriptionOfQuiz = dal.getDescriptionOfQuiz(givenQuizName);
		this.isRandom = dal.getIsRandomOfQuiz(givenQuizName);
		this.isMultiplePage = dal.getIsRandomOfQuiz(givenQuizName);
		this.isImmediateCorrection = dal.getIsImmediateCorrectionOfQuiz(givenQuizName);
		this.canBeTakenInPracticeMode = dal.getCanBeTakenInPracticeModeOfQuiz(givenQuizName);
		this.creatorName = dal.getCreatorName(givenQuizName);
		this.creationDate = dal.getCreationDate(givenQuizName);
		this.numTimesTaken = dal.getNumTimesTaken(givenQuizName);
		
	}
	
	//constructor for creating a quiz, adds quiz to database
	public Quiz(DAL dal, String quizName, String descriptionOfQuiz,
			boolean isRandom, boolean isMultiplePage,
			boolean isImmediateCorrection, boolean canBeTakenInPracticeMode,
			String creatorName, java.util.Date dateCreated, int numTimesTaken) {
		
		initializeArrayLists();
		this.dal = new DAL();
		dal.insertQuiz(quizName, descriptionOfQuiz, isRandom, isMultiplePage, isImmediateCorrection, canBeTakenInPracticeMode, creatorName, dateCreated, 0);
	}

	//constructor for taking a quiz, handles querying of database
	public Quiz(DAL dal, String givenQuizName) {
		initializeArrayLists();
		this.dal = dal;
		readDatabase(givenQuizName);
		getQuestionsFromDB(givenQuizName);
	}
	
	//simple constructor only for unit testing
	//TODO take this out eventually
	public Quiz(DAL dal){
		initializeArrayLists();
		this.dal = dal;
	}
	
	
	private void getQuestionsFromDB(String quizName)
	{
		questions = dal.getQuestionsFromDB(quizName);
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
	
	public String getCreatorName(){
		return creatorName;
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
	
	public int getNumTimesTaken(){
		return numTimesTaken;
	}
	
	public java.util.Date getCreationDate(){
		return creationDate;
	}
	
	public void incrementNumTimesTaken(){
		this.numTimesTaken++;
		dal.incrementNumTimesTaken(quizName);
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
	
	public void setLengthOfCompletion(long lengthOfCompletion) { //TODO: Update database?
		this.lengthOfCompletion = lengthOfCompletion;
	}

	public void setUsersScore(double usersScore) { //TODO: Update database?
		this.usersScore = usersScore;
	}


	public void addQuestion(Question question){
		questions.add(question);
		dal.addQuestion(quizName, question);
	}

	public void removeQuestion(Question question) { //TODO: Update the database?
		if (questions.contains(question)){
			questions.remove(question);
			dal.removeQuestion(quizName, question);
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
	
	public ArrayList<String> getRecentQuizTakers(){
		sortHistories();
		for (int i = 0; i < RECENT_TEST_TAKER_MAX; i++){
			recentQuizTakers.add(allHistories.get(i).getUserName());
		}
		return recentQuizTakers;
	}
	
	//Sort all histories in order of most recent date
	//history 1 is "less than" history 2 if history 1's date is before history 2's
	private void sortHistories(){
		
		Collections.sort(allHistories, new Comparator<HistoryObject>(){

			@Override
			public int compare(HistoryObject history1, HistoryObject history2){
				if (history1.getDate().before(history2.getDate())){
					return -1;
				}else if (history2.getDate().before(history1.getDate())){
					return 1;
				}
				return 0;
			}
		});
	}
	
	public ArrayList<TopScorer> getTopScorersPastDay(){
		//TODO populate top scorers in the past day
		return topScorersPastDay;
	}

}
