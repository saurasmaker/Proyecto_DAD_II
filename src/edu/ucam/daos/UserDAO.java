package edu.ucam.daos;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ucam.database.DatabaseController;
import edu.ucam.pojos.User;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;

public class UserDAO implements IDao<User>{
	
	
	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(User user) {
		try {
			DatabaseController.connect();
			if(read(user.getUsername(), SearchBy.USERNAME)!=null) {
				return ErrorType.USER_EXISTS;
			}
					
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO users (username, email, password) " + 
						"VALUES ('" + user.getUsername() + "', '" + user.getEmail() + "', '" + user.getPassword() + "')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return ErrorType.JDBC_ERROR_CONNECTION;
		}
	}

	
	@Override
	public User read(String search, SearchBy searchBy) {
		User user = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM users WHERE "; 
		try {
			DatabaseController.connect();
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery + search + "'");	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					user = new User();
					user.setId(rs.getString("id"));
					user.setUsername(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)  {
			e.printStackTrace();
		}	
			
		return user;
	}


	@Override
	public ErrorType update(String search, SearchBy searchBy, User user) {
		
		String updateQuery = "UPDATE users SET " + 
				"username = '" + user.getUsername()  + "', " + 
				"email = '" +  user.getEmail() + "', " + 
				"password = '" + user.getPassword() + "' " + 
				"WHERE ";
		
		try {
			DatabaseController.connect();
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery + search + "'");	
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)  {
			e.printStackTrace();
		}	
		
		return null;
	}


	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		
		String updateQuery = "DELETE FROM users WHERE ";
		try {
			DatabaseController.connect();
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery + search + "'");	
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)  {
			e.printStackTrace();
		}	
		
		return null;
	}
	

}
