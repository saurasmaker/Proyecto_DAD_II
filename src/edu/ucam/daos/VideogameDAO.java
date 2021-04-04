package edu.ucam.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Videogame;

public class VideogameDAO implements IDao<Videogame>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Videogame videogame) {

		try {
			if(read(videogame.getName(), SearchBy.NAME)!=null) {
				return ErrorType.VIDEOGAME_EXISTS;
			}
					
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO videogames (name, description, release_date, stock, purchase_price, rental_price) " + 
					"VALUES ('" + videogame.getName() + "', '" + videogame.getDescription()  + "', '" + videogame.getReleaseDate() + "', '" + videogame.getStock() + "', '"
					+ videogame.getPurchasePrice() + "', '" + videogame.getRentalPrice() + "')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return ErrorType.JDBC_ERROR_CONNECTION;
		}		
	}

	@Override
	public Videogame read(String search, SearchBy searchBy) {
		
		Videogame videogame = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM videogames WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery + search + "'");	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					videogame = new Videogame();
					videogame.setId(rs.getString("id"));
					videogame.setName(rs.getString("name"));
					videogame.setDescription(rs.getString("description"));
					videogame.setReleaseDate(rs.getDate("releaseDate"));
					videogame.setStock(rs.getInt("stock"));
					videogame.setPurchasePrice(rs.getFloat("purchase_price"));
					videogame.setPurchasePrice(rs.getFloat("rental_price"));
				}
			}
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return videogame;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Videogame videogame) {
		String updateQuery = "UPDATE videogames SET " +
				"name = '" + videogame.getName()  + "', " +
				"description = '" +  videogame.getDescription() + "', " +
				"release_date = '" +  videogame.getReleaseDate() + "', " +
				"stock = '" +  videogame.getStock() + "', " +
				"purchase_price = '" +  videogame.getPurchasePrice() + "', " +
				"rental_price = '" + videogame.getRentalPrice() + "' " +
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
		String updateQuery = "DELETE FROM videogames WHERE ";
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
	public ArrayList<Videogame> list() {
		
		String updateQuery = "SELECT * FROM videogames"; 		
		ResultSet rs = null;
		ArrayList<Videogame> videogamesList = new ArrayList<Videogame>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Videogame videogame = new Videogame();
				videogame.setId(rs.getString("id"));
				videogame.setName(rs.getString("name"));
				videogame.setDescription(rs.getString("description"));
				videogame.setReleaseDate(rs.getDate("release_date"));
				videogame.setStock(rs.getInt("stock"));
				videogame.setPurchasePrice(rs.getFloat("purchase_price"));
				videogame.setPurchasePrice(rs.getFloat("rental_price"));
				
				System.out.println(videogame.getId());
				
				videogamesList.add(videogame);
			}			
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return videogamesList;
	}

}
