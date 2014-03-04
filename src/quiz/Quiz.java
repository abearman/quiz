package quiz;
import java.util.*;

public class Quiz {

	public static final int TOPSCORER_MAX = 5;
	public static final int RECENT_TEST_TAKER_MAX = 5;
	public static final int TOTAL_STATISTICS = 3;

	public static final int AVERAGE_SCORE = 0;
	public static final int MEDIAN_SCORE = 1;
	public static final int AVERAGE_TIME = 2;
	
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
	private int numQuestionsCorrect;
	private ArrayList<String> usersAnswers;
	private int currentQuestionNumIndex = 0;

	//updated across different sessions, stored in database
	private ArrayList<TopScorer> topScorers;
	private int numTimesTaken;

	//connect to database
	private DAL dal;
	
	public int getNextQuestionNum() {
		currentQuestionNumIndex++;
		return currentQuestionNumIndex;
	}
	
	public int getCurrentQuestionNum() {
		return currentQuestionNumIndex;
	}


	public void initializeUsersAnswers() {
		usersAnswers = new ArrayList<String>();
	}
	
	public void addAnswers(String answer) {
		usersAnswers.add(answer);
	}
	
	public ArrayList<String> getAnswers(){
		return usersAnswers;
	}
	
	private void initializeArrayLists() {
		questions = new ArrayList<Question>();
		topScorers = new ArrayList<TopScorer>();
		allHistories = initializeAllHistories();

		topScorersPastDay = new ArrayList<TopScorer>();
		recentQuizTakers = new ArrayList<String>();
	}

	private ArrayList<HistoryObject> initializeAllHistories() { 
		return dal.getAllHistoryLists(quizName);
	}

	//reads database to find this quiz and populates instance variables
	private void readDatabase(String givenQuizName){
		this.quizName = dal.getNameOfQuiz(givenQuizName);
		this.descriptionOfQuiz = dal.getDescriptionOfQuiz(givenQuizName);
		this.isRandom = dal.getIsRandomOfQuiz(givenQuizName);
		this.isMultiplePage = dal.getIsMultiplePageOfQuiz(givenQuizName);
		this.isImmediateCorrection = dal.getIsImmediateCorrectionOfQuiz(givenQuizName);
		this.canBeTakenInPracticeMode = dal.getCanBeTakenInPracticeModeOfQuiz(givenQuizName);
		this.creatorName = dal.getCreatorName(givenQuizName);
		this.creationDate = dal.getCreationDate(givenQuizName);
		this.numTimesTaken = dal.getNumTimesTaken(givenQuizName);
	}
	
	//retrieves the questions associated with the quiz from the database
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

	//constructor for creating a quiz, adds quiz to database
	public Quiz(DAL dal, String quizName, String descriptionOfQuiz,
			boolean isRandom, boolean isMultiplePage,
			boolean isImmediateCorrection, boolean canBeTakenInPracticeMode,
			String creatorName, java.util.Date dateCreated, int numTimesTaken) {
		this.dal = dal;
		this.quizName = quizName;
		this.descriptionOfQuiz = descriptionOfQuiz;
		this.isRandom = isRandom;
		this.isMultiplePage = isMultiplePage;
		this.isImmediateCorrection = isImmediateCorrection;
		this.canBeTakenInPracticeMode = canBeTakenInPracticeMode;
		this.creatorName = creatorName;
		this.creationDate = dateCreated;
		this.numTimesTaken = numTimesTaken;
		initializeArrayLists();
		dal.insertQuiz(this);
	}

	//constructor for taking a quiz, handles querying of database
	public Quiz(DAL dal, String givenQuizName) {
		this.dal = dal;
		initializeArrayLists();
		readDatabase(givenQuizName);
		getQuestionsFromDB(givenQuizName);
	}

	//simple constructor only for unit testing
	//TODO take this out eventually
	public Quiz(DAL dal){
		this.dal = dal;
		initializeArrayLists();
	}

