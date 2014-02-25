package quiz;

public class FriendRequestMessage extends Message {
	
	private String message;

	public FriendRequestMessage(String fromUser, String toUser) {
		super(fromUser, toUser);
		message = fromUser + " wants to be friends with you! Do you accept the request?";
	}
	
	public void acceptRequest(boolean accept) {
		if (accept) {
			//update MySQL database
//			String fromUser = getFromUser();
//			String toUser = getToUser();
//			
//			String update = "INSERT INTO friends VALUES(\"" + fromUser + "\",\"" + toUser + "\") , "
//													+ "(\"" + toUser + "\",\"" + fromUser + "\")";
//			stmt.executeUpdate(update);

		}
	}
	
	public String getMessage() {
		return message;
	}
	
}
