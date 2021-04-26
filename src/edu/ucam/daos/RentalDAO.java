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
		return executeQueryWithParameters("INSERT INTO rentals (start_date, start_time, end_date, end_time, returned, videogame_id, bill_id) VALUES (?, ?, ?, ?, ?, ?, ?)" , rental);		

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
		String updateQuery = "UPDATE rentals SET start_date = ?, start_time = ?, end_date = ?, end_time = ?, returned = ?, videogame_id = ?, bill_id = ?  WHERE "; 
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
	public ArrayList<Rental> listByBillId(String billId) {
		
		ArrayList<Rental> rentalsList = new ArrayList<Rental>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM rentals WHERE bill_id = '" + billId + "'"; 
		System.out.println(selectQuery);
		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Rental rental = setRentalAttrubutes(rs);
				rentalsList.add(rental);
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return rentalsList;
	}
	
	
	public ArrayList<Rental> listByVideogameId(String videogameId) {
		
		ArrayList<Rental> rentalsList = new ArrayList<Rental>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM rentals WHERE videogame_id = '" + videogameId + "'"; 
		System.out.println(selectQuery);
		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Rental rental = setRentalAttrubutes(rs);
				rentalsList.add(rental);
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
			preparedStatement.setDate(1, rental.getStartDate());
			preparedStatement.setTime(2, rental.getStartTime());
			preparedStatement.setDate(3, rental.getEndDate());
			preparedStatement.setTime(4, rental.getEndTime());
			preparedStatement.setBoolean(5, rental.isReturned());
			preparedStatement.setString(6, rental.getVideogameId());
			preparedStatement.setString(7, rental.getBillId());
			
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
			rental.setBillId(rs.getString("bill_id"));
			rental.setVideogameId(rs.getString("videogame_id"));
			rental.setStartDate(rs.getDate("start_date"));
			rental.setStartTime(rs.getTime("start_time"));
			rental.setEndDate(rs.getDate("end_date"));
			rental.setEndTime(rs.getTime("end_time"));
			rental.setReturned(rs.getBoolean("returned"));
			
			System.out.println(rental.toJavaScriptFunction());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rental;
	}

}
