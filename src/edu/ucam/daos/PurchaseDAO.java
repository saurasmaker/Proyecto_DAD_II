package edu.ucam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Purchase;

public class PurchaseDAO implements IDao<Purchase>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Purchase purchase) {
		return executeQueryWithParameters("INSERT INTO purchases (amount, videogame_id, bill_id) VALUES (?, ?, ?)" , purchase);		
	}

	@Override
	public Purchase read(String search, SearchBy searchBy) {
		Purchase purchase = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM purchases WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					purchase = setPurchaseAttributes(rs);
				}
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return purchase;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Purchase purchase) {
		String updateQuery = "UPDATE purchases SET amount = ?, videogame_id = ?, bill_id = ? WHERE ";
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
		return executeQueryWithParameters(updateQuery, purchase);
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		String deleteQuery = "DELETE FROM purchases WHERE ";
		try {
			deleteQuery = IDao.appendSqlSearchBy(deleteQuery, searchBy, search);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(deleteQuery);	
		} catch (SQLException e)  {
			e.printStackTrace();
			return ErrorType.ERROR;
		}	
		
		return ErrorType.NO_ERROR;
	}

	@Override
	public ArrayList<Purchase> list() {
		String selectQuery = "SELECT * FROM purchases"; 		
		ResultSet rs = null;
		ArrayList<Purchase> purchasesList = new ArrayList<Purchase>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(selectQuery);					
			while(rs.next()) {
				Purchase purchae = setPurchaseAttributes(rs);	
				purchasesList.add(purchae);
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return purchasesList;
	}

	
	
	/*
	 * Tool Methods
	 */
	public ArrayList<Purchase> listByVideogameId(String videogameId) {
		
		ArrayList<Purchase> purchasesList = new ArrayList<Purchase>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM bills WHERE user_id = '" + videogameId + "'"; 

		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Purchase purchase = setPurchaseAttributes(rs);
				purchasesList.add(read(purchase.getBillId(), SearchBy.ID));
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return purchasesList;
	}
	
	public ArrayList<Purchase> listByBillId(String billId) {
		
		ArrayList<Purchase> purchasesList = new ArrayList<Purchase>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM bills WHERE user_id = '" + billId + "'"; 

		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Purchase purchase = setPurchaseAttributes(rs);
				purchasesList.add(read(purchase.getBillId(), SearchBy.ID));
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return purchasesList;
	}
	
	
	private ErrorType executeQueryWithParameters(String query, Purchase purchase) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setInt(1, purchase.getAmount());
			preparedStatement.setString(2, purchase.getVideogameId());
			preparedStatement.setString(3, purchase.getBillId());
			
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	
	private Purchase setPurchaseAttributes(ResultSet rs) {
		Purchase purchase = null;
		try {
			purchase = new Purchase();
			purchase.setId(rs.getString("id"));
			purchase.setAmount(rs.getInt("amount"));
			purchase.setVideogameId(rs.getString("videogame_id"));
			purchase.setBillId(rs.getString("bill_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return purchase;
	}
}
