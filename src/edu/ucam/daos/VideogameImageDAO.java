package edu.ucam.daos;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IDao;
import edu.ucam.pojos.VideogameImage;

public class VideogameImageDAO implements IDao<VideogameImage>{

	/*
	 * CRUD Methods
	 */
	@Override
	public ErrorType create(VideogameImage videogameImage) {
		return executeStatementWithParameters("INSERT INTO videogames_images (name, image, videogame_id) VALUES (?, ?, ?)", videogameImage);
	}

	@Override
	public VideogameImage read(String search, SearchBy searchBy) {
		
		VideogameImage videogameImage = null;
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM videogames_images WHERE "; 
		try {
			updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);	
			if(rs.next()) { //se valida si hay resultados
				if(rs.getRow() == 1) {
					videogameImage = setVideogameImageAttributes(rs);
				}
			}
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return videogameImage;
	}

	@Override
	public ErrorType update(String search, SearchBy searchBy, VideogameImage videogameImage) {
		String updateQuery = "UPDATE videogames_images SET name = ?, image = ?, videogame_id = ? WHERE ";
		updateQuery = IDao.appendSqlSearchBy(updateQuery, searchBy, search);
		return executeStatementWithParameters(updateQuery, videogameImage);	
	}

	@Override
	public ErrorType delete(String search, SearchBy searchBy) {
		String updateQuery = "DELETE FROM videogames_images WHERE ";
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
	public ArrayList<VideogameImage> list() {
		
		String updateQuery = "SELECT * FROM videogames_images"; 		
		ResultSet rs = null;
		ArrayList<VideogameImage> videogamesImagesList = new ArrayList<VideogameImage>();
		
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				VideogameImage videogameImage = setVideogameImageAttributes(rs);
				videogamesImagesList.add(videogameImage);
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
		
		return videogamesImagesList;
	}
	

	/*
	 * Tool Methods
	 */
	public ArrayList<VideogameImage> listByVideogameId(String videogameId) {
		ArrayList<VideogameImage> videogameImagesList = new ArrayList<VideogameImage>();
		ResultSet rs = null;
		
		String updateQuery = "SELECT * FROM videogames_images WHERE videogame_id = '" + videogameId + "'"; 
		try {
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery(updateQuery);					
			while(rs.next()) {
				VideogameImage videogameImage = setVideogameImageAttributes(rs);
				videogameImagesList.add(videogameImage);
			}	
			rs.close();
		} catch (SQLException e)  {
			e.printStackTrace();
		}	
			
		return videogameImagesList;
	}
	
	
	private ErrorType executeStatementWithParameters(String query, VideogameImage videogameImage) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = DatabaseController.DATABASE_CONNECTION.prepareStatement(query);
			preparedStatement.setString(1, videogameImage.getName());
			preparedStatement.setBlob(2, new ByteArrayInputStream(videogameImage.getImage()));
			preparedStatement.setString(3, videogameImage.getVideogameId());
			
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorType.DATABASE_STATEMENT_ERROR;
		}
		return ErrorType.NO_ERROR;
	}
	
	private VideogameImage setVideogameImageAttributes(ResultSet rs) {
		VideogameImage videogameImage = null;
		try {
			Blob blobImage= rs.getBlob("image");
			videogameImage = new VideogameImage();
			videogameImage.setId(rs.getString("id"));
			videogameImage.setName(rs.getString("name"));
			videogameImage.setImage(blobImage.getBytes(1, (int)blobImage.length()));
			videogameImage.setVideogameId("videogame_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return videogameImage;
	}

	

}
