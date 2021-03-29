package edu.ucam.daos;

import java.util.ArrayList;

import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Bill;

public class BillDAO implements IDao<Bill>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Bill pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill read(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Bill pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Bill> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
