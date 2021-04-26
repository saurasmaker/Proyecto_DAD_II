package edu.ucam.pojos;

import java.sql.Date;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;

import edu.ucam.interfaces.IMyPojo;

public class Bill implements IMyPojo{
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_BILL = "ATR_BILL", ATR_BILL_ID = "ATR_BILL_ID", ATR_BILL_USERID = "ATR_BILL_USERID",
			ATR_BILL_BILLINGDATE = "ATR_BILL_BILLINGDATE", ATR_BILL_BILLINGTIME = "ATR_BILL_BILLINGTIME",
			ATR_BILL_PAIDDATE = "ATR_BILL_PAIDDATE", ATR_BILL_PAIDTIME = "ATR_BILL_PAIDTIME",
			ATR_BILL_PAID = "ATR_BILL_PAID", ATR_BILLS_LIST = "ATR_BILLS_LIST";
	
	
	
	/*
	 * Attributes
	 */
	private String id, userId;
	private Date billingDate, paidDate;
	private Time billingTime, paidTime;
	private boolean paid;
	
	
	/*
	 * Constructors
	 */
	public Bill() {
		
	}
	
	public Bill(HttpServletRequest request) {
		this.id = request.getParameter(ATR_BILL_ID);
		this.userId = request.getParameter(ATR_BILL_USERID);
		this.billingDate = Date.valueOf(request.getParameter(ATR_BILL_BILLINGDATE));
		this.billingTime = Time.valueOf(request.getParameter(ATR_BILL_BILLINGTIME));
		this.paidDate = Date.valueOf(request.getParameter(ATR_BILL_PAIDDATE));
		this.paidTime = Time.valueOf(request.getParameter(ATR_BILL_PAIDTIME));
		this.paid = request.getParameter(ATR_BILL_PAID) != null ? true : false;
		
		if(this.paid==false) {
			this.paidDate = null;
			this.paidTime = null;
		}
	}
	
	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.userId + "', '" + this.billingDate + "', '" + this.billingTime + "', '"
				+ this.paid + "', '" + this.paidDate + "', '" + this.paidTime + "'";
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public Time getBillingTime() {
		return billingTime;
	}
	public void setBillingTime(Time billingTime) {
		this.billingTime = billingTime;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public Time getPaidTime() {
		return paidTime;
	}
	public void setPaidTime(Time paidTime) {
		this.paidTime = paidTime;
	}
	
	
	
	
}
