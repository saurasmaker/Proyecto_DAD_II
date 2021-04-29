package edu.ucam.pojos;

public class Item{
	
	private String videogameId;
	private int amount; //if amount == -1 it is a rent
	
	public Item(String videogameId, int amount) {
		this.setVideogameId(videogameId);
		this.setAmount(amount);
	}

	public String getVideogameId() {
		return videogameId;
	}

	public void setVideogameId(String videogameId) {
		this.videogameId = videogameId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
