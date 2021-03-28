package edu.ucam.pojos;

public class Purchase {
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_PURCHASE = "ATR_PURCHASE", ATR_PURCHASE_ID = "ATR_PURCHASE_ID", ATR_PURCHASE_AMOUNT = "ATR_PURCHASE_AMOUNT",
			ATR_PURCHASE_VIDEOGAMEID = "ATR_PURCHASE_VIDEOGAMEID", ATR_PURCHASE_BILLID = "ATR_PURCHASE_BILLID";
	
	
	
	/*
	 * Attributes
	 */
	private int amount;
	private String id, videogame_id, bill_id;
	
		
	
	/*
	 * Getters & Setters
	 */
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVideogame_id() {
		return videogame_id;
	}
	public void setVideogame_id(String videogame_id) {
		this.videogame_id = videogame_id;
	}
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}	
	
	
	
	/*
	 * Constructors
	 */
	public Purchase() {
		
	}
	
}
