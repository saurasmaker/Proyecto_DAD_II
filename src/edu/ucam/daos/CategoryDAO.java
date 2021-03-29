package edu.ucam.daos;

import java.util.ArrayList;

import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Category;

public class CategoryDAO implements IDao<Category>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Category pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category read(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Category pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Category> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
