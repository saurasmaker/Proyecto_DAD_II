package edu.ucam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.pojos.User;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;

public class UserDAO implements IDao<User>{
	
	public static int USER_COUNT = 0;
	
	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(User user) {
		return prepareStatement("INSERT INTO users (username, email, password, sign_up_date, last_sign_in) VALUES (?, ?, ?, ?, ?)", user);	
	}

	
	@Override
	public User read(String search, SearchBy searchBy) {
		User user = null;
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM users WHERE "; 
		try {
			selectQuery = IDao.appendSqlSearchBy(selectQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(selectQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					user = setUserAttributes(rs);
				}
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return user;
	}


	@Override
	public ErrorType update(String search, SearchBy searchBy, User user) {
		String updateQuery = "UPDATE users SET username = ?, email = ?, password = ?, sign_up_date = ?, last_sign_in = ? WHERE ";
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);			
		prepareStatement(updateQuery, user);
		return ErrorType.NO_ERROR;
	}


	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		
		String deleteQuery = "DELETE FROM users WHERE ";
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
	public ArrayList<User> list() {
		String selectQuery = "SELECT * FROM users"; 		
		ResultSet rs = null;
		ArrayList<User> usersList = new ArrayList<User>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(selectQuery);					
			while(rs.next()) {
				User user = setUserAttributes(rs);
				usersList.add(user);
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return usersList;
	}
	

	
	/*
	 * Tool Methods
	 */
	private ErrorType prepareStatement(String query, User user) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setTimestamp(4, user.getSignUpDate());
			preparedStatement.setTimestamp(5, user.getLastSignIn());
			
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	private User setUserAttributes(ResultSet rs) {
		User user = null;
		try {
			user = new User();
			user.setId(rs.getString("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setSignUpDate(rs.getTimestamp("sign_up_date"));
			user.setLastSignIn(rs.getTimestamp("last_sign_in"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
