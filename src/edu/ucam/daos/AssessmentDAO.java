package edu.ucam.daos;

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
		try {
			DatabaseController.DATABASE_STATEMENT.executeUpdate("INSERT INTO assessments (value, subject, comment, publication_date, edit_date, videogame_id, user_id) " + 
					"VALUES ('" +
						assessment.getValue() + "', '" + assessment.getSubject() + "', '" + assessment.getComment() + "', '" + assessment.getPublicationDate() +
						"', '" + assessment.getEditDate() + "', '" + assessment.getVideogameId() + "', '" + assessment.getUserId() +
					"')");		
			
			return ErrorType.NO_ERROR;
			
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return ErrorType.JDBC_ERROR_CONNECTION;
		}
	}

	@Override
	public Assessment read(String search, SearchBy searchBy) {
		Assessment assessment = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM assessments WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery + search + "'");	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					assessment = new Assessment();
					assessment.setId(rs.getString("id"));
					assessment.setValue(rs.getInt("value"));
					assessment.setSubject(rs.getString("subject"));
					assessment.setComment(rs.getString("comment"));
					assessment.setPublicationDate(rs.getDate("publication_date"));
					assessment.setEditDate(rs.getDate("edit_date"));
					assessment.setVideogameId(rs.getString("videogame_id"));
					assessment.setUserId(rs.getString("user_id"));
				}
			}
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return assessment;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, Assessment assessment) {
		String updateQuery = "UPDATE assessments SET " + 
				"value = '" + assessment.getValue()  + "', " + 
				"subject = '" +  assessment.getSubject() + "', " + 
				"comment = '" +  assessment.getComment() + "', " + 
				"publication_date = '" +  assessment.getPublicationDate() + "', " + 
				"edit_date = '" +  assessment.getEditDate() + "', " + 
				"videogame_id = '" +  assessment.getVideogameId() + "', " + 
				"user_id = '" + assessment.getUserId() + "' " + 
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
		String updateQuery = "DELETE FROM assessments WHERE ";
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
	public ArrayList<Assessment> list() {
		String updateQuery = "SELECT * FROM assessments"; 		
		ResultSet rs = null;
		ArrayList<Assessment> assessmentsList = new ArrayList<Assessment>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				Assessment assessment = new Assessment();
				assessment.setId(rs.getString("id"));
				assessment.setValue(rs.getInt("value"));
				assessment.setSubject(rs.getString("subject"));
				assessment.setComment(rs.getString("comment"));
				assessment.setPublicationDate(rs.getDate("publication_date"));
				assessment.setEditDate(rs.getDate("edit_date"));
				assessment.setVideogameId(rs.getString("videogame_id"));
				assessment.setUserId(rs.getString("user_id"));
				
				assessmentsList.add(assessment);
			}			
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return assessmentsList;
	}



}
