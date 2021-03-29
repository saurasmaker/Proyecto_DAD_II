package edu.ucam.daos;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ucam.database.DatabaseController;
import edu.ucam.pojos.User;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchUserBy;

public class UserDAO{

	public static ErrorType create(User user) {
		try {
			DatabaseController.connect();
			if(read(user.getUsername(), SearchUserBy.USERNAME)!=null) {
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


	public static User read(String search, SearchUserBy searchBy) {
		User user = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM users WHERE "; 
		try {
			DatabaseController.connect();
			appendSearchBy(updateQuery, searchBy);
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



	public static ErrorType update(String search, SearchUserBy searchBy, User user) {
		
		String updateQuery = "UPDATE users SET " + 
				"username = '" + user.getUsername()  + "', " + 
				"email = '" +  user.getEmail() + "', " + 
				"password = '" + user.getPassword() + "' " + 
				"WHERE ";
		
		try {
			DatabaseController.connect();
			appendSearchBy(updateQuery, searchBy);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery + search + "'");	
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)  {
			e.printStackTrace();
		}	
		
		return null;
	}



	public static ErrorType delete(String search, SearchUserBy searchBy) {
		
		String updateQuery = "DELETE FROM users WHERE ";
		try {
			DatabaseController.connect();
			appendSearchBy(updateQuery, searchBy);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery + search + "'");	
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)  {
			e.printStackTrace();
		}	
		
		return null;
	}
	
	
	
	
	/*
	 * Private methods
	 */
	private static String appendSearchBy(String s, SearchUserBy searchBy) {
		
		switch(searchBy) {
		
		case ID:
			s += "id = '";
			break;
			
		case USERNAME:
			s += "username = '";	
			break;
			
		case EMAIL:
			s += "email = '";	
			break;
			
		}
		
		return s;
	}

}
