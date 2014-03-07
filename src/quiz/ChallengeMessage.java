package quiz;

public class ChallengeMessage extends Message {
	
	private String quizName;
	private String message;
	private double bestScore;
	private DAL dal;
	
	public ChallengeMessage(String fromUser, String toUser, Quiz quiz, DAL dal) {
		super(fromUser, toUser, Message.CHALLENGE_MESSAGE, dal);
		this.dal = dal;
		this.quizName = quiz.getQuizName();
		this.bestScore = challengingUserBestScore(fromUser);
		message = fromUser + " is challenging you to take the " + quiz.getQuizName() + " quiz!";
		dal.addMessageForUser(fromUser, toUser, Message.CHALLENGE_MESSAGE, message, quizName, bestScore);
	}
	
	//Used for testing
	public ChallengeMessage(String fromUserString, String toUserString, Quiz quiz, DAL dal, double bestScore) {
		super(fromUserString, toUserString, Message.CHALLENGE_MESSAGE, dal);
		this.quizName = quiz.getQuizName();
		this.bestScore = bestScore;
		message = fromUserString + " is challenging you to take the " + quiz.getQuizName() + " quiz!";
		dal.addMessageForUser(fromUserString, toUserString, Message.CHALLENGE_MESSAGE, message, quizName, bestScore);
	}
	
	//Used for building
	public ChallengeMessage(String fromUserString, String toUserString, String quizName, DAL dal, double bestScore) {
		super(toUserString, toUserString, Message.CHALLENGE_MESSAGE,dal);
		this.quizName = quizName;
		this.bestScore = bestScore;
		message = fromUserString + " is challenging you to take the " + quizName + " quiz!";
	}
	
	public String getQuizName() {
		return quizName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public double challengingUserBestScore(String userName) {
		//Alternative looping through user's histories
		int bestScore = 0;
		for (HistoryObject hist : dal.getHistoryListForUser(userName)) {
			if (hist.getQuizName().equals(getQuizName())) {
				int score = hist.getNumQuestionsCorrect();
				if (score > bestScore)
					bestScore = score;
			}
		}
		return bestScore;
	}

}
