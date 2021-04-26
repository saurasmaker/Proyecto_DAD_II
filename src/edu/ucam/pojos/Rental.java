package edu.ucam.pojos;

import java.sql.Date;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;

import edu.ucam.interfaces.IMyPojo;

public class Rental implements IMyPojo{
	/*
	 * Static Attributes
	 */
	public static final String ATR_RENTAL = "ATR_RENTAL", ATR_RENTAL_ID = "ATR_RENTAL_ID",
			ATR_RENTAL_BILLID = "ATR_RENTAL_BILLID", ATR_RENTAL_VIDEOGAMEID = "ATR_RENTAL_VIDEOGAMEID",
			ATR_RENTAL_STARTDATE = "ATR_RENTAL_STARTDATE", ATR_RENTAL_STARTTIME = "ATR_RENTAL_STARTTIME",
			ATR_RENTAL_ENDDATE = "ATR_RENTAL_ENDDATE", ATR_RENTAL_ENDTIME = "ATR_RENTAL_ENDTIME",
			ATR_RENTAL_RETURNED = "ATR_RENTAL_RETURNED", ATR_RENTALS_LIST = "ATR_RENTALS_LIST";
	
	
	/*
	 * Attributes
	 */
	private String id, videogameId, billId;
	private Date startDate, endDate;
	private Time startTime, endTime;
	private boolean returned;
	
	
	/*
	 * Constructors
	 */
	public Rental() {
		
	}
	
	public Rental(HttpServletRequest request) {
		this.id = request.getParameter(ATR_RENTAL_ID);
		this.startDate = Date.valueOf(request.getParameter(ATR_RENTAL_STARTDATE));
		this.startTime = Time.valueOf(request.getParameter(ATR_RENTAL_STARTTIME));
		this.endDate = Date.valueOf(request.getParameter(ATR_RENTAL_ENDDATE));
		this.endTime = Time.valueOf(request.getParameter(ATR_RENTAL_ENDTIME));
		this.returned = request.getParameter(ATR_RENTAL_RETURNED) != null ? true : false;
		this.videogameId = request.getParameter(ATR_RENTAL_VIDEOGAMEID);
		this.billId = request.getParameter(ATR_RENTAL_BILLID);
	}
	
	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '"  + this.startDate + "', '" + this.startTime + "', '" + this.endDate 
			+ "', '" + this.endTime + "', '" + this.returned + "', '" + this.videogameId + "', '" + this.billId + "'";
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
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
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

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
}