	public double[] getStatisticsSummary() {
		double[] statistics = new double[TOTAL_STATISTICS];
		double averageScore = 0;
		double medianScore = 0;
		double averageTime = 0;
		ArrayList<Double> scores = new ArrayList<Double>();
		for (HistoryObject hist : allHistories) {
			averageScore += hist.getNumQuestionsCorrect();
			averageTime += hist.getElapsedTime();
			scores.add(new Double(hist.getNumQuestionsCorrect()));
		}
		medianScore = getMedian(scores);
		averageScore /= allHistories.size();
		averageTime /= allHistories.size();

		statistics[AVERAGE_SCORE] = averageScore;
		statistics[MEDIAN_SCORE] = medianScore;
		statistics[AVERAGE_TIME] = averageTime;
		return statistics;
	}

	private double getMedian(ArrayList<Double> scores) {
		Collections.sort(scores);
		
		if (scores.size() == 0) return 0;

		if (scores.size() % 2 == 1) {
			return scores.get((scores.size()+1)/2 - 1);
		} else {
			double lower = scores.get(scores.size()/2 - 1 );
			double upper = scores.get(scores.size()/2);

			return (lower + upper) / 2.0;
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

	public int getNumQuestionsCorrect(){
		return numQuestionsCorrect;
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
		sortTopScorers(topScorers); //Sort the array
		return topScorers;
	}


	public ArrayList<HistoryObject> getAllHistories() {
		return allHistories;
	}

	public void setLengthOfCompletion(long lengthOfCompletion) { //TODO: Update database?
		this.lengthOfCompletion = lengthOfCompletion;
	}

	//TODO Update the database?
	public void setNumQuestionsCorrect(int numQuestionsCorrect){
		this.numQuestionsCorrect = numQuestionsCorrect;
	}

	public void addQuestion(Question question){
		questions.add(question);
		dal.addQuestion(quizName, question);
	}

	public void removeQuestion(Question question) { 
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
		sortTopScorers(topScorers);

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
	private void sortTopScorers(ArrayList<TopScorer> topScorerList){

		//Sort top scorers
		Collections.sort(topScorerList, new Comparator<TopScorer>(){

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

		for (int i = 0; i < allHistories.size(); i++){

			HistoryObject history = allHistories.get(i);

			Date currentDate = new Date();
			Calendar previousDayCal = Calendar.getInstance();
			previousDayCal.setTime(currentDate);
			
			//keep track of current day, month, and year
			int currentYear = previousDayCal.get(Calendar.YEAR);
			int currentMonth = previousDayCal.get(Calendar.MONTH);
			int currentDay = previousDayCal.get(Calendar.DAY_OF_MONTH);
			
			//figure out what the previous day is
			previousDayCal.add(Calendar.DAY_OF_MONTH, -1);

			//get the history object's day
			Date historyDate = history.getDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(historyDate);

			int historyYear = cal.get(Calendar.YEAR);
			int historyMonth = cal.get(Calendar.MONTH);
			int historyDay = cal.get(Calendar.DAY_OF_MONTH);

			int yesterdayYear = previousDayCal.get(Calendar.YEAR);
			int yesterdayMonth = previousDayCal.get(Calendar.MONTH);
			int yesterdayDay = previousDayCal.get(Calendar.DAY_OF_MONTH);

			//found a history in the past day
			if ((historyYear == currentYear && historyMonth == currentMonth && historyDay == currentDay) || 
					(historyYear == yesterdayYear && historyMonth == yesterdayMonth && historyDay == yesterdayDay)){
				topScorersPastDay.add(new TopScorer(history.getUserName(), history.getNumQuestionsCorrect(), history.getElapsedTime(), dal));
			}

		}

		//sort top scorers in the past day and cap at 5
		sortTopScorers(topScorersPastDay);

		return topScorersPastDay;
	}
	
	//for testing
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}



}
