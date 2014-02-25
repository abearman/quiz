package quiz;

public class Message {
	
	private String fromUser;
	private String toUser;
	
	public Message(String fromUser, String toUser) {
		this.fromUser = fromUser;
		this.toUser = toUser;
	}
	
	public String getFromUser() {
		return fromUser;
	}
	
	public String getToUser() {
		return toUser;
	}
	
	
}
