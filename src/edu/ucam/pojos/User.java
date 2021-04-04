package edu.ucam.pojos;

import java.sql.Date;

public class User {
	
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_USER = "ATR_USER", ATR_USER_ID = "ATR_USER_ID", ATR_USER_USERNAME = "ATR_USER_USERNAME",
			ATR_USER_EMAIL = "ATR_USER_EMAIL", ATR_USER_PASSWORD = "ATR_USER_PASSWORD",  ATR_USER_SIGNUPDATE = "ATR_USER_SIGNUPDATE",
			ATR_USER_LASTSIGNIN = "ATR_USER_LASTSIGNIN", ATR_USERS_LIST = "ATR_USERS_LIST", ATR_USER_LOGGED = "ATR_USER_LOGGED";
		
	
	
	/*
	 * Attributes
	 */
	private String id, username, email, password;
	private Date lastSignIn, signUpDate;

	
	
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
	
	
	public Date getLastSignIn() {
		return lastSignIn;
	}



	public void setLastSignIn(Date lastSignIn) {
		this.lastSignIn = lastSignIn;
	}



	public Date getSignUpDate() {
		return signUpDate;
	}



	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
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
