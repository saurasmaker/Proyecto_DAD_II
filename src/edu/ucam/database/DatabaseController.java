package edu.ucam.database;

import java.sql.*;


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
	public static void connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
				
		if(DATABASE_STATEMENT != null) {
			DATABASE_STATEMENT.close();
			DATABASE_STATEMENT = null;
			
			if(DATABASE_CONNECTION != null) {
				DATABASE_CONNECTION.close();
				DATABASE_CONNECTION = null;
			}
				
				
		}
		
        //Driver driver = (Driver) Class.forName(JDBC_DRIVER).newInstance();
        //DriverManager.registerDriver(driver);
        DATABASE_CONNECTION = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASS);
        DATABASE_STATEMENT = DATABASE_CONNECTION.createStatement();		
	}
	
}
