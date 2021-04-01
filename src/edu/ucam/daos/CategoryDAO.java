package edu.ucam.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Category;

public class CategoryDAO implements IDao<Category>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Category category) {
		try {	
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO categories (name, description) " + 
						"VALUES ('" + category.getName() + "', '" + category.getDescription() + "')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return ErrorType.JDBC_ERROR_CONNECTION;
		}
	}

	@Override
	public Category read(String search, SearchBy searchBy) {
		Category category = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM categories WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery + search + "'");	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					category = new Category();
					category.setId(rs.getString("id"));
					category.setName(rs.getString("name"));
					category.setDescription(rs.getString("description"));
				}
			}
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return category;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Category category) {
		String updateQuery = "UPDATE categories SET " + 
				"username = '" + category.getName()  + "', " + 
				"email = '" +  category.getDescription() + "', " + 
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
		String updateQuery = "DELETE FROM categories WHERE ";
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
	public ArrayList<Category> list() {
		String updateQuery = "SELECT * FROM users"; 		
		ResultSet rs = null;
		ArrayList<Category> usersList = new ArrayList<Category>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getString("id"));
				category.setName(rs.getString("name"));
				category.setDescription(rs.getString("description"));
				
				usersList.add(category);
			}			
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return usersList;
	}

}
