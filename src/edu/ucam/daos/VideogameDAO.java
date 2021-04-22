package edu.ucam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Category;
import edu.ucam.pojos.Videogame;

public class VideogameDAO implements IDao<Videogame>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Videogame videogame) {
		return executeStatementWithParameters("INSERT INTO videogames (name, description, release_date, stock, purchase_price, rental_price) VALUES (?, ?, ?, ?, ?, ?)", videogame);
	}

	@Override
	public Videogame read(String search, SearchBy searchBy) {
		
		Videogame videogame = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM videogames WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					videogame = setUserAttributes(rs);
				}
			}
			rs.close();
		} catch (Exception e)  {
			e.printStackTrace();
		}	
			
		return videogame;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Videogame videogame) {
		String updateQuery = "UPDATE videogames SET name = ?, description = ?, release_date = ?, stock = ?, purchase_price = ?, rental_price = ? WHERE ";
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
		return executeStatementWithParameters(updateQuery, videogame);	
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		String updateQuery = "DELETE FROM videogames WHERE ";
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery);	
		} catch (Exception e)  {
			e.printStackTrace();
			return ErrorType.ERROR;
		}	
		
		return ErrorType.NO_ERROR;
	}

	@Override
	public ArrayList<Videogame> list() {
		
		String updateQuery = "SELECT * FROM videogames"; 		
		ResultSet rs = null;
		ArrayList<Videogame> videogamesList = new ArrayList<Videogame>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Videogame videogame = setUserAttributes(rs);
				videogamesList.add(videogame);
			}	
			rs.close();
		} catch (Exception e)  {
			e.printStackTrace();
		}	
		
		return videogamesList;
	}
	
	
	public ArrayList<Videogame> listByCategoryId(String categoryId) {
		
		String updateQuery = "SELECT * FROM videogames v INNER JOIN videogames_categories vc ON v.id = vc.videogame_id WHERE vc.category_id = '" + categoryId + "'"; 		
		ResultSet rs = null;
		ArrayList<Videogame> videogamesList = new ArrayList<Videogame>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Videogame videogame = setUserAttributes(rs);
				videogamesList.add(videogame);
			}	
			rs.close();
		} catch (Exception e)  {
			e.printStackTrace();
		}	
		
		return videogamesList;
		
	}
	
	/*
	 * Tool Methods
	 */
	private ErrorType executeStatementWithParameters(String query, Videogame videogame) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setString(1, videogame.getName());
			preparedStatement.setString(2, videogame.getDescription());
			preparedStatement.setDate(3, videogame.getReleaseDate());
			preparedStatement.setInt(4, videogame.getStock());
			preparedStatement.setFloat(5, videogame.getPurchasePrice());
			preparedStatement.setFloat(6, videogame.getRentalPrice());

			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	private Videogame setUserAttributes(ResultSet rs) {
		Videogame videogame = null;
		try {
			videogame = new Videogame();
			videogame.setId(rs.getString("id"));
			videogame.setName(rs.getString("name"));
			videogame.setDescription(rs.getString("description"));
			videogame.setReleaseDate(rs.getDate("release_date"));
			videogame.setStock(rs.getInt("stock"));
			videogame.setPurchasePrice(rs.getFloat("purchase_price"));
			videogame.setRentalPrice(rs.getFloat("rental_price"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return videogame;
	}

}
