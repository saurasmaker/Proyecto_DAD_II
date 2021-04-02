package edu.ucam.pojos;

import java.sql.Date;

public class Rental {
	/*
	 * Static Attributes
	 */
	public static final String ATR_RENTAL = "ATR_RENTAL", ATR_RENTAL_ID = "ATR_RENTAL_ID",
			ATR_RENTAL_USERID = "ATR_RENTAL_USER_ID", ATR_RENTAL_VIDEOGAMEID = "ATR_RENTAL_VIDEOGAMEID",  ATR_RENTAL_STARTDATE = "ATR_RENTAL_STARTDATE",
			ATR_RENTAL_ENDDATE = "ATR_RENTAL_ENDDATE", ATR_RENTAL_RETURNED = "ATR_RENTAL_RETURNED", ATR_RENTALS_LIST = "ATR_RENTALS_LIST";
	
	
	/*
	 * Attributes
	 */
	private String id, userId, videogameId;
	private Date startDate, endDate;
	private boolean returned;
	
	
	
	/*
	 * Getters & Setters
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVideogameId() {
		return videogameId;
	}
	public void setVideogameId(String videogameId) {
		this.videogameId = videogameId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isReturned() {
		return returned;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	
	
	
	/*
	 * Constructors
	 */
	public Rental() {
		
	}
	
	
}
