package edu.ucam.pojos;

import java.sql.Timestamp;

import edu.ucam.interfaces.IMyPojo;

public class User implements IMyPojo{
	
	
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
	private Timestamp lastSignIn, signUpDate;

	
	
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
	
	
	public Timestamp getLastSignIn() {
		return lastSignIn;
	}



	public void setLastSignIn(Timestamp lastSignIn) {
		this.lastSignIn = lastSignIn;
	}



	public Timestamp getSignUpDate() {
		return signUpDate;
	}



	public void setSignUpDate(Timestamp signUpDate) {
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



	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.username + "', '" + this.email + "', '" + this.password + "', '"  + 
				this.signUpDate + "', '" + this.lastSignIn + "'";
	}


	
}
