package quiz;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AccountManager {

	/* Instance variables */
	private DBConnection conn;
	private Statement stmt;
	
	/* Constructor */
	public AccountManager(DBConnection conn) {
		this.conn = conn;
		this.stmt = conn.getStatement();
	}
	
	/* Private helper method. Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array. */
	private String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/* Private helper method to convert the user's password from clear text
	 * to a hash, and to store the hash as an instance variable */
	private String hashPassword(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA"); //Initialize the MessageDigest
			md.reset();
			byte[] buffer = password.getBytes(); //Converts password to array of bytes
			md.update(buffer);
			byte[] digest = md.digest();
			return hexToString(digest);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace(); //TODO: How should we handle this exception?
			return null;
		}
	}
	
	/* Given an account name, returns a boolean representing whether or not the account already exists */
	public boolean accountExists(String loginName) {
		String query = "SELECT * FROM users WHERE loginName = \"" + loginName + "\"";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) { //If there exists a record with this loginName in the database return true
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/* Given an account name and a password String, returns a boolean representing whether or not 
	 * the password parameter matches the password for the specified account */
	public boolean isPasswordForAccount(String loginName, String passwordClear) {
		String query = "SELECT * FROM users WHERE loginName = \"" + loginName + "\"";
		try {
			ResultSet rs = stmt.executeQuery(query);
			String databasePasswordHash = rs.getString("password"); //Retrieves the stored hash from the Database for this User
			String hashOfAttemptedPassword = hashPassword(passwordClear);
			if (hashOfAttemptedPassword.equals(databasePasswordHash)) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/* Given an account name and a password, creates a new account */
	public void createNewAccount(String loginName, String passwordClear) {
		User user = new User(loginName, passwordClear, conn); //The constructor handles putting the User in the database
	}
	
}
