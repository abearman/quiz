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

	private Quiz testQuiz10, testQuiz11;
	private TopScorer topScorer1, topScorer2, topScorer3, topScorer4;
	private DAL dal;


	@Before
	public void setUp() throws Exception {

		dal = new DAL();
		//testQuiz1 = new Quiz(dal, "TestQuizName","testQuizDescription",true,true,true,true,"Pavitra",new Date(),3);

		topScorer4 = new TopScorer("user4", 10, 0.7, dal);
		topScorer3 = new TopScorer("user3", 10, 0.5, dal);
		topScorer2 = new TopScorer("user2", 11, 0.6, dal);
		topScorer1 = new TopScorer("user1", 19, 7.8, dal);

	}

	@Test
	public void testInsertQuiz() {
		testQuiz10 = new Quiz(dal, "quiz10","another quiz",true,true,true,true,"Pavitra",new Date(),5);
		testQuiz11 = new Quiz(dal, "quiz11","another quiz",true,true,true,true,"Pavitra",new Date(),5);
	}

	@Test
	public void testRemoveQuiz() {
		dal.removeQuiz("quiz10");
	}

	@Test
	public void testGettingQuiz() {
		Quiz getTestQuiz11 = new Quiz(dal, "quiz11");
		assertEquals(getTestQuiz11.getCreatorName(),"Pavitra");
		assertEquals(getTestQuiz11.canBeTakenInPracticeMode(),true);
		assertEquals(getTestQuiz11.isRandom(),true);
		assertEquals(getTestQuiz11.isImmediateCorrection(),true);
		assertEquals(getTestQuiz11.isMultiplePage(),true);
		assertEquals(getTestQuiz11.getNumTimesTaken(),5);
		getTestQuiz11.incrementNumTimesTaken();
		assertEquals(getTestQuiz11.getNumTimesTaken(),6);
	}

	@Test
	public void testAddTopScorer(){
		Quiz testQuiz12 = new Quiz(dal, "quiz12","another quiz",true,true,true,true,"Pavitra",new Date(),5);
		testQuiz12.addTopScorer(topScorer1);
	}


	//order should be 1, 2, 3, 4
	/*
	@Test
	public void testSortOrder() {
		testQuiz1.addTopScorer(topScorer1);
		testQuiz1.addTopScorer(topScorer3);
		testQuiz1.addTopScorer(topScorer2);
		testQuiz1.addTopScorer(topScorer4);

		for (int i = 0; i < testQuiz1.getTopScorers().size(); i++){
			System.out.println("topScorer is " + testQuiz1.getTopScorers().get(i).getLoginName());
		}
	}*/


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
		String toUserName = "Bruno";
		for (int i = 1; i <= 10; i++) {
			String fromUserName = "user" + i;

			FriendRequestMessage request = new FriendRequestMessage(fromUserName, toUserName, dal);
			request.acceptRequest(true);

			// check if toUser has i friends so far
//			ResultSet rs = stmt.executeQuery("SELECT * FROM friends WHERE user1 = \"" + toUserName + "\";");
//			while (rs.next()) {
//				assertEquals(fromUserName, rs.getString(2));
//			}
//
//			//check if fromUser only has one friend toUser
//			rs = stmt.executeQuery("SELECT * FROM friends WHERE user1 = \"" + fromUserName + "\";");
//			while (rs.next()) {
//				assertEquals(toUserName, rs.getString(2));
//			}

			//Remove what was added to the database
			dal.removeFriendPair(toUserName, fromUserName);
			dal.removeMessageForUser(toUserName);

		}
	}

	@Test
	public void testMessages() throws SQLException {
		DAL dal = new DAL();
		Statement stmt = dal.getStatement();
		User toUser = new User("Bruno");
		for (int i = 1; i <= 10; i++) {
			User fromUser = new User ("user" + i);
			Quiz quiz = new Quiz(dal, "quiz" + i, "super awesome quiz!", true, true, true, false, "creator" + i, new Date(), i);
			NoteMessage note = new NoteMessage(fromUser.getLoginName(), toUser.getLoginName(), "I love you", dal);
			ChallengeMessage challenge = new ChallengeMessage(fromUser.getLoginName(), toUser.getLoginName(), quiz, dal, i);
			FriendRequestMessage request = new FriendRequestMessage(fromUser.getLoginName(), toUser.getLoginName(), dal);
		}

		ResultSet rs = stmt.executeQuery("SELECT * FROM messages WHERE toUser = \"" + toUser.getLoginName() + "\" ORDER BY messageType;");
		rs.last();
		//check that all 30 messages where added
//		assertEquals(3*10, rs.getRow());
//		
//		//check if we have 10 of each type
//		rs.beforeFirst();
//		for (int i = 1; i <= 10; i++) {
//			rs.next();
//			assertEquals(Message.CHALLENGE_MESSAGE, rs.getString("messageType"));
//		}
//		for (int i = 1; i <= 10; i++) {
//			rs.next();
//			assertEquals(Message.FRIEND_REQUEST_MESSAGE, rs.getString("messageType"));
//		}
//		for (int i = 1; i <= 10; i++) {
//			rs.next();
//			assertEquals(Message.NOTE_MESSAGE, rs.getString("messageType"));
//		}

		//Remove what we added to the database
		dal.removeMessageForUser(toUser.getLoginName());

	}

	
	@Test
	public void testHistoriesTable() throws SQLException {
		DAL dal = new DAL();
		String loginName = "Bruno";
		Statement stmt = dal.getStatement();
		for (int i = 1; i <= 10; i++) {
			Quiz quiz = new Quiz(dal, "quiz" + i, "super awesome quiz!", true, true, true, false, "creator" + i, new Date(), i);
			new HistoryObject(loginName, quiz, dal);
		}
		
		//See if we have the correct number of histories
		ResultSet rs = stmt.executeQuery("SELECT * FROM histories WHERE loginName = \"" + loginName + "\" ORDER BY quizName DESC;");
		rs.last();
		assertEquals(10, rs.getRow());
		rs.beforeFirst();
		
		//See if the name is correct;
		for (int i = 1; i <=10; i++) {
			rs.next();
			assertEquals(i, rs.getRow());
			//assertEquals("quiz" + i, rs.getString("quizName"));
		}
		
		dal.removeUserHistory(loginName);
	}

}

