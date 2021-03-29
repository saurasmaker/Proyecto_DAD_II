package edu.ucam.daos;

import java.util.ArrayList;

import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Purchase;

public class PurchaseDAO implements IDao<Purchase>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Purchase pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Purchase read(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Purchase pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Purchase> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
