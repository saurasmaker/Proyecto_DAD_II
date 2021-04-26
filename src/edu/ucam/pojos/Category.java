package edu.ucam.pojos;

import javax.servlet.http.HttpServletRequest;

import edu.ucam.interfaces.IMyPojo;

public class Category implements IMyPojo{
	
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_CATEGORY = "ATR_CATEGORY", ATR_CATEGORY_ID = "ATR_CATEGORY_ID", ATR_CATEGORY_NAME = "ATR_CATEGORY_NAME",
			ATR_CATEGORY_DESCRIPTION = "ATR_CATEGORY_DESCRIPTION", ATR_CATEGORIES_LIST = "ATR_CATEGORIES_LIST";
	
	
	
	/*
	 * Attributes
	 */
	private  String id, name, description;

	/*
	 * Constructors
	 */
	public Category() {
		
	}
	
	public Category(HttpServletRequest request) {
		this.id = request.getParameter(ATR_CATEGORY_ID);
		this.name = request.getParameter(ATR_CATEGORY_NAME);
		this.description = request.getParameter(ATR_CATEGORY_DESCRIPTION);
	}


	
	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction() {
		return "'" + this.id + "', '" + this.name + "', '" + this.description + "'";
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
	
	
	
	
	
}
