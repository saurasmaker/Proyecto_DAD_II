package edu.ucam.pojos;

import java.util.ArrayList;

import edu.ucam.interfaces.IMyPojo; 


public class Basket implements IMyPojo{

	/*
	 * Static Attributes
	 */
	public static String ATR_BASKET = "ATR_BASKET", ATR_BASKET_USERID = "ATR_BASKET_USERID",
			ATR_BASKET_PRODUCTID = "ATR_BASKET_PRODUCTID", ATR_BASKET_AMOUNT = "ATR_BASKET_AMOUNT";
	
	
	
	/*
	 * Attributes 
	 */
	private String userId;
	private ArrayList<Item> products;
	
	
	
	/*
	 * Getters & Setters
	 */
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ArrayList<Item> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Item> products) {
		this.products = products;
	}
	
	
	/*
	 * Contructors
	 */
	public Basket(String userId) {
		this.userId = userId;
		this.products = new ArrayList<Item>();
	}
	
	
	
	/*
	 * Methods
	 */
	public void addProduct(String videogameId, int amount) {
		int n = -1;
		if((n = isAlreadyInBasket(videogameId)) != -1) {
			this.products.get(n).setAmount(this.products.get(n).getAmount()+amount);
		}
		else {
			this.products.add(new Item(videogameId, amount));
		}
	}
	
	public void removeProduct(String videogameId, int amount) {
		int n = isAlreadyInBasket(videogameId);
		if(n == -1) {
			this.products.remove(n);
		}
		else if(n > 0) {
			this.products.get(n).setAmount(this.products.get(n).getAmount() - amount);
			if(this.products.get(n).getAmount() <= 0) this.products.remove(n);
		}
	}
	
	private int isAlreadyInBasket(String videogameId) {
		
		for(int i = 0; i < this.products.size(); ++i) {
			if(this.products.get(i).getVideogameId().equals(videogameId))
				return i;
		}
		
		return -1;
	}
	
	
	@Override
	public String toJavaScriptFunction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}


class Item{
	
	private String videogameId;
	private int amount;
	
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
