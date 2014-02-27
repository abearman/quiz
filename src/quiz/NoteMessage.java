package quiz;

public class NoteMessage extends Message {

	private String message;

	public NoteMessage(String fromUser, String toUser, String message, DAL dal) {
		super(fromUser, toUser, dal);
		dal.addMessageForUser(fromUser, toUser, Message.NOTE_MESSAGE, message, null, -1);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
