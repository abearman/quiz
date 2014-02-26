package quiz;

public class NoteMessage extends Message {

	private String message;

	public NoteMessage(String fromUser, String toUser, String message, DBConnection con) {
		super(fromUser, toUser, con);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
