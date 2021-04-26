package edu.ucam.actions.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import edu.ucam.daos.*;
import edu.ucam.pojos.*;

import edu.ucam.servlets.Controller;
import edu.ucam.enums.ErrorType;
import edu.ucam.interfaces.IAction;

public class Create implements IAction{
	
	public static final String ATR_ACTION = "ATR_CREATE_ACTION";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String objectClass = request.getParameter(Controller.ATR_OBJECT_CLASS);				
		
		if(objectClass != null)
		switch(objectClass) {
		
		case "edu.ucam.pojos.Assessment":
			return createAssessment(request);
		
		case "edu.ucam.pojos.Bill":
			return createBill(request);
			
		case "edu.ucam.pojos.Category":
			return createCategory(request);
			
		case "edu.ucam.pojos.Purchase":
			return createPurchase(request);
			
		case "edu.ucam.pojos.Rental":			
			return createRental(request);
			
		case "edu.ucam.pojos.User":
			return createUser(request);
					
		case "edu.ucam.pojos.Videogame":
			return createVideogame(request);
			
		case "edu.ucam.pojos.VideogameImage":
			return createVideogamesImages(request);
		
		case "edu.ucam.pojos.VideogameCategory":
			return createVideogamesCategories(request);

		}
		
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.SELECTED_OPTION_DOES_NOT_EXISTS;
	}
	
	
	
	/*
	 * "Create" Methods
	 */
	private String createAssessment(HttpServletRequest request) {
				
		try {
			(new AssessmentDAO()).create(new Assessment(request));
			return "/secured/admin_page.jsp#assessments-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_ASSESSMENT_ERROR;
	}	
	
	
	private String createBill(HttpServletRequest request) {
				
		try {
			(new BillDAO()).create(new Bill(request));
			return "/secured/admin_page.jsp#bills-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_BILL_ERROR;
	}
	
	
	
	private String createCategory(HttpServletRequest request) {
		
		try {
			(new CategoryDAO()).create(new Category(request));
			return "/secured/admin_page.jsp#categories-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_CATEGORY_ERROR;
	}

	
	
	private String createPurchase(HttpServletRequest request) {
		
		try {
			(new PurchaseDAO()).create(new Purchase(request));
			return "/secured/admin_page.jsp#purchases-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_PURCHASE_ERROR;
	}
	
	
	
	private String createRental(HttpServletRequest request) {
		
		try {
			(new RentalDAO()).create(new Rental(request));
			return "/secured/admin_page.jsp#rentals-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_RENTAL_ERROR;
	}
	
	
	
	private String createUser(HttpServletRequest request) {
		
		try {
			(new UserDAO()).create(new User(request));
			return "/secured/admin_page.jsp#users-title";
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_USER_ERROR;	
	}
	
	
	private String createVideogame(HttpServletRequest request) {
		
		try {
			(new VideogameDAO()).create(new Videogame(request));
			return "/secured/admin_page.jsp#videogames-title";
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_VIDEOGAME_ERROR;	
	}
	
	
	private String createVideogamesImages(HttpServletRequest request) {

		try {
			
			VideogameImage newVideogameImage = new VideogameImage();
			
			Part imagePart = request.getPart(VideogameImage.ATR_VIDEOGAMEIMAGE_IMAGE);
			InputStream imageInputStream = imagePart.getInputStream(); 
			byte[] imageByteArray = new byte[imageInputStream.available()];
			imageInputStream.read(imageByteArray);
			
			newVideogameImage.setName(Paths.get(imagePart.getSubmittedFileName()).getFileName().toString());
			newVideogameImage.setImage(imageByteArray);
			newVideogameImage.setVideogameId(request.getParameter(VideogameImage.ATR_VIDEOGAMEIMAGE_VIDEOGAMEID));
			
			if(newVideogameImage.getName() != null && newVideogameImage.getVideogameId() != null && newVideogameImage.getImage() != null) {
				(new VideogameImageDAO()).create(newVideogameImage);
				return "/secured/admin_page.jsp#videogames-title";
			}
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_VIDEOGAME_IMAGE_ERROR;
	}
	
	
	private String createVideogamesCategories(HttpServletRequest request) {
		
		try {
			VideogameCategory newVideogamesCategories = new VideogameCategory(request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID),
					request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_CATEGORYID));
			
			if(newVideogamesCategories.getCategoryId() != null && newVideogamesCategories.getVideogameId() != null) {
				(new VideogameCategoryDAO()).create(newVideogamesCategories);
				return "/secured/admin_page.jsp#videogames-title";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_VIDEOGAME_CATEGORY_ERROR;
	}
	
}
