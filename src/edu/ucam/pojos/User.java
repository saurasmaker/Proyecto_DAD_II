package edu.ucam.pojos;

public class User {
	
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_USER = "ATR_USER", ATR_USER_ID = "ATR_USER_ID", ATR_USER_USERNAME = "ATR_USER_USERNAME",
			ATR_USER_EMAIL = "ATR_USER_EMAIL", ATR_USER_PASSWORD = "ATR_USER_PASSWORD",  ATR_USERS_LIST = "ATR_USERS_LIST";
	
	
	
	
	/*
	 * Attributes
	 */
	private String id, username, email, password;


	
	
	/*
	 * Getters & Setters
	 */
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	/*
	 * Constructors
	 */
	public User() {
		
	}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}


	
}