package edu.ucam.pojos;

import edu.ucam.interfaces.IMyPojo;

public class Purchase implements IMyPojo{
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_PURCHASE = "ATR_PURCHASE", ATR_PURCHASE_ID = "ATR_PURCHASE_ID", ATR_PURCHASE_AMOUNT = "ATR_PURCHASE_AMOUNT",
			ATR_PURCHASE_VIDEOGAMEID = "ATR_PURCHASE_VIDEOGAMEID", ATR_PURCHASE_BILLID = "ATR_PURCHASE_BILLID";
	
	
	
	/*
	 * Attributes
	 */
	private int amount;
	private String id, videogameId, billId;
	
		
	
	/*
	 * Getters & Setters
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getVideogameId() {
		return videogameId;
	}
	public void setVideogameId(String videogameId) {
		this.videogameId = videogameId;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}	
	
	
	
	/*
	 * Constructors
	 */
	public Purchase() {
		
	}
	
	
	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.amount + "', '" + this.videogameId + "', '" + this.billId + "'";
	}
	
}
