package quiz;

public class Message {
	
	/* Constants representing types of Messages */
	public static final String NOTE_MESSAGE = "NoteMessage";
	public static final String CHALLENGE_MESSAGE = "ChallengeMessage";
	public static final String FRIEND_REQUEST_MESSAGE = "FriendRequestMessage";
	
	private String fromUser;
	private String toUser;
	private String messageType;
	private java.util.Date sendDate;
	private DAL dal;
	
	public Message(String fromUser, String toUser, String messageType, java.util.Date sendDate, DAL dal) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.sendDate = sendDate;
		this.dal = dal;
		this.messageType = messageType;
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
	
	public String getMessageType() {
		return messageType;
	}
	
	public java.util.Date getSendDate() {
		return sendDate;
	}
	
	
}
