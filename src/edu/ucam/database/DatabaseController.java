package edu.ucam.database;

import java.sql.*;


public class DatabaseController {
	
	/*
	 * Static Attributes
	 */
	public static String JDBC_DRIVER = "com.mysql.jdbc.Driver", DATABASE_URL = "jdbc:mysql://localhost:3306/",
			DATABASE_NAME = "infodeo2", DATABASE_USERNAME = "root", DATABASE_PASS = "";
	
	public static Statement DATABASE_STATEMENT = null;
	
	
	
	/*
	 * Static Methods 
	 */
	public static void connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
				
		if(DATABASE_STATEMENT != null)
			DATABASE_STATEMENT.close();
		
        Driver driver = (Driver) Class.forName(JDBC_DRIVER).newInstance();
        DriverManager.registerDriver(driver);
        DATABASE_STATEMENT = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASS).createStatement();		

		System.out.println("DataBase Connection success...");
	}
	
	public static void executeQuery(String sql) throws SQLException {
		DATABASE_STATEMENT.executeQuery(sql);
	}
	
}
