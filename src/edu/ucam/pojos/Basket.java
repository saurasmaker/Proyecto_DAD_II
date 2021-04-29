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
	public Basket() {
		this.products = new ArrayList<Item>();
	}
	
	public Basket(String userId) {
		this.userId = userId;
		this.products = new ArrayList<Item>();
	}
	
	
	
	/*
	 * Methods
	 */
	public void addProduct(String videogameId, int amount) {
		int n = -1;
		if((n = isAlreadyInBasketInPosition(videogameId)) != -1) {
			if(amount != -1 && this.products.get(n).getAmount() != -1)
				this.products.get(n).setAmount(this.products.get(n).getAmount()+amount);
			else
				this.products.get(n).setAmount(amount);
		}
		else {
			this.products.add(new Item(videogameId, amount));
		}
	}
	
	public Item getProductById(String videogameId) {
		return this.products.get(isAlreadyInBasketInPosition(videogameId));
	}
	
	public Item getProductByPosition(int i) {
		return this.products.get(i);
	}
	
	public void setProductAmount(String videogameId, int amount) {
		int position = isAlreadyInBasketInPosition(videogameId);
		
		if(position != -1) {
			
			this.products.get(position).setAmount(amount);
			
			if(this.products.get(position).getAmount() == 0) {
				this.products.remove(position);
			}

		}
	}
	
	
	private int isAlreadyInBasketInPosition(String videogameId) {
		
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

