package edu.ucam.daos;

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
		try {	
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO rentals (user_id, videogame_id, start_date, end_date, returned) " + 
						"VALUES ('" + rental.getUserId() + "', '" + rental.getVideogameId() + "', '" + rental.getStartDate() + "', '" + rental.getEndDate()
						+ "', '" + rental.isReturned() + "')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return ErrorType.JDBC_ERROR_CONNECTION;
		}
	}

	@Override
	public Rental read(String search, SearchBy searchBy) {
		Rental rental = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM rentals WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery + search + "'");	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					rental = new Rental();
					rental.setId(rs.getString("id"));
					rental.setUserId(rs.getString("user_id"));
					rental.setVideogameId(rs.getString("videogame_id"));
					rental.setStartDate(rs.getDate("start_date"));
					rental.setEndDate(rs.getDate("end_date"));
					rental.setReturned(rs.getBoolean("returned"));
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
		String updateQuery = "UPDATE rentals SET " + 
				"user_id = '" + rental.getUserId()  + "', " + 
				"videogame_id = '" +  rental.getVideogameId() + "', " + 
				"start_date = '" +  rental.getStartDate() + "', " + 
				"end_date = '" +  rental.getEndDate() + "', " + 
				"returned = '" + rental.isReturned() + "' " + 
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
		String updateQuery = "DELETE FROM rentals WHERE ";
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
	public ArrayList<Rental> list() {
		String updateQuery = "SELECT * FROM rentals"; 		
		ResultSet rs = null;
		ArrayList<Rental> usersList = new ArrayList<Rental>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Rental rental = new Rental();
				rental.setId(rs.getString("id"));
				rental.setUserId(rs.getString("user_id"));
				rental.setVideogameId(rs.getString("videogame_id"));
				rental.setStartDate(rs.getDate("start_date"));
				rental.setEndDate(rs.getDate("end_date"));
				rental.setReturned(rs.getBoolean("returned"));
				
				usersList.add(rental);
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return usersList;
	}

}
