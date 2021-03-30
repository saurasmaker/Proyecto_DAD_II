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
				return ErrorType.PRODUCT_EXISTS;
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
	public ErrorType update(String search, SearchBy searchBy, Videogame pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Videogame> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
