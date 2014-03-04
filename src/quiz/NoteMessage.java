package quiz;

public class NoteMessage extends Message {

	private String message;

	public NoteMessage(String fromUser, String toUser, String message, DAL dal) {
		super(fromUser, toUser, Message.NOTE_MESSAGE,dal);
		this.message = message;
		dal.addMessageForUser(fromUser, toUser, Message.NOTE_MESSAGE, message, null, -1);
	}
	
	//Used for building
	public NoteMessage(String fromUser, String toUser, String message, DAL dal, boolean building) {
		super(fromUser, toUser, Message.NOTE_MESSAGE,dal);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
