package quiz;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AccountManager {

	/* Instance variables */
	private DAL dal;
	
	/* Constructor */
	public AccountManager(DAL dal) {
		this.dal = dal;
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
		return dal.accountExists(loginName);
	}
	
	/* Given an account name and a password String, returns a boolean representing whether or not 
	 * the password parameter matches the password for the specified account */
	public boolean isPasswordForAccount(String loginName, String passwordClear) {
		String hashOfAttemptedPassword = hashPassword(passwordClear);
		return dal.isPasswordForAccount(loginName, passwordClear, hashOfAttemptedPassword);
	}
	
}
