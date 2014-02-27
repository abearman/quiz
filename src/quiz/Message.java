package quiz;

import java.sql.Statement;

public class Message {
	
	/* Constants representing types of Messages */
	public static final String NOTE_MESSAGE = "NoteMessage";
	public static final String CHALLENGE_MESSAGE = "ChallengeMessage";
	public static final String FRIEND_REQUEST_MESSAGE = "FriendRequestMessage";
	
	private String fromUser;
	private String toUser;
	private DAL dal;
	
	public Message(String fromUser, String toUser, DAL dal) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.dal = dal;
	}
	
	public String getFromUser() {
		return fromUser;
	}
	
	public String getToUser() {
		return toUser;
	}
	
	public DAL getDAL() {
		return dal;
	}
	
	
}
