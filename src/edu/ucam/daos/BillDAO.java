package edu.ucam.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Bill;

public class BillDAO implements IDao<Bill>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Bill bill) {
		try {				
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO bills (user_id, purchase_date, paid) " + 
						"VALUES ('" + bill.getUserId() + "', '" + bill.getPurchaseDate() + "', '" + bill.isPaid() + "')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return ErrorType.JDBC_ERROR_CONNECTION;
		}
	}

	@Override
	public Bill read(String search, SearchBy searchBy) {
		Bill bill = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM bills WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery + search + "'");	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					bill = new Bill();
					bill.setId(rs.getString("id"));
					bill.setUserId(rs.getString("user_id"));
					bill.setPurchaseDate(rs.getDate("purchase_date"));
					bill.setPaid(rs.getBoolean("paid"));
				}
			}
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return bill;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Bill bill) {

		String updateQuery = "UPDATE bills SET " + 
				"user_id = '" + bill.getUserId()  + "', " + 
				"purchase_date = '" +  bill.getPurchaseDate() + "', " + 
				"paid = '" + bill.isPaid() + "' " + 
				"WHERE ";
		
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery + search + "'");	
		} catch (SQLException e)  {
			e.printStackTrace();
			return ErrorType.ERROR;
		}	
		
		return ErrorType.NO_ERROR;
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		String updateQuery = "DELETE FROM bills WHERE ";
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery + search + "'");	
		} catch (SQLException e)  {
			e.printStackTrace();
			return ErrorType.ERROR;
		}	
		
		return ErrorType.NO_ERROR;
	}

	@Override
	public ArrayList<Bill> list() {
		String updateQuery = "SELECT * FROM bills"; 		
		ResultSet rs = null;
		ArrayList<Bill> usersList = new ArrayList<Bill>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getString("id"));
				bill.setUserId(rs.getString("user_id"));
				bill.setPurchaseDate(rs.getDate("purchase_date"));
				bill.setPaid(rs.getBoolean("paid"));
				
				usersList.add(bill);
			}			
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return usersList;
	}

}
