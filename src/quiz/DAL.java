package quiz;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

public class DAL {

	/* Private instance variables */
	private DBConnection db;
	private Statement stmt;

	/* Constructor */
	public DAL() {
		db = new DBConnection();
		stmt = db.getStatement();
	}

	public Statement getStatement() {
		return stmt;
	}
	
	///////////////////////////////////////////////////
	/** NEWSFEED */
	
	public ArrayList<NewsfeedObject> getNewsfeed(String loginName) {
		ArrayList<NewsfeedObject> newsfeed = new ArrayList<NewsfeedObject>();
		
		//Get all of loginName's friends
		ArrayList<String> friendList = getFriendListForUser(loginName);
		
		//Get all created quizzes, sorted by creationDate (that were created by friends of this user)
		ArrayList<NewsfeedObject> allCreatedQuizzes = getAllCreatedQuizzesForNewsFeed(friendList); 
		
		//Get all taken quizzes, sorted by dateValue (that were taken by friends of this user)
		ArrayList<NewsfeedObject> allTakenQuizzes = getAllTakenQuizzesForNewsFeed(friendList);
		
		//Get all achievements, sorted by achievementDate (that were achieved by friends of this user) ==> Do this later //TODO
		//Get all statuses, sorted by date (that were posted by friends of this user) ==> Do this later //TODO
		
		//Sort these interweavingly, by date
		int i = 0, j = 0;
		while (i < allCreatedQuizzes.size() && j < allTakenQuizzes.size()) {
			NewsfeedObject cq  = allCreatedQuizzes.get(i);
			NewsfeedObject tq = allTakenQuizzes.get(j);
			java.sql.Date cqDate = cq.getDate();
			java.sql.Date tqDate = tq.getDate();
			
			if (cqDate.after(tqDate)) { //if (cqDate < tqDate), more recent should go first
				newsfeed.add(cq);
				i++;
			} else {
				newsfeed.add(tq);
				j++;
			}
		}
		
		while (i < allCreatedQuizzes.size()) {
			newsfeed.add(allCreatedQuizzes.get(i++));
		}
		
		while (j < allTakenQuizzes.size()) {
			newsfeed.add(allTakenQuizzes.get(j++));
		}
		
		//Return an ArrayList of these
		return newsfeed;
	}

	///////////////////////////////////////////////////
	/**  USERS TABLE */
	
	/* Getters */
	
