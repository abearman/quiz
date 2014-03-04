package quiz;

public class ChallengeMessage extends Message {
	
	private String quizName;
	private String message;
	private double bestScore;
	
	public ChallengeMessage(User fromUser, User toUser, Quiz quiz, DAL dal) {
		super(fromUser.getLoginName(), toUser.getLoginName(), dal);
		this.quizName = quiz.getQuizName();
		this.bestScore = challengingUserBestScore(fromUser);
		message = fromUser.getLoginName() + "is challenging you to take the " + quiz.getQuizName() + "quiz!";
		dal.addMessageForUser(fromUser.getLoginName(), toUser.getLoginName(), Message.CHALLENGE_MESSAGE, message, quizName, bestScore);
	}
	
	//Used for testing
	//TODO delete this method
	public ChallengeMessage(User fromUser, User toUser, Quiz quiz, DAL dal, double bestScore) {
		super(fromUser.getLoginName(), toUser.getLoginName(), dal);
		this.quizName = quiz.getQuizName();
		this.bestScore = bestScore;
		message = fromUser.getLoginName() + "is challenging you to take the " + quiz.getQuizName() + "quiz!";
		dal.addMessageForUser(fromUser.getLoginName(), toUser.getLoginName(), Message.CHALLENGE_MESSAGE, message, quizName, this.bestScore);
	}
	
	public String getQuizName() {
		return quizName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public double challengingUserBestScore(User user) {
		//Alternative looping through user's histories
		int bestScore = 0;
		for (HistoryObject hist : user.getHistory()) {
			if (hist.getQuizName().equals(getQuizName())) {
				int score = hist.getNumQuestionsCorrect();
				if (score > bestScore)
					bestScore = score;
			}
		}
		return bestScore;
	}

}
