package edu.ucam.daos;

import java.util.ArrayList;

import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Rental;

public class RentalDAO implements IDao<Rental>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Rental pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rental read(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Rental pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rental> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
