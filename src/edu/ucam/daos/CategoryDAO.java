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
import edu.ucam.pojos.VideogameCategory;
import edu.ucam.pojos.VideogameImage;

public class CategoryDAO implements IDao<Category>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Category category) {
		return executeQueryWithParameters("INSERT INTO categories (name, description) VALUES (?, ?)", category);
	}

	@Override
	public Category read(String search, SearchBy searchBy) {
		Category category = null;
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM categories WHERE "; 
		try {
			selectQuery = IDao.appendSqlSearchBy(selectQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(selectQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					category = setCategoryAttributes(rs);
				}
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return category;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Category category) {
		String updateQuery = "UPDATE categories SET name = ?, description = ? WHERE ";
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
		return executeQueryWithParameters(updateQuery, category);
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		String deleteQuery = "DELETE FROM categories WHERE ";
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
	public ArrayList<Category> list() {
		String selectQuery = "SELECT * FROM categories"; 		
		ResultSet rs = null;
		ArrayList<Category> categoriesList = new ArrayList<Category>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(selectQuery);					
			while(rs.next()) {
				Category category = setCategoryAttributes(rs);
				categoriesList.add(category);
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return categoriesList;
	}
	
	/*
	 * Tool Methods
	 */
	public ArrayList<Category> listByVideogameId(String videogameId) {
		
		ArrayList<Category> categoriesList = new ArrayList<Category>();
			
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM videogames_categories WHERE videogame_id = '" + videogameId + "'"; 
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				VideogameCategory videogameCategory = setVideogameCategoryAttributes(rs);
				categoriesList.add(read(videogameCategory.getCategoryId(), SearchBy.ID));
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return categoriesList;
	}
	
	private ErrorType executeQueryWithParameters(String query, Category category) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setString(1, category.getName());
			preparedStatement.setString(2, category.getDescription());
			
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	private Category setCategoryAttributes(ResultSet rs) {
		Category category = null;
		try {
			category = new Category();
			category.setId(rs.getString("id"));
			category.setName(rs.getString("name"));
			category.setDescription(rs.getString("description"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return category;
	}
	
	private VideogameCategory setVideogameCategoryAttributes(ResultSet rs) {
		VideogameCategory category = null;
		try {
			category = new VideogameCategory();
			category.setId(rs.getString("id"));
			category.setVideogameId(rs.getString("videogame_id"));
			category.setCategoryId(rs.getString("category_id"));	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return category;
	}

}
