package edu.ucam.database;

import java.sql.*;

import edu.ucam.enums.ErrorType;


public class DatabaseController {
	
	/*
	 * Static Attributes
	 */
	public static String JDBC_DRIVER = "com.mysql.jdbc.Driver", DATABASE_URL = "jdbc:mysql://localhost:3306/",
			DATABASE_NAME = "infodeo2", DATABASE_USERNAME = "root", DATABASE_PASS = "";
	
	public static Connection DATABASE_CONNECTION = null;
	public static Statement DATABASE_STATEMENT = null;	
	
	/*
	 * Static Methods 
	 */
	public static ErrorType connect(){
			
		try {
			if(DATABASE_STATEMENT != null) {
				DATABASE_STATEMENT.close();
				DATABASE_STATEMENT = null;
				
				if(DATABASE_CONNECTION != null) {
					DATABASE_CONNECTION.close();
					DATABASE_CONNECTION = null;
				}
					
					
			}
			
	        DATABASE_CONNECTION = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASS);
	        DATABASE_STATEMENT = DATABASE_CONNECTION.createStatement();		
		}catch(Exception e) {
			return ErrorType.DATABASE_CONNECTION_ERROR;
		}
        return ErrorType.NO_ERROR;
	}
	
}
