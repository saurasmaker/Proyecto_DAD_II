package edu.ucam.pojos;

import java.sql.Blob;

import edu.ucam.interfaces.IMyPojo;

public class VideogameImage implements IMyPojo{

	/*
	 * Static Attributes
	 */
	public static final String ATR_VIDEOGAMEIMAGE_ID = "ATR_VIDEOGAMEIMAGE_ID", ATR_VIDEOGAMEIMAGE_NAME = "ATR_VIDEOGAMEIMAGE_NAME",
			ATR_VIDEOGAMEIMAGE_IMAGE = "ATR_VIDEOGAMEIMAGE_IMAGE", ATR_VIDEOGAMEIMAGE_VIDEOGAMEID = "ATR_VIDEOGAMEIMAGE_VIDEOGAMEID",
			ATR_VIDEOGAMES_LIST = "ATR_VIDEOGAMES_LIST";
	
	/*
	 * Attributes
	 */
	private String id, name, videogameId;
	private Blob image;
	
	
	
	/*
	 * Constructors
	 */
	public VideogameImage() {
		
	}

	

	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction() {
		// TODO Auto-generated method stub
		return null;
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


	public String getVideogameId() {
		return videogameId;
	}


	public void setVideogameId(String videogameId) {
		this.videogameId = videogameId;
	}


	public Blob getImage() {
		return image;
	}


	public void setImage(Blob image) {
		this.image = image;
	}
	
	
	
	
}

