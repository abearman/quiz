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
		friends = initializeFriends();
		achievements = new boolean[Achievements.NUM_ACHIEVEMENTS];
		initAchievementsArray();
		historyList = initializeHistoryList();
		messages = new ArrayList<Message>();
		
		recentlyTakenQuizzes = new ArrayList<String>();
		recentlyCreatedQuizzes = new ArrayList<String>();
		
		this.con = con;
		this.stmt = con.getStatement();
		
		String achievementsString = "000000"; //Initialized to all 0's for all "false"
		try {
			String update = "INSERT INTO users VALUES(\"" + loginName + " \",\" " + isAdministrator + " \",\" " + passwordHash + " \",\" " + achievementsString + ");";
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace(); //TODO How do we want to handle this?
		}
	}
	
	private ArrayList<HistoryObject> initializeHistoryList() {
		ArrayList<HistoryObject> result = new ArrayList<HistoryObject>();
		
		try {
			String query = "SELECT * FROM histories WHERE loginName = \"" + loginName + "\"";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String loginName = rs.getString(1);
				String quizName = rs.getString(2);
				double score = rs.getDouble(3);
				long timeElapsed = rs.getLong(4);
				String dateString = rs.getString(5);
				result.add(new HistoryObject(loginName, quizName, score, timeElapsed, dateString, con));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private ArrayList<String> initializeFriends() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			String query1 = "SELECT * FROM friends WHERE user1 = \"" + loginName + "\"";
			ResultSet rs1 = stmt.executeQuery(query1);
			while(rs1.next()) {
				String user2 = rs1.getString(2);
				String query2 = "SELECT * FROM users WHERE loginName = \"" + user2 + "\"";
				ResultSet rs2 = stmt.executeQuery(query2);
				while (rs2.next()) {
					//add user to result (should friends just be an ArrayList<String>?)
					result.add(rs2.getString(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
		String update = "UPDATE users SET isAdministrator = true";
		try {
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setAsNotAdministrator() {
		isAdministrator = false;
		String update = "UPDATE users SET isAdministrator = false";
		try {
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public void addAchievement(int index) {
		achievements[index] = true;
		String achievementsString = "";
		for (int i = 0; i < Achievements.NUM_ACHIEVEMENTS; i++) {
			if (i == index) {
				achievementsString.concat("1"); //Sets this achievement to be true
			} else {
				achievementsString.concat(achievements[i] + ""); //Leaves the achievement as before
			}
		}
			
		String update = "UPDATE users SET achievements = \"" + achievementsString + "\"";
		try {
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAchievement(int index){
		achievements[index] = false;
		String achievementsString = "";
		for (int i = 0; i < Achievements.NUM_ACHIEVEMENTS; i++) {
			if (i == index) {
				achievementsString.concat("1"); //Sets this achievement to be true
			} else {
				achievementsString.concat(achievements[i] + ""); //Leaves the achievement as before
			}
		}
			
		String update = "UPDATE users SET achievements = \"" + achievementsString + "\"";
		try {
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
