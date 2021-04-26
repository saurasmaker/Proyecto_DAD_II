package edu.ucam.pojos;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import edu.ucam.interfaces.IMyPojo;

public class Videogame implements IMyPojo{
	/*
	 * Static Attributes
	 */
	public static final String ATR_VIDEOGAME = "ATR_VIDEOGAME", ATR_VIDEOGAME_ID = "ATR_VIDEOGAME_ID", ATR_VIDEOGAME_NAME = "ATR_VIDEOGAME_NAME",
			ATR_VIDEOGAME_DESCRIPTION = "ATR_VIDEOGAME_DESCRIPTION", ATR_VIDEOGAME_RELEASEDATE = "ATR_VIDEOGAME_RELEASEDATE",
			ATR_VIDEOGAME_PURCHASEPRICE = "ATR_VIDEOGAME_PURCHASEPRICE", ATR_VIDEOGAME_RENTALPRICE = "ATR_VIDEOGAME_RENTALPRICE",
			ATR_VIDEOGAME_STOCK = "ATR_VIDEOGAME_STOCK", ATR_VIDEOGAME_IMAGE = "ATR_VIDEOGAME_IMAGE", ATR_VIDEOGAME_CATEGORY = "ATR_VIDEOGAME_CATEGORY",
			ATR_VIDEOGAMES_LIST = "ATR_VIDEOGAMES_LIST";
			
	
	/*
	 * Attributes
	 */
	private String id, name, description;
	private int stock;
	private Date releaseDate;
	private float purchasePrice, rentalPrice;
	
	
	/*
	 * Constructors
	 */
	public Videogame() {
		
	}
	
	public Videogame(HttpServletRequest request) {
		this.id = request.getParameter(ATR_VIDEOGAME_ID);
		this.name = request.getParameter(ATR_VIDEOGAME_NAME);
		this.description = request.getParameter(ATR_VIDEOGAME_DESCRIPTION);
		this.releaseDate = Date.valueOf(request.getParameter(ATR_VIDEOGAME_RELEASEDATE));
		this.stock = Integer.parseInt(request.getParameter(ATR_VIDEOGAME_STOCK));
		this.purchasePrice = Float.valueOf(request.getParameter(ATR_VIDEOGAME_PURCHASEPRICE));
		this.rentalPrice = Float.valueOf(request.getParameter(ATR_VIDEOGAME_RENTALPRICE));
	}

	
	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.name + "', '" + this.description + "', '" + this.releaseDate + "', '"  + 
				this.stock + "', '" + this.purchasePrice + "', '"  + this.rentalPrice + "'";
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

}
