package edu.ucam.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.VideogameCategory;

public class VideogameCategoryDAO implements IDao<VideogameCategory>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(VideogameCategory videogamesCategories) {
		try {
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO videogames_categories (videogame_id, category_id) " + 
						"VALUES ('" + videogamesCategories.getVideogameId() + "', '" + videogamesCategories.getCategoryId() + "')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return ErrorType.JDBC_ERROR_CONNECTION;
		}
	}

	@Override
	public VideogameCategory read(String search, SearchBy searchBy) {
		VideogameCategory videogamesCategories = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM videogames_categories WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					videogamesCategories = new VideogameCategory();
					videogamesCategories.setId(rs.getString("id"));
					videogamesCategories.setVideogameId(rs.getString("videogame_id"));
					videogamesCategories.setCategoryId(rs.getString("category_id"));
				}
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return videogamesCategories;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, VideogameCategory videogamesCategories) {
		String updateQuery = "UPDATE videogames_categories SET " + 
				"videogame_id = '" + videogamesCategories.getVideogameId()  + "', " + 
				"category_id = '" + videogamesCategories.getCategoryId() + "' " + 
				"WHERE ";
		
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
	public ErrorType delete(String search, SearchBy searchBy) {
		String updateQuery = "DELETE FROM videogames_categories WHERE ";
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
	public ArrayList<VideogameCategory> list() {
		String updateQuery = "SELECT * FROM videogames_categories"; 		
		ResultSet rs = null;
		ArrayList<VideogameCategory> videogamesCategoriesList = new ArrayList<VideogameCategory>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				VideogameCategory videogamesCategories = new VideogameCategory();
				videogamesCategories.setId(rs.getString("id"));
				videogamesCategories.setVideogameId(rs.getString("videogame_id"));
				videogamesCategories.setCategoryId(rs.getString("category_id"));
				
				videogamesCategoriesList.add(videogamesCategories);
			}		
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return videogamesCategoriesList;
	}

}
