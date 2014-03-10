package quiz;

import java.util.Date;

public class NoteMessage extends Message {

	private String message;

	public NoteMessage(String fromUser, String toUser, String message, DAL dal) {
		super(fromUser, toUser, Message.NOTE_MESSAGE, new Date(), dal);
		this.message = message;
		dal.addMessageForUser(fromUser, toUser, Message.NOTE_MESSAGE, message, null, -1, this.getSendDate());
	}
	
	//Used for building
	public NoteMessage(String fromUser, String toUser, String message, java.util.Date sendDate, DAL dal, boolean building) {
		super(fromUser, toUser, Message.NOTE_MESSAGE, sendDate, dal);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
