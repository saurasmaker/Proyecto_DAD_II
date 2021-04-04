package edu.ucam.daos;

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
		try {
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO purchases (amount, videogame_id, bill_id) " + 
						"VALUES ('" + purchase.getAmount() + "', '" + purchase.getVideogameId() + "', '" + purchase.getBillId() + "')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return ErrorType.JDBC_ERROR_CONNECTION;
		}
	}

	@Override
	public Purchase read(String search, SearchBy searchBy) {
		Purchase purchase = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM purchases WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery + search + "'");	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					purchase = new Purchase();
					purchase.setId(rs.getString("id"));
					purchase.setAmount(rs.getInt("amount"));
					purchase.setVideogameId(rs.getString("videogame_id"));
					purchase.setBillId(rs.getString("bill_id"));
				}
			}
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return purchase;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Purchase purchase) {
		String updateQuery = "UPDATE categories SET " + 
				"amount = '" + purchase.getAmount()  + "', " + 
				"videogame_id = '" +  purchase.getVideogameId() + "', " + 
				"bill_id = '" +  purchase.getBillId() + "', " + 
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
		String deleteQuery = "DELETE FROM purchases WHERE ";
		try {
			deleteQuery = IDao.appendSqlSearchBy(deleteQuery, searchBy);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(deleteQuery + search + "'");	
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
				Purchase purchae = new Purchase();
				purchae.setId(rs.getString("id"));
				purchae.setAmount(rs.getInt("amount"));
				purchae.setVideogameId(rs.getString("videogame_id"));
				purchae.setBillId(rs.getString("bill_id"));

				
				purchasesList.add(purchae);
			}			
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return purchasesList;
	}

}
