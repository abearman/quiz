package quiz;

public class ChallengeMessage extends Message {
	
	private String quizName;
	private String message;
	private double bestScore;
	
	public ChallengeMessage(User fromUser, User toUser, Quiz quiz, DAL dal) {
		super(fromUser.getLoginName(), toUser.getLoginName(), Message.CHALLENGE_MESSAGE, dal);
		this.quizName = quiz.getQuizName();
		this.bestScore = challengingUserBestScore(fromUser);
		message = fromUser.getLoginName() + "is challenging you to take the " + quiz.getQuizName() + "quiz!";
		dal.addMessageForUser(fromUser.getLoginName(), toUser.getLoginName(), Message.CHALLENGE_MESSAGE, message, quizName, bestScore);
	}
	
	//Used for building
	public ChallengeMessage(String fromUserString, String toUserString, String quizName, DAL dal, double bestScore) {
		super(toUserString, toUserString, Message.CHALLENGE_MESSAGE,dal);
		this.quizName = quizName;
		this.bestScore = bestScore;
		message = fromUserString + "is challenging you to take the " + toUserString + "quiz!";
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
