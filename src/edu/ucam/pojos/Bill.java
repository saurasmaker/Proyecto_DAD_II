package edu.ucam.pojos;

import java.sql.Timestamp;

import edu.ucam.interfaces.IMyPojo;

public class Bill implements IMyPojo{
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_BILL = "ATR_BILL", ATR_BILL_ID = "ATR_BILL_ID", ATR_BILL_USERID = "ATR_BILL_USERID",
			ATR_BILL_PURCHASEDATE = "ATR_BILL_PURCHASEDATE", ATR_BILL_PAID = "ATR_BILL_PAID", ATR_BILLS_LIST = "ATR_BILLS_LIST";
	
	
	
	/*
	 * Attributes
	 */
	private String id, userId;
	private Timestamp purchaseDate;
	private boolean paid;
	
	
	
	
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
	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	
	
	/*
	 * Constructors
	 */
	public Bill() {
		
	}
	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.userId + "', '" + this.purchaseDate + "', '" + this.paid + "'";
	}
}
