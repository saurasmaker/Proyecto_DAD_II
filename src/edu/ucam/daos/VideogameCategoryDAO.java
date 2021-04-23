package edu.ucam.daos;

import java.sql.PreparedStatement;
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
		return executeStatementWithParameters("INSERT INTO videogames_categories (videogame_id, category_id) VALUES (?, ?)", videogamesCategories);	

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
					videogamesCategories = setVideogameCategoryAttributes(rs);
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
		String updateQuery = "UPDATE videogames_categories SET videogames_id = ?, category_id = ? WHERE "; 
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
		return executeStatementWithParameters(updateQuery, videogamesCategories);
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
		return listTool(updateQuery);
	}

	
	public ArrayList<VideogameCategory> listByCategoryId(String categoryId) {
		String updateQuery = "SELECT * FROM videogames_categories WHERE category_id = '" + categoryId + "'"; 		
		return listTool(updateQuery);
	}
	
	public ArrayList<VideogameCategory> listByVideogameId(String videogameId) {
		String updateQuery = "SELECT * FROM videogames_categories WHERE videogame_id = '" + videogameId + "'"; 		
		return listTool(updateQuery);
	}
	
	
	/*
	 * Tool Methods
	 */
	private ErrorType executeStatementWithParameters(String query, VideogameCategory videogameCategory) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setString(1, videogameCategory.getVideogameId());
			preparedStatement.setString(2, videogameCategory.getCategoryId());

			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	private VideogameCategory setVideogameCategoryAttributes(ResultSet rs) {
		VideogameCategory videogameCategory = null;
		try {
			videogameCategory = new VideogameCategory();
			videogameCategory.setId(rs.getString("id"));
			videogameCategory.setVideogameId(rs.getString("videogame_id"));
			videogameCategory.setCategoryId(rs.getString("category_id"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return videogameCategory;
	}
	
	private ArrayList<VideogameCategory> listTool(String updateQuery){
		
		ResultSet rs = null;
		ArrayList<VideogameCategory> videogamesCategoriesList = new ArrayList<VideogameCategory>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				VideogameCategory videogamesCategories = setVideogameCategoryAttributes(rs);
				videogamesCategoriesList.add(videogamesCategories);
			}		
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return videogamesCategoriesList;	
	}
}
