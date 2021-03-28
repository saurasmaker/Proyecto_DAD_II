package edu.ucam.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.*;

import edu.ucam.database.DatabaseController;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	public static final String OBJECT_CLASS = "OBJECT_CLASS";
	
	@Resource (name = "jdbc/infodeo2")
	private static DataSource ds;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
    public void init() throws ServletException {
    	
    	
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Testing functionality of JDBC
		 */
		
		ResultSet rs = null;
		
		try {
			DatabaseController.connect();
			rs = DatabaseController.DATABASE_STATEMENT.executeQuery("SELECT * FROM users");
			
			while(rs.next()) {
				System.out.println(" - Id: " + rs.getInt("id"));
				System.out.println(" - Username: " + rs.getString("username"));
			}
			
			rs.close();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
