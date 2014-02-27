package quiz;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class User {
	
	/* Instance variables*/
	private boolean isAdministrator;
	private String loginName;
	private String passwordHash;
	private boolean hasNewMessages;
	
	private ArrayList<String> friends;
	private ArrayList<HistoryObject> historyList;
	private boolean[] achievements;
	private ArrayList<Message> messages;
	
	private ArrayList<String> recentlyTakenQuizzes;
	private ArrayList<String> recentlyCreatedQuizzes;
	private ArrayList<FriendRecentActivity> friendsRecentActivity;
	
	private DAL dal; //Handles connection to Database
	
	/* Private helper method to set all achievements to false */
	private void initAchievementsArray() {
		for (int i = 0; i < achievements.length; i++) {
			achievements[i] = false;
		}
	}
	
	/* Private helper method. Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array. */
	private String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/* Private helper method to convert the user's password from clear text
	 * to a hash, and to store the hash as an instance variable */
	private void hashPassword(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA"); //Initialize the MessageDigest
			md.reset();
			byte[] buffer = password.getBytes(); //Converts password to array of bytes
			md.update(buffer);
			byte[] digest = md.digest();
			String result = hexToString(digest);
			this.passwordHash = result;
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace(); //TODO: How should we handle this exception?
		}
	}
		
	/* Constructor */
	public User(String loginName, String password, DAL dal) {
		this.loginName = loginName;
		hashPassword(password);
		this.isAdministrator = false; //By default, a user is not an administrator
		this.hasNewMessages = false;
		
		friends = new ArrayList<String>();
		friends = initializeFriends();
		achievements = new boolean[Achievements.NUM_ACHIEVEMENTS];
		initAchievementsArray();
		historyList = initializeHistoryList();
		messages = new ArrayList<Message>();
		
		recentlyTakenQuizzes = new ArrayList<String>();
		recentlyCreatedQuizzes = new ArrayList<String>();
		
		this.dal = dal;
		dal.insertUser(loginName, isAdministrator, passwordHash, achievements);
	}
	
	private ArrayList<HistoryObject> initializeHistoryList() {
		return dal.getHistoryListForUser(this.loginName);
	}
	
	private ArrayList<String> initializeFriends() {
		return dal.getFriendListForUser(this.loginName);
	}
	
	/* Getter methods */
	
	public String getLoginName() {
		return this.loginName;
	}
	
	public String getPasswordHash() {
		return this.passwordHash;
	}
	
	public boolean getIsAdministrator() {
		return this.isAdministrator;
	}
	
	public ArrayList<String> getFriends() {
		return this.friends;
	}
	
	public ArrayList<HistoryObject> getHistory() {
		return this.historyList;
	}
	
	public boolean[] getAchievements() {
		return this.achievements;
	}
	
	public ArrayList<Message> getMessages() {
		return this.messages;
	}
	
	public ArrayList<FriendRecentActivity> getListOfFriendsRecentActivities() {
		return this.friendsRecentActivity;
	}
	
	/* Setter methods */
	
	public void setAsAdministrator() {
		this.isAdministrator = true; //Updates the instance variable
		dal.changeIsAdministrator(this.loginName, true); //Changes it in the database
	}
	
	public void setAsNotAdministrator() {
		this.isAdministrator = false; //Updates the instance variable
		dal.changeIsAdministrator(this.loginName, false); //Changes it in the database
	}
	
	public void addFriendPair(String friendName) { 
		friends.add(friendName);
		dal.addFriendPair(loginName, friendName);
	}	
	
	public void removeFriendPair(String friendName) { //TODO Database?
		friends.remove(friendName);
		dal.removeFriendPair(loginName, friendName);
	}
	 
	public void addMessage(User toUser, String type, String message, Quiz quiz) { 
		if (type.equals(Message.NOTE_MESSAGE)) {
			NoteMessage nm = new NoteMessage(this.loginName, toUser.getLoginName(), message, dal);
			messages.add(nm);
			dal.addMessageForUser(this.loginName, toUser.getLoginName(), Message.NOTE_MESSAGE, message, null, -1);
		} else if (type.equals(Message.FRIEND_REQUEST_MESSAGE)) {
			FriendRequestMessage frm = new FriendRequestMessage(this.loginName, toUser.getLoginName(), dal);
			messages.add(frm);
			dal.addMessageForUser(this.loginName, toUser.getLoginName(), Message.FRIEND_REQUEST_MESSAGE, message, null, -1);
		} else if (type.equals(Message.CHALLENGE_MESSAGE)) {
			ChallengeMessage cm = new ChallengeMessage(this, toUser, quiz, dal);
			messages.add(cm);
			dal.addMessageForUser(this.loginName, toUser.getLoginName(), Message.CHALLENGE_MESSAGE, message, quiz.getQuizName(), cm.challengingUserBestScore(this));
		}
	}
	
	public void removeMessage(Message message) { //TODO Database?
		messages.remove(message);
		dal.removeMessageForUser(loginName);
	}
	
	public void addAchievement(int index) {
		achievements[index] = true; //Updates the instance variable 
		String achievementsString = "";
		for (int i = 0; i < Achievements.NUM_ACHIEVEMENTS; i++) {
			if (i == index) {
				achievementsString.concat("1"); //Sets this achievement to be true
			} else {
				achievementsString.concat(achievements[i] + ""); //Leaves the achievement as before
			}
		}
		dal.updateUserAchievements(this.loginName, achievementsString);
	}
	
	public void deleteAchievement(int index){
		achievements[index] = false; //Updates the instance variable
		String achievementsString = "";
		for (int i = 0; i < Achievements.NUM_ACHIEVEMENTS; i++) {
			if (i == index) {
				achievementsString.concat("1"); //Sets this achievement to be true
			} else {
				achievementsString.concat(achievements[i] + ""); //Leaves the achievement as before
			}
		}
		dal.updateUserAchievements(this.loginName, achievementsString);	
	}
	
}
