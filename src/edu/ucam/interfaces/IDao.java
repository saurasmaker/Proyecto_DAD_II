package edu.ucam.interfaces;

import java.util.ArrayList;

import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;

public interface IDao <Pojo> {
	
	/*
	 * CRUD Methods
	 */	
	public ErrorType create(Pojo pojo);
	public Pojo read(String search, SearchBy searchBy);
	public ErrorType update(String search, SearchBy searchBy, Pojo pojo);
	public ErrorType delete(String search, SearchBy searchBy);
	public ArrayList<Pojo> list();
	
	
	/*
	 * Static methods
	 */
	public static String appendSqlSearchBy(String s, SearchBy searchBy, String search) {
		
		switch(searchBy) {
		
		case ID:
			s += "id = '";
			break;
			
		case NAME:
			s += "name = '";	
			break;	
		
		case USERNAME:
			s += "username = '";	
			break;
			
		case EMAIL:
			s += "email = '";	
			break;
			
		default:
			break;
			
		}
		
		return s  + search + "'";
	}
	
}
