package edu.ucam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.Assessment;

public class AssessmentDAO implements IDao<Assessment>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(Assessment assessment) {
		return executeQueryWithParameters("INSERT INTO assessments (value, subject, comment, publication_date, publication_time, edit_date, edit_time, videogame_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" , assessment);		
	}

	@Override
	public Assessment read(String search, SearchBy searchBy) {
		Assessment assessment = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM assessments WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					assessment = setAssessmentAttributes(rs);
				}
			}
			rs.close();
		} catch (Exception e)  {
			e.printStackTrace();
		}	
			
		return assessment;
	}

	
	@Override
	public ErrorType update(String search, SearchBy searchBy, Assessment assessment) {
		String updateQuery = "UPDATE assessments SET value = ?, subject = ?, comment = ?, publication_date = ?, publication_time = ?, edit_date = ?, edit_time = ?, videogame_id = ?, user_id = ? WHERE ";
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
		return executeQueryWithParameters(updateQuery, assessment);
	}

	
	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		String updateQuery = "DELETE FROM assessments WHERE ";
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			DatabaseController.DATABASE_STATEMENT.executeUpdate(updateQuery);	
		} catch (Exception e)  {
			e.printStackTrace();
			return ErrorType.ERROR;
		}	
		
		return ErrorType.NO_ERROR;
	}

	@Override
	public ArrayList<Assessment> list() {
		String updateQuery = "SELECT * FROM assessments"; 		
		ResultSet rs = null;
		ArrayList<Assessment> assessmentsList = new ArrayList<Assessment>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Assessment assessment = setAssessmentAttributes(rs);
				assessmentsList.add(assessment);
			}			
			rs.close();
		} catch (Exception e)  {
			e.printStackTrace();
		}	
		
		return assessmentsList;
	}
	
	
	
	/*
	 * Tool Methods
	 */
	public Assessment readByVideogameUserId(String userId, String videogameId) {
		Assessment assessment = null;
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM assessments WHERE user_id = '" + userId + "' AND videogame_id = '" + videogameId + "'" ; 
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(selectQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					assessment = setAssessmentAttributes(rs);
				}
			}
			rs.close();
		} catch (Exception e)  {
			e.printStackTrace();
		}	
			
		return assessment;
	}
	
	
	public ArrayList<Assessment> listByVideogameId(String videogameId) {
		
		ArrayList<Assessment> assessmentsList = new ArrayList<Assessment>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM assessments WHERE videogame_id = '" + videogameId + "'"; 
		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Assessment assessment = setAssessmentAttributes(rs);
				assessmentsList.add(assessment);
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
				
		return assessmentsList;
	}
	
	
	public ArrayList<Assessment> listByUserId(String userId) {
		
		ArrayList<Assessment> assessmentsList = new ArrayList<Assessment>();
			
		ResultSet rs = null;
		
		String selectQuery = "SELECT * FROM assessments WHERE user_id = '" + userId + "'"; 
		try {
			rs = DatabaseController.DATABASE_CONNECTION.createStatement().executeQuery(selectQuery);					
			while(rs.next()) {
				Assessment assessment = setAssessmentAttributes(rs);
				assessmentsList.add(assessment);
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return assessmentsList;
	}
	
	
	private ErrorType executeQueryWithParameters(String query, Assessment assessment) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setInt(1, assessment.getValue());
			preparedStatement.setString(2, assessment.getSubject());
			preparedStatement.setString(3, assessment.getComment());
			preparedStatement.setDate(4, assessment.getPublicationDate());
			preparedStatement.setTime(5, assessment.getPublicationTime());
			preparedStatement.setDate(6, assessment.getEditDate());
			preparedStatement.setTime(7, assessment.getEditTime());
			preparedStatement.setString(8, assessment.getVideogameId());
			preparedStatement.setString(9, assessment.getUserId());
			
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	
	private Assessment setAssessmentAttributes(ResultSet rs) {
		Assessment assessment = null;
		try {
			assessment = new Assessment();
			assessment.setId(rs.getString("id"));
			assessment.setValue(rs.getInt("value"));
			assessment.setSubject(rs.getString("subject"));
			assessment.setComment(rs.getString("comment"));
			assessment.setPublicationDate(rs.getDate("publication_date"));
			assessment.setPublicationTime(rs.getTime("publication_time"));
			assessment.setEditDate(rs.getDate("edit_date"));
			assessment.setEditTime(rs.getTime("edit_time"));
			assessment.setVideogameId(rs.getString("videogame_id"));
			assessment.setUserId(rs.getString("user_id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return assessment;
	}


}
