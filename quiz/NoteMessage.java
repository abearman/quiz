package quiz;

public class NoteMessage extends Message {

	private String message;

	public NoteMessage(String fromUser, String toUser, String message) {
		super(fromUser, toUser);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
