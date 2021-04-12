package edu.ucam.daos;

import java.sql.PreparedStatement;
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
		return executeQueryWithParameters("INSERT INTO bills (user_id, purchase_date, paid) VALUES (?, ?, ?)" , bill);		
	}

	@Override
	public Bill read(String search, SearchBy searchBy) {
		Bill bill = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM bills WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					bill = setBillAttributes(rs);
				}
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return bill;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Bill bill) {
		String updateQuery = "UPDATE assessments SET value = ?, subject = ?, comment = ?, publication_date = ?, edit_date = ?, videogame_id = ?, user_id = ? WHERE ";
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
		return executeQueryWithParameters(updateQuery, bill);
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		String updateQuery = "DELETE FROM bills WHERE ";
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery);	
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
		ArrayList<Bill> billsList = new ArrayList<Bill>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Bill bill = setBillAttributes(rs);
				billsList.add(bill);
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return billsList;
	}

	
	
	/*
	 * Tool Methods
	 */
	public ArrayList<Bill> listByUserId(String userId) {
		
		ArrayList<Bill> billsList = new ArrayList<Bill>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM bills WHERE user_id = '" + userId + "'"; 

		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Bill bill = setBillAttributes(rs);
				billsList.add(read(bill.getUserId(), SearchBy.ID));
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return billsList;
	}
	
	
	private ErrorType executeQueryWithParameters(String query, Bill bill) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setString(1, bill.getUserId());
			preparedStatement.setTimestamp(2, bill.getPurchaseDate());
			preparedStatement.setBoolean(3, bill.isPaid());
			
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	
	private Bill setBillAttributes(ResultSet rs) {
		Bill bill = null;
		try {
			bill = new Bill();
			bill.setId(rs.getString("id"));
			bill.setUserId(rs.getString("user_id"));
			bill.setPurchaseDate(rs.getTimestamp("purchase_date"));
			bill.setPaid(rs.getBoolean("paid"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bill;
	}
	
}