	/**
	 * Administrative site statistics getter: number of users
	 */
	public int getNumberOfUsers() {
		String query = "SELECT * FROM users;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public User getUser(String loginName) {
		User user = new User(loginName);
		String query = "SELECT * FROM users WHERE loginName = \"" + loginName + "\";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			user.setNewPassword(rs.getString("password"));

			user.isAdministrator = (rs.getBoolean("isAdministrator")) ? true : false;

			String achievements = rs.getString("achievements");
			for (int i = 0; i < achievements.length(); i++) {
				if (achievements.charAt(i) == '1') user.achievements[i] = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean accountExists(String loginName) {
		String query = "SELECT * FROM users WHERE loginName = \"" + loginName + "\";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) { //If there exists a record with this loginName in the database return true
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isPasswordForAccount(String loginName, String passwordClear, String hashOfAttemptedPassword) {
		String query = "SELECT * FROM users WHERE loginName = \"" + loginName + "\";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			String databasePasswordHash = rs.getString("password"); //Retrieves the stored hash from the Database for this User
			if (hashOfAttemptedPassword.equals(databasePasswordHash)) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getUserAchievements(String userName) {
		try {
			String query = "SELECT * FROM users WHERE loginName = \"" + userName + "\";";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return rs.getString("achievements");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isUserAdmin(String userName) {
		try {
			String query = "SELECT * FROM users WHERE loginname = \"" + userName + "\";";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			if (rs.getBoolean("isAdministrator")) return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//retrieves all of the user's friends' most recent activity
	public ArrayList<FriendRecentActivity> getFriendsRecentActivity(ArrayList<String> friends) {
		ArrayList<FriendRecentActivity> fra = new ArrayList<FriendRecentActivity>();
		for(String f : friends) {
			String query = "SELECT * FROM users WHERE loginName = \"" + f + "\";";
			try {
				ResultSet rs = stmt.executeQuery(query);
				rs.first();
				
				String activity = rs.getString("recentActivity");
				System.out.println(activity);
				
				StringTokenizer st = new StringTokenizer(rs.getString("recentActivity"), "\n");
				FriendRecentActivity act = new FriendRecentActivity(f);
				act.setRecentAchievement(Integer.getInteger(st.nextToken()));
				act.setRecentlyTakenQuiz(st.nextToken());
				act.setRecentlyCreatedQuiz(st.nextToken());
				fra.add(act);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fra;
	}
	
	/* Setters */
	
	public void insertUser(String loginName, boolean isAdministrator, String passwordHash, boolean[] achievements, String recentActivity) {
		String achievementsString = "000000"; //Initialized to all 0's for all "false"
		try {
			String update = "INSERT INTO users VALUES(\"" + loginName + "\", " + isAdministrator + ", \"" + passwordHash + "\", \"" + achievementsString + "\", \"" + recentActivity + "\");";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}

	public void removeUser(String loginName) {
		try {
			String update = "DELETE FROM users WHERE loginName = \"" + loginName + "\"";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addAchievementForUser(String loginName, int index) {
		try {
			String query = "SELECT * FROM users WHERE loginName = \"" + loginName + "\";";
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			String achievements = rs.getString("achievements");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < Achievements.NUM_ACHIEVEMENTS; i++) {
				if (i == index) {
					sb.append('1');
				} else {
					sb.append(achievements.charAt(i));
				}
			}
			String update = "UPDATE users SET achievements = \"" + sb.toString() + "\" WHERE loginName = \"" + loginName + "\";";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserAchievements(String loginName, String achievementsString) {
		String update = "UPDATE users SET achievements = \"" + achievementsString + "\" WHERE loginName = \"" + loginName + "\";";
		try {
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changeIsAdministrator(String loginName, boolean isAdmin) {
		String update = "UPDATE users SET isAdministrator = " + isAdmin + " WHERE loginName = \"" + loginName + "\";";
		try {
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//updates a user's most recent activity
	public void updateRecentUserActivity(String loginName, String recentActivity) {
		String execute = "UPDATE users SET recentActivity= \""+recentActivity+"\" WHERE loginName= \""+loginName+"\";";
		try { 
			stmt.executeUpdate(execute);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	//////////////////////////////////////////////
	/** QUIZZES TABLE */
	
	/* Getters */
	
	public Quiz getQuiz(String quizName) {
		Quiz quiz = new Quiz(this, quizName);
		return quiz;
	}
	
	public int getNumberQuizzesCreatedForUser(String userName) {
		try {
			String query = "SELECT * FROM quizzes WHERE creatorName = \"" + userName + "\";";
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//check if a quiz exists in the database
	public boolean doesQuizExist(String quizName) {
		String query = "SELECT * FROM quizzes WHERE quizName = \""+quizName+"\";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/* 
	 * Returns a list of all created quizzes, ordered by date, created by users who are friends 
	 * of a given user (are included in the friendList) 
	 * */
	public ArrayList<NewsfeedObject> getAllCreatedQuizzesForNewsFeed(ArrayList<String> friendList) {
		ArrayList<NewsfeedObject> recentlyCreatedQuizzes = new ArrayList<NewsfeedObject>();
		
		String query = "SELECT * FROM quizzes ORDER BY creationDate DESC;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (friendList.contains(rs.getString("creatorName"))) {
					String loginName = rs.getString("creatorName");
					String action = NewsfeedObject.CREATED_A_QUIZ_STRING;
					String quizName = rs.getString("quizName");
					java.sql.Date date = rs.getDate("creationDate");
					NewsfeedObject nfo = new NewsfeedObject(loginName, action, true, quizName, date);
					recentlyCreatedQuizzes.add(nfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recentlyCreatedQuizzes;
	}
		
		
	public ArrayList<String> getRecentlyCreatedQuizzes() {
		ArrayList<String> recentlyCreatedQuizzes = new ArrayList<String>();
		String query = "SELECT * FROM quizzes ORDER BY creationDate DESC LIMIT 0, 10;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				recentlyCreatedQuizzes.add(rs.getString("quizName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recentlyCreatedQuizzes;
	}
	
	public ArrayList<String> getUserRecentlyCreatedQuizzes(String username) {
		ArrayList<String> usersRecentlyCreatedQuizzes = new ArrayList<String>();
		String query = "SELECT * FROM quizzes WHERE creatorName = \"" + username + "\" ORDER BY creationDate DESC LIMIT 0, 10;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				usersRecentlyCreatedQuizzes.add(rs.getString("quizName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersRecentlyCreatedQuizzes;
	}
	
	/**
	 * Administrative site statistics getter: number of quizzes created
	 */
	public int getNumberOfQuizzesCreated() {
		String query = "SELECT * FROM quizzes;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/* Sorted by the number of times each quiz has been taken */
	public ArrayList<String> getPopularQuizzes() {
		ArrayList<String> popularQuizzes = new ArrayList<String>();
		String query = "SELECT * FROM quizzes ORDER BY numTimesTaken DESC LIMIT 0, 10;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				popularQuizzes.add(rs.getString("quizName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return popularQuizzes;
	}
	
	//	public ArrayList<Quiz> getAllQuizzes() {
	//	ArrayList<Quiz> allQuizzes = new ArrayList<Quiz>();
	//	
	//	try {
	//		String query = "SELECT * FROM quizzes;";
	//		ResultSet rs = stmt.executeQuery(query);
	//		while (rs.next()) {
	//			Quiz q = new Quiz(this, rs.getString("quizName"));
	//			allQuizzes.add(q);
	//		}
	//	} catch (SQLException e) {
	//		e.printStackTrace();
	//	}
	//	return allQuizzes;
	//}
	
	public ArrayList<Quiz> getAllQuizzes() {
		ArrayList<Quiz> allQuizzes = new ArrayList<Quiz>();
		
		try {
			String query = "SELECT * FROM quizzes;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String quizName = rs.getString("quizName");
				String description = rs.getString("description");
				boolean isRandom = (Boolean) rs.getObject("isRandom");
				boolean isMultiplePage = (Boolean) rs.getObject("isMultiplePage");
				boolean isImmediateCorrection = (Boolean) rs.getObject("isImmediateCorrection");
				boolean canBeTakenInPracticeMode = (Boolean) rs.getObject("canBeTakenInPracticeMode");
				String creatorName = rs.getString("creatorName");
				java.util.Date creationDate = rs.getDate("creationDate");
				int numTimesTaken = rs.getInt("numTimesTaken");
				
				allQuizzes.add(new Quiz(this, quizName, description, isRandom, isMultiplePage, isImmediateCorrection, 
						canBeTakenInPracticeMode, creatorName, creationDate, numTimesTaken, true));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Quiz q : allQuizzes) {
			q.initializeArrayLists();
		}
		return allQuizzes;
	}
	
	//what does this do??
	public String getNameOfQuiz(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (String)quizResultSet.getObject("quizName");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return "";
	}

	public String getDescriptionOfQuiz(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (String)quizResultSet.getObject("description");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return "";
	}

	public boolean getIsRandomOfQuiz(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (Boolean) quizResultSet.getObject("isRandom");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return false;
	}

	public boolean getIsMultiplePageOfQuiz(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (Boolean) quizResultSet.getObject("isMultiplePage");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return false;
	}

	public boolean getIsImmediateCorrectionOfQuiz(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (Boolean) quizResultSet.getObject("isImmediateCorrection");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return false;
	}

	public boolean getCanBeTakenInPracticeModeOfQuiz(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (Boolean) quizResultSet.getObject("canBeTakenInPracticeMode");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return false;
	}

	public String getCreatorName(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (String) quizResultSet.getObject("creatorName");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return "";
	}

	public java.util.Date getCreationDate(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (java.util.Date) quizResultSet.getObject("creationDate");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return null;
	}

	public int getNumTimesTaken(String givenQuizName){
		try{
			ResultSet quizResultSet = stmt.executeQuery("SELECT * FROM quizzes WHERE quizName = \"" + givenQuizName + "\";");
			if (quizResultSet.next()){
				quizResultSet.first();
				return (Integer) quizResultSet.getObject("numTimesTaken");
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return 0;
	}
	
	/* Setters */
	
	public void insertQuiz(Quiz quiz) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(quiz.getCreationDate());
		try {
			String update = "INSERT INTO quizzes VALUES(\""+quiz.getQuizName()+"\",\""+quiz.getDescriptionOfQuiz()+"\","+ quiz.isRandom()+","+quiz.isMultiplePage()+","+quiz.isImmediateCorrection()+","+quiz.canBeTakenInPracticeMode()+",\"" + 
					quiz.getCreatorName() + "\",'" + currentTime + "'," + quiz.getNumTimesTaken() + ");";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}

	public void removeQuiz(String quizName) {
		try {
			String update = "DELETE FROM quizzes WHERE quizName = \"" + quizName + "\";";
			stmt.executeUpdate(update);
			update = "DELETE FROM questionResponse WHERE quizName = \"" + quizName + "\";";
			stmt.executeUpdate(update);
			update = "DELETE FROM fillInTheBlank WHERE quizName = \"" + quizName + "\";";
			stmt.executeUpdate(update);
			update = "DELETE FROM multipleChoice WHERE quizName = \"" + quizName + "\";";
			stmt.executeUpdate(update);
			update = "DELETE FROM pictureResponse WHERE quizName = \"" + quizName + "\";";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void incrementNumTimesTaken(String quizName){
		try {
			int numTimesTaken = getNumTimesTaken(quizName);
			String update = "UPDATE quizzes SET numTimesTaken = " + (numTimesTaken+1) + " WHERE quizName = \"" + quizName + "\";";
			stmt.executeUpdate(update);
		} catch (SQLException e){
			e.printStackTrace(); 
		}
	}
	
	///////////////////////////////////////////////////
	/** HISTORIES TABLE */
	
	/* Getters */
	
	public int getNumberQuizzesTakenForUser(String userName) {
		try {
			String query = "SELECT * FROM histories WHERE loginName = \"" + userName + "\";";
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Administrative site statistics getter: number of quizzes taken
	 */
	public int getNumberOfQuizzesTaken() {
		String query = "SELECT * FROM histories;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/* 
	 * Returns a list of all taken quizzes, ordered by date, taken by users who are friends 
	 * of a given user (are included in the friendList) 
	 * */
	public ArrayList<NewsfeedObject> getAllTakenQuizzesForNewsFeed(ArrayList<String> friendList) {
		ArrayList<NewsfeedObject> recentlyTakenQuizzes = new ArrayList<NewsfeedObject>();
		String query = "SELECT * FROM histories ORDER BY dateValue DESC;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String loginName = rs.getString("loginName");
				if (friendList.contains(rs.getString("loginName"))) {
					String action = NewsfeedObject.TOOK_A_QUIZ_STRING;
					String quizName = rs.getString("quizName");
					java.sql.Date date = rs.getDate("dateValue");
					NewsfeedObject nfo = new NewsfeedObject(loginName, action, true, quizName, date);
					recentlyTakenQuizzes.add(nfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recentlyTakenQuizzes;
	}

	public ArrayList<String> getRecentlyTakenQuizzes() {
		ArrayList<String> recentlyTakenQuizzes = new ArrayList<String>();
		String query = "SELECT * FROM histories ORDER BY dateValue DESC LIMIT 0, 10;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				recentlyTakenQuizzes.add(rs.getString("quizName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recentlyTakenQuizzes;
	}
	
	public ArrayList<String> getUserRecentlyTakenQuizzes(String username) {
		ArrayList<String> usersRecentlyTakenQuizzes = new ArrayList<String>();
		String query = "SELECT * FROM histories WHERE loginName = \"" + username + "\" ORDER BY dateValue DESC LIMIT 0, 10;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				usersRecentlyTakenQuizzes.add(rs.getString("quizName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersRecentlyTakenQuizzes;
	}
	
	public boolean isHighestScorerForQuiz(String userName, String quizName) {
		try {
			String query = "SELECT * FROM histories WHERE quizName = \"" + quizName + "\" ORDER BY numQuestionsCorrect DESC;";
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			if (rs.getString("loginName").equals(userName)) return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<HistoryObject> getHistoryListForUser(String userName) {
		ArrayList<HistoryObject> historyList = new ArrayList<HistoryObject>();
		try {
			String query = "SELECT * FROM histories WHERE loginName = \"" + userName + "\";";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String loginName = rs.getString("loginName");
				String quizName = rs.getString("quizName");
				int numQuestionsCorrect = rs.getInt("numQuestionsCorrect");
				long timeElapsed = rs.getLong("timeElapsed");
				Timestamp sqlDate = rs.getTimestamp("dateValue");
				historyList.add(new HistoryObject(loginName, quizName, numQuestionsCorrect, timeElapsed, new java.util.Date(sqlDate.getTime()), this));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historyList;
	}
	
	public ArrayList<HistoryObject> getAllHistoryLists(String quizTitle) {
		ArrayList<HistoryObject> result = new ArrayList<HistoryObject>();

		try {
			String query = "SELECT * FROM histories WHERE quizName =\""+quizTitle+"\";";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()){
				rs.beforeFirst();
				while(rs.next()) {
					String loginName = rs.getString("loginName");
					String quizName = rs.getString("quizName");
					int numQuestionsCorrect = rs.getInt("numQuestionsCorrect");
					long timeElapsed = rs.getLong("timeElapsed");
					Timestamp sqlDate = rs.getTimestamp("dateValue");
					result.add(new HistoryObject(loginName, quizName, numQuestionsCorrect, timeElapsed, new java.util.Date(sqlDate.getTime()), this));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/* Setters */
	
	public void addToHistoryListForUser(String loginName, String quizName, int numQuestionsCorrect, long timeElapsed, java.util.Date utilDate) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(utilDate);
		try {
			String update = "INSERT INTO histories VALUES(\"" + loginName + "\", \"" + quizName + "\", " + numQuestionsCorrect + ", " + timeElapsed + ", '" + currentTime + "');";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearAllHistoryForQuiz(String quizName) {
		String update = "DELETE FROM histories WHERE quizName = \"" + quizName + "\";";
		try {
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeUserHistory(String loginName) {
		String update = "DELETE FROM histories WHERE loginName = \"" + loginName + "\";";
		try {
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////
	/** FRIENDS TABLE */
	
	/* Getters */
	
	public ArrayList<String> getFriendListForUser(String loginName) {
		ArrayList<String> friendList = new ArrayList<String>();
		String query = "SELECT * FROM friends WHERE user1 = \"" + loginName + "\";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String friend = rs.getString("user2");
				friendList.add(friend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendList;
	}
	
	public boolean areFriends(String friend1, String friend2) {
		boolean areFriends = false;
		String query = "SELECT * FROM friends WHERE user1 = \"" + friend1 + "\" AND user2 = \"" + friend2 + "\";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			areFriends = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areFriends;
	}
	
	/* Setters */
	
	public void addFriendPair(String user1, String user2) {
		try {
			String query = "SELECT * FROM friends WHERE user1 = \"" + user1 + "\" AND user2 = \"" + user2 + "\";";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) return; //Friend pair already exists
			String update = "INSERT INTO friends VALUES(\"" + user1 + "\",\"" + user2 + "\"), "
					+ "(\"" + user2 + "\",\"" + user1 + "\");";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeFriendPair(String user1, String user2) { 
		try {
			String update = "DELETE FROM friends WHERE user1 = \"" + user1 + "\" AND user2 = \"" + user2 + "\";";
			stmt.executeUpdate(update);
			update = "DELETE FROM friends WHERE user1 = \"" + user2 + "\" AND user2 = \"" + user1 + "\";";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	
	///////////////////////////////////////////////////
	/** TOPSCORERS TABLE */
	
	/* Getters */

	public ArrayList<TopScorer> getAllTopScorersForQuiz(String quizName) {
		ArrayList<TopScorer> topScorers = new ArrayList<TopScorer>();
		try {
			//Query database to get TopScorer's
			ResultSet topScorerResultSet = stmt.executeQuery("SELECT * FROM topscorers WHERE quizName = \"" + quizName + "\";");
			if (topScorerResultSet != null){
				topScorerResultSet.beforeFirst(); 

				while (topScorerResultSet.next()){
					String loginName = (String)topScorerResultSet.getObject("loginName");
					int numCorrectQuestions = (Integer)topScorerResultSet.getObject("numCorrectQuestions");
					double timeTaken = (Double)topScorerResultSet.getObject("timeTaken");

					//Add top scorer to TopScorer's array
					topScorers.add(new TopScorer(loginName, numCorrectQuestions, timeTaken, this));
				}

			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return topScorers;
	}
	
	/* Setters */
	
	public void addTopScorer(TopScorer topScorer, String quizName) {
		try {
			String update = "INSERT INTO topscorers VALUES(\""+quizName+"\",\""+topScorer.getLoginName()+"\","+ 
					topScorer.getNumCorrectQuestions()+","+topScorer.getTimeTaken()+");";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}

	public void removeTopScorer(String loginName, String quizName) {
		try {
			String update = "DELETE FROM topscorers WHERE quizName = \"" + quizName + "\" AND loginName = \"" + loginName + "\";";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}

	
	///////////////////////////////////////////////////
	/** MESSAGES TABLE */
	
	/* Getters */
	
	public ArrayList<Message> getFriendRequestMessages(String user) {
		ArrayList<Message> messages = new ArrayList<Message>();
		String query = "SELECT * FROM messages WHERE toUser = \"" + user + "\" AND messageType = \"" + Message.FRIEND_REQUEST_MESSAGE + "\";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				FriendRequestMessage frm = new FriendRequestMessage(rs.getString("fromUser"), user, this, true);
		 		messages.add(frm);
			}
		 } catch (SQLException e) {
			 e.printStackTrace(); 
		 }
		 return messages;
	}

	public ArrayList<Message> getUserMessages(User user) {
		ArrayList<Message> result = new ArrayList<Message>();
		String query = "SELECT * FROM messages WHERE toUser =\""+user.getLoginName()+"\";";
		try {
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()) {
				String fromUser = rs.getString("fromUser");
				String toUser = rs.getString("toUser");
				String messageType = rs.getString("messageType");
				String message = rs.getString("message");
				
				String quizName = "";
				double bestScore = -1;
				if (messageType.equals(Message.CHALLENGE_MESSAGE)) {
					quizName = rs.getString("quizName");
					bestScore = rs.getDouble("bestScore");
					result.add(new ChallengeMessage(fromUser, toUser, quizName, this, bestScore));
				} else if (messageType.equals(Message.FRIEND_REQUEST_MESSAGE)) {
					result.add(new FriendRequestMessage(fromUser, toUser, this, true));
				} else {
					result.add(new NoteMessage(fromUser, toUser, message, this, true));
				}				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean userHasNewMessages(String username) {
		String query = "SELECT * FROM messages WHERE toUser = \"" + username + "\";";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean userHasPendingFriendRequestForThisFriend(String userName, String friendName) {
		try {
			String query = "SELECT * FROM messages WHERE fromUser = \"" + userName + "\" AND toUser = \"" + friendName + "\" AND messageType = \"" + Message.FRIEND_REQUEST_MESSAGE + "\";";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) return true; 
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/* Setters */
	
	//VALUES and not "values"; messages, not users; need number of arguments in insert to be equivalent with number of columns
	public void addMessageForUser(String fromUser, String toUser, String type, String message, String quizName, double bestScore) {		
		try {
			if (type.equals(Message.NOTE_MESSAGE)) {
				String update = "INSERT INTO messages VALUES(\"" + fromUser + "\", \"" + toUser + "\", \"" + type + "\", \"" + message + "\", \"" + " " + "\", " + -1 + ");";
				stmt.executeUpdate(update);
			} else if (type.equals(Message.FRIEND_REQUEST_MESSAGE)) {
				String query = "SELECT * FROM messages WHERE fromUser = \"" + fromUser + "\" AND toUser = \"" + toUser + "\" AND messageType = \"" + Message.FRIEND_REQUEST_MESSAGE + "\";";
				ResultSet rs = stmt.executeQuery(query);
				if (!rs.next()) { //Only insert the message if the friend request doesn't already exist in the message table 
					String update = "INSERT INTO messages VALUES(\"" + fromUser + "\", \"" + toUser + "\", \"" + type + "\", \"" + message + "\", \"" + " " + "\", " + -1 + ");";
					stmt.executeUpdate(update);
				} 
			} else if (type.equals(Message.CHALLENGE_MESSAGE)) {
				String update = "INSERT INTO messages VALUES(\"" + fromUser + "\", \"" + toUser + "\", \"" + type + "\", \"" + message + "\", \"" + quizName + "\", " + bestScore + ");";
				stmt.executeUpdate(update);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeMessageForUser(String toUser) { 
		try {
			String update = "DELETE FROM messages WHERE toUser = \"" + toUser + "\";";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	
	public void removeFriendRequestMessage(String toUser, String fromUser) {
		try {
			String update = "DELETE FROM messages WHERE toUser = \"" + toUser + "\" AND fromUser = \"" + fromUser + "\" AND messageType = \"" + Message.FRIEND_REQUEST_MESSAGE + "\";";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////
	/** VARIOUS QUESTION TABLES */
	
	/* Getters */
	
	public ArrayList<Question> getQuestionsFromDB(String quizName){
		ArrayList<Question> questions = new ArrayList<Question>();
		try{
			//for question response
			ResultSet qrs = stmt.executeQuery("SELECT * FROM questionResponse WHERE quizName = \"" 
					+ quizName + "\";");
			if (qrs.next()){
				qrs.beforeFirst();
				while(qrs.next())
				{
					questions.add(new QuestionResponse(qrs.getString(2),Question.createArray(qrs.getString(3)),
							qrs.getInt(5)));
				}
			}

			//for fill-in-the-blank
			qrs = stmt.executeQuery("SELECT * FROM fillInTheBlank WHERE quizName = \"" 
					+ quizName + "\";");
			if (qrs.next()){
				qrs.beforeFirst();
				while(qrs.next())
				{
					questions.add(new FillInTheBlank(qrs.getString(2),Question.createArray(qrs.getString(3)),
							qrs.getInt(5)));
				}
			}

			//for multiple choice
			qrs = stmt.executeQuery("SELECT * FROM multipleChoice WHERE quizName = \"" 
					+ quizName + "\";");
			if (qrs.next()){
				qrs.beforeFirst();
				while(qrs.next())
				{
					questions.add(new MultipleChoice(qrs.getString(2),Question.createArray(qrs.getString(3)),
							qrs.getInt(5), Question.createArray(qrs.getString(6))));
				}
			}

			//for picture response
			qrs = stmt.executeQuery("SELECT * FROM pictureResponse WHERE quizName = \"" 
					+ quizName + "\";");
			if (qrs.next()){
				qrs.beforeFirst();
				while(qrs.next())
				{
					questions.add(new PictureResponse(qrs.getString(2),Question.createArray(qrs.getString(3)),
							qrs.getInt(5), qrs.getString(6)));
				}
			}
		} catch (SQLException e){
			e.printStackTrace(); 
		}
		return questions;
	}
	
	/* Setters */
	
	public void addQuestion(String quizName, Question question) {
		try {
			String update = "";
			if(question.getQuestionType() == Question.QUESTION_RESPONSE)
			{
				update = "INSERT INTO questionResponse VALUES(\""+quizName+"\",\""+question.getQuestion()
						+"\",\""+question.createAnswerString()+"\","+question.getQuestionType()+","
						+question.getQuestionNumber()+");";
			}
			if(question.getQuestionType() == Question.FILL_IN_THE_BLANK)
			{
				update = "INSERT INTO fillInTheBlank VALUES(\""+quizName+"\",\""+question.getQuestion()
						+"\",\""+question.createAnswerString()+"\","+question.getQuestionType()+","
						+question.getQuestionNumber()+");";
			}
			if(question.getQuestionType() == Question.MULTIPLE_CHOICE)
			{
				update = "INSERT INTO multipleChoice VALUES(\""+quizName+"\",\""+question.getQuestion()
						+"\",\""+question.createAnswerString()+"\","+question.getQuestionType()+","
						+question.getQuestionNumber()+",\""+((MultipleChoice)question).createChoicesString()+"\");";
			}
			if(question.getQuestionType() == Question.PICTURE_RESPONSE)
			{
				update = "INSERT INTO pictureResponse VALUES(\""+quizName+"\",\""+question.getQuestion()
						+"\",\""+question.createAnswerString()+"\","+question.getQuestionType()+","
						+question.getQuestionNumber()+",\""+((PictureResponse)question).getImageURL()+"\");";
			}
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeQuestion(String quizName, Question question){
		try {
			String update = "";
			if(question.getQuestionType() == Question.QUESTION_RESPONSE)
			{
				update = "DELETE FROM questionResponse WHERE quizName = \"" + quizName + "\" AND question = \"" + question.getQuestion() + "\";";
			}
			if(question.getQuestionType() == Question.FILL_IN_THE_BLANK)
			{
				update = "DELETE FROM fillInTheBlank WHERE quizName = \"" + quizName + "\" AND question = \"" + question.getQuestion() + "\";";
			}
			if(question.getQuestionType() == Question.MULTIPLE_CHOICE)
			{
				update = "DELETE FROM multipleChoice WHERE quizName = \"" + quizName + "\" AND question = \"" + question.getQuestion() + "\";";
			}
			if(question.getQuestionType() == Question.PICTURE_RESPONSE)
			{
				update = "DELETE FROM pictureResponse WHERE quizName = \"" + quizName + "\" AND question = \"" + question.getQuestion() + "\";";
			}
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	
	/////////////////////////////////////////////////
	/** ANNOUNCEMENTS TABLE */
	
	public void createAnnouncement(String announcement) {
		try {
			String update = "INSERT INTO announcements VALUES(\"" + announcement + "\");";
			stmt.executeUpdate(update);
		} catch (SQLException e){
			e.printStackTrace(); 
		}
	}

	public ArrayList<String> getAllAnnouncements() {
		ArrayList<String> announcements = new ArrayList<String>();
		String query = "SELECT * FROM announcements;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				announcements.add(rs.getString("announcement"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return announcements;
	}
	//////////////////////////////////////

}


