package quiz;

import java.util.ArrayList;

public class ChallengeMessage extends Message {
	
	private String quizName;
<<<<<<< HEAD
=======
	//private String quizLink;
>>>>>>> d67c79c14ae3122e22a584f94b080ed60130c91b
	private String message;
	private double bestScore;
	
	public ChallengeMessage(User fromUser, User toUser, Quiz quiz, DBConnection con) {
		super(fromUser.getLoginName(), toUser.getLoginName(), con);
		this.quizName = quiz.getQuizName();
<<<<<<< HEAD
=======
		//this.quizLink = quiz.getQuizLink();
>>>>>>> d67c79c14ae3122e22a584f94b080ed60130c91b
		this.bestScore = challengingUserBestScore(fromUser);
		message = fromUser + "is challenging you to take the " + quiz.getQuizName() + "quiz!";
	}
	
	public String getQuizName() {
		return quizName;
	}
	
<<<<<<< HEAD
=======
	/*
	public String getQuizLink() {
		return quizLink;
	}*/
	
>>>>>>> d67c79c14ae3122e22a584f94b080ed60130c91b
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
