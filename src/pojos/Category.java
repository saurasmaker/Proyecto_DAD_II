package pojos;

public class Category {
	
	
	/*
	 * Static Attributes
	 */
	public static String ATR_CATEGORY = "ATR_CATEGORY", ATR_CATEGORY_ID = "ATR_CATEGORY_ID", ATR_CATEGORY_NAME = "ATR_CATEGORY_NAME",
			ATR_CATEGORY_DESCRIPTION = "ATR_CATEGORY_DESCRIPTION";
	
	
	
	/*
	 * Attributes
	 */
	private  String id, name, description;

	
	
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
	
	
	
	
	/*
	 * Constructors
	 */
	public Category() {
		
	}
	
	public Category(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
}
