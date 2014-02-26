package quiz;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	private DBConnection con; //Manages connection to database
	private Statement stmt;
	
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
	public User(String loginName, String password, DBConnection con) {
		this.loginName = loginName;
		hashPassword(password);
		this.isAdministrator = false; //By default, a user is not an administrator
		this.hasNewMessages = false;
		
		friends = new ArrayList<String>();
		achievements = new boolean[Achievements.NUM_ACHIEVEMENTS];
		initAchievementsArray();
		historyList = new ArrayList<HistoryObject>();
		messages = new ArrayList<Message>();
		
		recentlyTakenQuizzes = new ArrayList<String>();
		recentlyCreatedQuizzes = new ArrayList<String>();
		
		this.con = con;
		this.stmt = con.getStatement();
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
		isAdministrator = true;
	}
	
	public void setAsNotAdministrator() {
		isAdministrator = false;
	}
	
	public void addFriend(String friend) {
		friends.add(friend);
	}
	
	public void removeFriend(String friend) {
		friends.remove(friend);
	}
	
	public void deleteMessage(Message message) {
		messages.remove(message);
	}
	
}
