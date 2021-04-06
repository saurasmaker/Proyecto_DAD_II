package edu.ucam.pojos;

import java.sql.Date;

import edu.ucam.interfaces.IMyPojo;

public class Videogame implements IMyPojo{
	/*
	 * Static Attributes
	 */
	public static final String ATR_VIDEOGAME = "ATR_VIDEOGAME", ATR_VIDEOGAME_ID = "ATR_VIDEOGAME_ID", ATR_VIDEOGAME_NAME = "ATR_VIDEOGAME_NAME",
			ATR_VIDEOGAME_DESCRIPTION = "ATR_VIDEOGAME_DESCRIPTION", ATR_VIDEOGAME_RELEASEDATE = "ATR_VIDEOGAME_RELEASEDATE",
			ATR_VIDEOGAME_PURCHASEPRICE = "ATR_VIDEOGAME_PURCHASEPRICE", ATR_VIDEOGAME_RENTALPRICE = "ATR_VIDEOGAME_RENTALPRICE",
			ATR_VIDEOGAME_STOCK = "ATR_VIDEOGAME_STOCK", ATR_VIDEOGAMES_LIST = "ATR_VIDEOGAMES_LIST";
			
	
	/*
	 * Attributes
	 */
	private String id, name, description;
	private int stock;
	private Date releaseDate;
	private float purchasePrice, rentalPrice;
	
	
	/*
	 * Getters & Setters
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	
	public float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public float getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(float rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	
	
	/*
	 * Constructors
	 */
	public Videogame() {
		this.releaseDate = new Date(System.currentTimeMillis());
	}
	
	public Videogame(String name, String description, Date releaseDate, int stock) {
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		this.stock = stock;
	}
	
	public Videogame(String id, String name, String description, Date releaseDate, int stock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		this.stock = stock;
	}

	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.name + "', '" + this.description + "', '" + this.releaseDate + "', '"  + 
				this.stock + "'";
	}
	
}
