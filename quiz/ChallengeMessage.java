package quiz;

public class ChallengeMessage extends Message {
	
	private Quiz quiz;
	private String message;
	
	public ChallengeMessage(String fromUser, String toUser, Quiz quiz) {
		super(fromUser, toUser);
		this.quiz = quiz;
		message = fromUser + "is challenging you to take the " + quiz.getName() + "quiz!";
	}
	
	public String getQuizName() {
		return quiz.getName();
	}
	
	public String getQuizLink() {
		return quiz.getLink();
	}
	
	public String getMessage() {
		return message;
	}
	
	public double challengingUserBestScore() {
		//is this worth it? Seems too costly.
		double bestScore = 0;
		for (HistoryObject hist : quiz.getAllHistories()) {
			if (hist.getUserName().equals(getFromUser())) {
				double score = hist.getScore();
				if (score > bestScore)
					bestScore = score;
			}
		}
		return bestScore;
	}
	
	public double challengingUserBestScore2() {
		//Alternative looping through user's histories
		double bestScore = 0;
//		for (HistoryObject hist : user.getAllHistories()) {
//			if (user.getQuizName().equals(getQuizName())) {
//				double score = hist.getScore();
//				if (score > bestScore)
//					bestScore = score;
//			}
//		}
		return bestScore;
	}

}
