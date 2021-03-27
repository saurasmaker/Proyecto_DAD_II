package edu.uca.database;

import java.sql.*;


public class DatabaseConnection {
	
	/*
	 * Static Attributes
	 */
	public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";


	
	
	
	/*
	 * Static Methods 
	 */
	public static Connection connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Connection connection = null;
		
        Driver driver = (Driver) Class.forName(JDBC_DRIVER).newInstance();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/infodeo2", "root", "");		

		System.out.println("JDBC Driver loaded successfully");

		return connection;
	}
	
}
