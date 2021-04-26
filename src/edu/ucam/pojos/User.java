package edu.ucam.pojos;

import java.sql.Date;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;

import edu.ucam.interfaces.IMyPojo;

public class User implements IMyPojo{
	
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_USER = "ATR_USER", ATR_USER_ID = "ATR_USER_ID", ATR_USER_USERNAME = "ATR_USER_USERNAME",
			ATR_USER_EMAIL = "ATR_USER_EMAIL", ATR_USER_PASSWORD = "ATR_USER_PASSWORD",  ATR_USER_SIGNUPDATE = "ATR_USER_SIGNUPDATE",
			ATR_USER_SIGNUPTIME = "ATR_USER_SIGNUPTIME",  ATR_USER_LASTSIGNINDATE = "ATR_USER_LASTSIGNINDATE",
			ATR_USER_LASTSIGNINTIME = "ATR_USER_LASTSIGNINTIME", ATR_USERS_LIST = "ATR_USERS_LIST", ATR_USER_LOGGED = "ATR_USER_LOGGED",
			ATR_USER_ISADMIN = "ATR_USER_ISADMIN";
		
	
	
	/*
	 * Attributes
	 */
	private String id, username, email, password;
	private Date lastSignInDate, signUpDate;
	private Time lastSignInTime, signUpTime;
	private Boolean isAdmin;
	
	
	/*
	 * Constructors
	 */
	public User() {
		
	}
	
	public User(HttpServletRequest request) {
		this.id = request.getParameter(ATR_USER_ID);
		this.username = request.getParameter(ATR_USER_USERNAME);
		this.email = request.getParameter(ATR_USER_EMAIL);
		this.password = request.getParameter(ATR_USER_PASSWORD);
		this.signUpDate = Date.valueOf(request.getParameter(ATR_USER_SIGNUPDATE));
		this.signUpTime = Time.valueOf(request.getParameter(ATR_USER_SIGNUPTIME));
		this.lastSignInDate = Date.valueOf(request.getParameter(ATR_USER_LASTSIGNINDATE));
		this.lastSignInTime = Time.valueOf(request.getParameter(ATR_USER_LASTSIGNINTIME));
		this.isAdmin = request.getParameter(ATR_USER_ISADMIN)!=null ? true : false;
	}
	
	
	
	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.username + "', '" + this.email + "', '" + this.password + "', '"  + 
				this.signUpDate + "', '" + this.signUpTime + "', '" + this.lastSignInDate + "', '" + this.lastSignInTime + "', '" + this.isAdmin + "'";
	}
	
	
	
	
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
	
	
	public Date getLastSignInDate() {
		return lastSignInDate;
	}



	public void setLastSignInDate(Date lastSignInDate) {
		this.lastSignInDate = lastSignInDate;
	}



	public Date getSignUpDate() {
		return signUpDate;
	}



	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}
	
	
	public Boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Time getSignUpTime() {
		return signUpTime;
	}



	public void setSignUpTime(Time signUpTime) {
		this.signUpTime = signUpTime;
	}



	public Time getLastSignInTime() {
		return lastSignInTime;
	}



	public void setLastSignInTime(Time lastSignInTime) {
		this.lastSignInTime = lastSignInTime;
	}
	
	
}
