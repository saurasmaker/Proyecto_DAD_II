package edu.ucam.servlets.crud;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.database.DatabaseController;
import edu.ucam.pojos.*;
import edu.ucam.servlets.Controller;

/**
 * Servlet implementation class CreateVideogame
 */
@WebServlet({"/Create", "/create"})
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String objectClass = request.getParameter(Controller.OBJECT_CLASS);		
		
		switch(objectClass) {
		
		case "Assessment":
			createAssessment(request);
			break;
		
		case "Bill":
			createBill(request);
			break;
			
		case "Category":
			createCategory(request);
			break;
			
		case "Purchase":
			createPurchase(request);
			break;
			
		case "Rental":
			createRental(request);
			break;
			
		case "User":
			createUser(request);
			break;
					
		case "Videogame":
			createVideogame(request);
			break;
			
		case "VideogamesCategories":
			createVideogamesCategories(request);
			break;
		
		default:
			break;
		
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		doGet(request, response);
	}
	
	
	
	/*
	 * "Create" Methods
	 */
	private void createAssessment(HttpServletRequest request) {
		//request.getParameter()
	}
	
	private void createBill(HttpServletRequest request) {
		
	}
	
	private void createCategory(HttpServletRequest request) {
		
	}

	private void createPurchase(HttpServletRequest request) {
		
	}
	
	private void createRental(HttpServletRequest request) {
		
	}
	
	private void createUser(HttpServletRequest request) {
		
		User newUser = new User(request.getParameter(User.ATR_USER_USERNAME), request.getParameter(User.ATR_USER_EMAIL), request.getParameter(User.ATR_USER_PASSWORD));
		
		if(newUser.getUsername() != null && newUser.getEmail() != null && newUser.getPassword() != null) {
			try {
				
				DatabaseController.connect();
				DatabaseController.executeQuery("INSERT INTO Users (username, email, password) VALUES (" 
						+ newUser.getUsername() + ", " + newUser.getEmail() + ", " + newUser.getPassword() + ");");
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	private void createVideogame(HttpServletRequest request) {
		
		Videogame newVideogame = new Videogame(request.getParameter(Videogame.ATR_VIDEOGAME_NAME), request.getParameter(Videogame.ATR_VIDEOGAME_DESCRIPTION),
								Date.valueOf(request.getParameter(Videogame.ATR_VIDEOGAME_RELEASEDATE)), Integer.parseInt(Videogame.ATR_VIDEOGAME_STOCK));
		
		if(newVideogame.getName() != null && newVideogame.getDescription() != null && newVideogame.getReleaseDate() != null) {
			try {
				
				DatabaseController.connect();
				DatabaseController.executeQuery("INSERT INTO Videogames (name, description, release_date, stock, purchase_price, rental_price) VALUES (" 
						+ newVideogame.getName() + ", " + newVideogame.getDescription() + ", " + newVideogame.getReleaseDate() + ", " 
						+ newVideogame.getStock() + ", " + newVideogame.getPurchasePrice() + ", " + newVideogame.getRentalPrice() + ");");
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void createVideogamesCategories(HttpServletRequest request) {
		
	}
}
