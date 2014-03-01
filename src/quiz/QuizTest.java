package quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
import org.junit.*;


public class QuizTest {

	private Quiz thisQuiz;
	private TopScorer topScorer1, topScorer2, topScorer3, topScorer4;
	private DAL dal;

	
	@Before
	public void setUp() throws Exception {

		dal = new DAL();
		thisQuiz = new Quiz(dal);

		topScorer4 = new TopScorer("user4", 10, 0.7, dal);
		topScorer3 = new TopScorer("user3", 10, 0.5, dal);
		topScorer2 = new TopScorer("user2", 11, 0.6, dal);
		topScorer1 = new TopScorer("user1", 19, 7.8, dal);

	}

	@Test
	public void testInsertQuiz() {
		//HistoryObject ho = new HistoryObject("testUser",thisQuiz ,dal);
		Quiz testQuiz = new Quiz(dal, "TestQuizName","testQuizDescription",true,
				true,true,true,"Pavitra",new Date(),3);
		HistoryObject ho2 = new HistoryObject("testUser",testQuiz ,dal);
	}
	
	
	//order should be 1, 2, 3, 4
	@Test
	public void testSortOrder() {
		thisQuiz.addTopScorer(topScorer1);
		thisQuiz.addTopScorer(topScorer3);
		thisQuiz.addTopScorer(topScorer2);
		thisQuiz.addTopScorer(topScorer4);

		for (int i = 0; i < thisQuiz.getTopScorers().size(); i++){
			System.out.println("topScorer is " + thisQuiz.getTopScorers().get(i).getLoginName());
		}
	}
	
	
	@Test 
	//Test database queries
	public void testQueries() {
		String loginName = "amy";
		String quizName = "quiz";
		boolean isAdministrator = false;
		String passwordHash = "password1234";
		String achievementsString = "000000";
		int numQuestionsCorrect = 5;
		long timeElapsed = 5000;
		String dateString = "10:34:00";
		java.sql.Date sqlDate = null;
		String user1 = "jacob";
		String user2 = "alanna";
		String fromUser = "catherine";
		String toUser = "biola";
		String type = Message.CHALLENGE_MESSAGE;
		String message = "I challenge you!";
		double bestScore = 10;
		TopScorer topScorer = new TopScorer(loginName, numQuestionsCorrect, timeElapsed, null);
		String descriptionOfQuiz = "This is the best quiz ever";
		boolean isRandom = true;
		boolean isMultiplePage = false;
		boolean isImmediateCorrection = true;
		boolean canBeTakenInPracticeMode = false;
		String creatorName = "sharon";
		int numTimesTaken = 10;
		System.out.println("SELECT * FROM users WHERE loginName = \"" + loginName + "\";");
		System.out.println("SELECT * FROM histories WHERE loginName = \"" + loginName + "\";");
		System.out.println("SELECT * FROM friends WHERE user1 = \"" + loginName + "\";");
		System.out.println("SELECT * FROM histories;");
		System.out.println("SELECT * FROM topscorers WHERE quizName = \"" + quizName + "\";");
		System.out.println("INSERT INTO users VALUES(\"" + loginName + "\", " + isAdministrator + ", \"" + passwordHash + "\", \"" + achievementsString + "\");");
		System.out.println("DELETE FROM users WHERE loginName = \"" + loginName + "\"");
		System.out.println("UPDATE users SET achievements = \"" + achievementsString + "\" WHERE loginName = \"" + loginName + "\";");
		System.out.println("UPDATE users SET isAdministrator = " + isAdministrator + " WHERE loginName = \"" + loginName + "\";");
		System.out.println("INSERT INTO histories VALUES(\"" + loginName + "\",\"" + quizName + "\"," + numQuestionsCorrect + "," + timeElapsed + ",\"" + dateString + "\"," + sqlDate + ");");
		System.out.println("INSERT INTO friends VALUES(\"" + user1 + "\",\"" + user2 + "\") , " + "(\"" + user2 + "\",\"" + user1 + "\");");
		System.out.println("INSERT INTO users values(\"" + fromUser + "\", \"" + toUser + "\", \"" + type + "\", \"" + message + "\", \"" + quizName + "\", " + bestScore + ");");
		System.out.println("INSERT INTO topscorers VALUES(\""+quizName+"\",\""+topScorer.getLoginName()+"\","+ topScorer.getNumCorrectQuestions()+","+topScorer.getTimeTaken()+");");
		System.out.println("DELETE FROM topscorers WHERE quizName = \"" + quizName + "\" AND loginName = \"" + loginName + "\";");
		System.out.println("INSERT INTO quizzes VALUES(\""+quizName+"\",\""+descriptionOfQuiz+"\","+ isRandom+","+isMultiplePage+","+isImmediateCorrection+","+canBeTakenInPracticeMode+",\"" + creatorName + "\"," + sqlDate + "," + numTimesTaken + ");");
		System.out.println("DELETE FROM quizzes WHERE quizName = \"" + quizName + "\"");
	}
	
	
	@Test
	public void testFriendsTable() throws SQLException {
		DAL dal = new DAL();
		Statement stmt = dal.getStatement();
		String toUser = "Bruno";
		for (int i = 1; i <= 10; i++) {
			String fromUser = "user" + i;
			FriendRequestMessage request = new FriendRequestMessage(fromUser, toUser, dal);
			request.acceptRequest(true);
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM friends WHERE user1 = \"" + toUser + "\";");
			rs.last();
			assertEquals(i, rs.getRow());
			
			rs = stmt.executeQuery("SELECT * FROM friends WHERE user1 = \"" + fromUser + "\";");
			rs.last();
			assertEquals(1, rs.getRow());
		}
	}
	
	
	@Test
	public void testHistoriesTable() throws SQLException {
		DAL dal = new DAL();
		String loginName = "Bruno";
		Statement stmt = dal.getStatement();
		for (int i = 1; i <= 10; i++) {
			Quiz quiz = new Quiz(dal);
			quiz.setLengthOfCompletion(i);
			quiz.setNumQuestionsCorrect(i);
			quiz.setQuizName("quiz" + i);
			new HistoryObject(loginName, quiz, dal);
		}
		for (int i = 1; i <=10; i++) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM histories WHERE loginName = \"" + loginName + "\";");
			rs.next();
			assertEquals(i, rs.getRow());
			assertEquals("quiz" + i, rs.getString(2));
		}
	}
	
}

