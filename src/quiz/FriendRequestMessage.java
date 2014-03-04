package quiz;

public class FriendRequestMessage extends Message {
	
	private String message;
	private DAL dal;

	public FriendRequestMessage(String fromUser, String toUser, DAL dal) {
		super(fromUser, toUser, Message.FRIEND_REQUEST_MESSAGE,dal);
		message = fromUser + " wants to be friends with you! Do you accept the request?";
		this.dal = dal;
		dal.addMessageForUser(fromUser, toUser, Message.FRIEND_REQUEST_MESSAGE, message, null, -1);
	}
	
	//for building
	public FriendRequestMessage(String fromUser, String toUser, DAL dal, boolean building) {
		super(fromUser, toUser, Message.FRIEND_REQUEST_MESSAGE,dal);
		message = fromUser + " wants to be friends with you! Do you accept the request?";
		this.dal = dal;
	}
	
	public void acceptRequest(boolean accept) {
		if (accept) {
			dal.addFriendPair(getFromUser(), getToUser());
		}
	}
	
	public String getMessage() {
		return message;
	}
	
}
