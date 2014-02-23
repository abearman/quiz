package quiz;

import java.util.HashMap;

public class AccountManager {

	/* Instance variables */
	private HashMap<String, String> map;
	
	/* Constructor */
	public AccountManager() {
		map = new HashMap<String, String>();
	}
	
	/* Given an account name, returns a boolean representing whether or not the account already exists */
	public boolean accountExists(String accountName) {
		if (map.containsKey(accountName)) {
			return true;
		}
		return false;
	}
	
	/* Given an account name and a password String, returns a boolean representing whether or not 
	 * the password parameter matches the password for the specified account */
	public boolean isPasswordForAccount(String password, String accountName) {
		if (map.get(accountName).equals(password)) {
			return true;
		}
		return false;
	}
	
	/* Given an account name and a password, creates a new account */
	public void createNewAccount(String name, String password) {
		map.put(name, password);
	}
	
}
