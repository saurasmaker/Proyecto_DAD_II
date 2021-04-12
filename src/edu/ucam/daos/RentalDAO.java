package edu.ucam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Rental;

public class RentalDAO implements IDao<Rental>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Rental rental) {
		return executeQueryWithParameters("INSERT INTO rentals (user_id, videogame_id, start_date, end_date, returned) VALUES (?, ?, ?, ?, ?)" , rental);		

	}

	@Override
	public Rental read(String search, SearchBy searchBy) {
		Rental rental = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM rentals WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					rental = setRentalAttrubutes(rs);
				}
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return rental;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Rental rental) {
		String updateQuery = "UPDATE rentals SET user_id = ?, videogame_id = ?, start_date = ?, end_date = ?, returned = ? WHERE "; 
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
		return executeQueryWithParameters(updateQuery, rental);
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		String updateQuery = "DELETE FROM rentals WHERE ";
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
	public ArrayList<Rental> list() {
		String updateQuery = "SELECT * FROM rentals"; 		
		ResultSet rs = null;
		ArrayList<Rental> usersList = new ArrayList<Rental>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Rental rental = setRentalAttrubutes(rs);
				usersList.add(rental);
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
	public ArrayList<Rental> listByUserId(String userId) {
		
		ArrayList<Rental> rentalsList = new ArrayList<Rental>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM rentals WHERE user_id = '" + userId + "'"; 
		System.out.println(selectQuery);
		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Rental rental = setRentalAttrubutes(rs);
				rentalsList.add(read(rental.getUserId(), SearchBy.ID));
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return rentalsList;
	}
	
	
	public ArrayList<Rental> listVideogameId(String videogameId) {
		
		ArrayList<Rental> rentalsList = new ArrayList<Rental>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM rentals WHERE user_id = '" + videogameId + "'"; 
		System.out.println(selectQuery);
		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Rental rental = setRentalAttrubutes(rs);
				rentalsList.add(read(rental.getUserId(), SearchBy.ID));
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return rentalsList;
	}
	
	
	private ErrorType executeQueryWithParameters(String query, Rental rental) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setString(1, rental.getUserId());
			preparedStatement.setString(2, rental.getVideogameId());
			preparedStatement.setTimestamp(3, rental.getStartDate());
			preparedStatement.setTimestamp(4, rental.getEndDate());
			preparedStatement.setBoolean(5, rental.isReturned());
			
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	
	private Rental setRentalAttrubutes(ResultSet rs) {
		Rental rental = null;
		try {
			rental = new Rental();
			rental.setId(rs.getString("id"));
			rental.setUserId(rs.getString("user_id"));
			rental.setVideogameId(rs.getString("videogame_id"));
			rental.setStartDate(rs.getTimestamp("start_date"));
			rental.setEndDate(rs.getTimestamp("end_date"));
			rental.setReturned(rs.getBoolean("returned"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rental;
	}

}
