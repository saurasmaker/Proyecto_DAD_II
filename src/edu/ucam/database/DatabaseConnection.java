package edu.ucam.database;

import java.sql.*;


public class DatabaseConnection {
	
	/*
	 * Static Attributes
	 */
	public static String JDBC_DRIVER = "com.mysql.jdbc.Driver", DATABASE_URL = "jdbc:mysql://localhost:3306/",
			DATABASE_NAME = "infodeo2", DATABASE_USERNAME = "root", DATABASE_PASS = "";
	


	
	
	
	/*
	 * Static Methods 
	 */
	public static Statement connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Connection connection = null;
		
        Driver driver = (Driver) Class.forName(JDBC_DRIVER).newInstance();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASS);		

		System.out.println("JDBC Driver loaded successfully");

		return connection.createStatement();
	}
	
}
