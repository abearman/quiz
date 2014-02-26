package quiz;

import java.sql.SQLException;
import java.sql.Statement;

public class FriendRequestMessage extends Message {
	
	private String message;

	public FriendRequestMessage(String fromUser, String toUser, DBConnection con) {
		super(fromUser, toUser, con);
		message = fromUser + " wants to be friends with you! Do you accept the request?";
	}
	
	public void acceptRequest(boolean accept) {
		Statement stmt = getCon().getStatement();
		if (accept) {
			//update MySQL database
			try {
				String fromUser = getFromUser();
				String toUser = getToUser();
				
				String update = "INSERT INTO friends VALUES(\"" + fromUser + "\",\"" + toUser + "\") , "
														+ "(\"" + toUser + "\",\"" + fromUser + "\")";
				stmt.executeUpdate(update);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public String getMessage() {
		return message;
	}
	
}
