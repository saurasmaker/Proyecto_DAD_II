package pojos;

public class VideogamesCategories {
	
	/*
	 * Static Attributes
	 */
	public String ATR_VIDEOGAMESCATEGORIES = "ATR_VIDEOGAMES_CATEGORIES", ATR_VIDEOGAMESCATEGORIES_ID = "ATR_VIDEOGAMESCATEGORIES_ID",
			ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID = "ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID", ATR_VIDEOGAMESCATEGORIES_CATEGORYID = "ATR_VIDEOGAMESCATEGORIES_CATEGORYID";
	
	
	/*
	 * Attributes
	 */
	private String videogameId, categoryId;
	
	
	/*
	 * Getters & Setters
	 */
	public String getVieogameId() {
		return videogameId;
	}


	public void setVieogameId(String vieogameId) {
		this.videogameId = vieogameId;
	}


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	