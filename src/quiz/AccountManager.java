package quiz;

import java.util.HashMap;

public class AccountManager {

	/* Instance variables */
	private HashMap<String, String> map; //Maps loginNames to passwordHash
	
	/* Constructor */
	public AccountManager() {
		map = new HashMap<String, String>();
	}
	
	/* Given an account name, returns a boolean representing whether or not the account already exists */
	public boolean accountExists(String loginName) {
		if (map.containsKey(loginName)) {
			return true;
		}
		return false;
	}
	
	/* Given an account name and a password String, returns a boolean representing whether or not 
	 * the password parameter matches the password for the specified account */
	public boolean isPasswordForAccount(String loginName, String passwordClear) {
		User user = new User(loginName, passwordClear);
		String passwordHash = user.getPasswordHash(); //Hash the password they enter to log in
		if (map.get(loginName).equals(passwordHash)) { //Compares the hash of the password they enter to the hash stored when the account was created
			return true; //If they match, let them log in to this account
		}
		return false;
	}
	
	/* Given an account name and a password, creates a new account */
	public void createNewAccount(String loginName, String passwordClear) {
		User user = new User(loginName, passwordClear);
		String passwordHash = user.getPasswordHash(); //Hashes their password and stores the hashed version
		map.put(loginName, passwordHash);
	}
	
}
