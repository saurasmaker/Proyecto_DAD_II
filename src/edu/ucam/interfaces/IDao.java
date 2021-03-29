package edu.ucam.interfaces;

import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;

public interface IDao <Pojo> {
	
	/*
	 * 
	 */
	
	public ErrorType create(Pojo pojo);
	public Pojo read(String search, SearchBy searchBy);
	public ErrorType update(String search, SearchBy searchBy, Pojo pojo);
	public ErrorType delete(String search, SearchBy searchBy);
}
