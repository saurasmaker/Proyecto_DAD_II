package edu.ucam.pojos;

import javax.servlet.http.HttpServletRequest;

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
	 * Constructors
	 */
	public Purchase() {
		
	}
	
	public Purchase(HttpServletRequest request) {
		this.id = request.getParameter(ATR_PURCHASE_ID);
		this.amount = Integer.parseInt(request.getParameter(ATR_PURCHASE_AMOUNT));
		this.videogameId = request.getParameter(ATR_PURCHASE_VIDEOGAMEID);
		this.billId = request.getParameter(ATR_PURCHASE_BILLID);
	}
	
	
	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.amount + "', '" + this.videogameId + "', '" + this.billId + "'";
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
	
	
	
	
	
}
