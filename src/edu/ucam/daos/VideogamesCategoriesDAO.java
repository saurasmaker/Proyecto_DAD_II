package edu.ucam.daos;

import java.util.ArrayList;

import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.VideogamesCategories;

public class VideogamesCategoriesDAO implements IDao<VideogamesCategories>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(VideogamesCategories pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VideogamesCategories read(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, VideogamesCategories pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<VideogamesCategories> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
