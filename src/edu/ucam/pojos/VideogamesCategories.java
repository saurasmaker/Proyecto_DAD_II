package edu.ucam.pojos;

public class VideogamesCategories {
	
	/*
	 * Static Attributes
	 */
	public static String ATR_VIDEOGAMESCATEGORIES = "ATR_VIDEOGAMES_CATEGORIES", ATR_VIDEOGAMESCATEGORIES_ID = "ATR_VIDEOGAMESCATEGORIES_ID",
			ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID = "ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID", ATR_VIDEOGAMESCATEGORIES_CATEGORYID = "ATR_VIDEOGAMESCATEGORIES_CATEGORYID";
	
	
	/*
	 * Attributes
	 */
	private String id, videogameId, categoryId;
	
	
	/*
	 * Getters & Setters
	 */
	public String getVideogameId() {
		return videogameId;
	}


	public void setVideogameId(String videogameId) {
		this.videogameId = videogameId;
	}


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	
	
	/*
	 * Constructors
	 */
	public VideogamesCategories() {
		
	}
	
	public VideogamesCategories(String videogameId, String categoryId) {
		this.videogameId = videogameId;
		this.categoryId = categoryId;
	}


}
	