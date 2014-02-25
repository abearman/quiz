package quiz;

import java.util.ArrayList;

public class ChallengeMessage extends Message {
	
	private String quizName;
	private String quizLink;
	private String message;
	private double bestScore;
	
	public ChallengeMessage(User fromUser, User toUser, Quiz quiz) {
		super(fromUser.getLoginName(), toUser.getLoginName());
		this.quizName = quiz.getQuizName();
		this.quizLink = quiz.getQuizLink();
		this.bestScore = challengingUserBestScore(fromUser);
		message = fromUser + "is challenging you to take the " + quiz.getQuizName() + "quiz!";
	}
	
	public String getQuizName() {
		return quizName;
	}
	
	public String getQuizLink() {
		return quizLink;
	}
	
	public String getMessage() {
		return message;
	}
	
//	public double challengingUserBestScore(ArrayList<HistoryObject> allHistories) {
//		//is this worth it? Seems too costly.
//		double bestScore = 0;
//		for (HistoryObject hist : allHistories) {
//			if (hist.getUserName().equals(getFromUser())) {
//				double score = hist.getScore();
//				if (score > bestScore)
//					bestScore = score;
//			}
//		}
//		return bestScore;
//	}
	
	public double challengingUserBestScore(User user) {
		//Alternative looping through user's histories
		double bestScore = 0;
		for (HistoryObject hist : user.getHistory()) {
			if (hist.getQuizName().equals(getQuizName())) {
				double score = hist.getScore();
				if (score > bestScore)
					bestScore = score;
			}
		}
		return bestScore;
	}

}
