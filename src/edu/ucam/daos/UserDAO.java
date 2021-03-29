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
				System.out.println(" --- This user exists already ---");	
				return ErrorType.USER_EXISTS;
			}
					
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO users (username, email, password) " + 
						"VALUES ('" + user.getUsername() + "', '" + user.getEmail() + "', '" + user.getPassword() + "')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println(" --- Error when signing up user ---");
			return ErrorType.JDBC_ERROR_CONNECTION;
		}
	}


	public static  User read(String search, SearchUserBy searchBy) {
		User user = null;
		ResultSet rs = null;
		
		try {
			DatabaseController.connect();
			switch(searchBy) {
			
			case ID:
				rs = DatabaseController.DATABASE_STATEMENT.executeQuery("SELECT * FROM users WHERE id = '" + search + "'");			
				break;
				
			case USERNAME:
				rs = DatabaseController.DATABASE_STATEMENT.executeQuery("SELECT * FROM users WHERE username = '" + search + "'");			
				break;
				
			case EMAIL:
				rs = DatabaseController.DATABASE_STATEMENT.executeQuery("SELECT * FROM users WHERE email = '" + search + "'");			
				break;
				
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)  {
			e.printStackTrace();
		}	

		
		try {
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					user = new User();
					user.setId(rs.getString("id"));
					user.setUsername(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			
		return user;
	}



	public static ErrorType update(String search, SearchUserBy searchBy, User user) {
		
		System.out.println("NOT IMPLEMENTED");
		
		return null;
	}



	public static ErrorType delete(String search, SearchUserBy searchBy) {
		
		System.out.println("NOT IMPLEMENTED");
		
		return null;
	}
	
	
	
}
