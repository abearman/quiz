package quiz;

public class Message {
	
	private String fromUser;
	private String toUser;
	private DBConnection con;
	
	public Message(String fromUser, String toUser, DBConnection con) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.con = con;
	}
	
	public String getFromUser() {
		return fromUser;
	}
	
	public String getToUser() {
		return toUser;
	}
	
	public DBConnection getCon() {
		return con;
	}
	
	
}
