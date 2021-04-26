package edu.ucam.actions.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.servlets.Controller;
import edu.ucam.interfaces.IAction;

import edu.ucam.daos.*;
import edu.ucam.enums.*;
import edu.ucam.pojos.*;



public class Update implements IAction{
	
	public static final String ATR_ACTION = "ATR_UPDATE_ACTION";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String objectClass = request.getParameter(Controller.ATR_OBJECT_CLASS);		
		
		if(objectClass != null)
		switch(objectClass) {
		
		case "edu.ucam.pojos.Assessment":
			return updateAssessment(request);
		
		case "edu.ucam.pojos.Bill":
			return updateBill(request);
			
		case "edu.ucam.pojos.Category":
			return updateCategory(request);
			
		case "edu.ucam.pojos.Purchase":
			return updatePurchase(request);
			
		case "edu.ucam.pojos.Rental":
			return updateRental(request);
			
		case "edu.ucam.pojos.User":
			return updateUser(request);
					
		case "edu.ucam.pojos.Videogame":
			return updateVideogame(request);
		}
		
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.SELECTED_OPTION_DOES_NOT_EXISTS;	
	}
	
	
	/*
	 * "Update" Methods
	 */
	private String updateAssessment(HttpServletRequest request) {
		
		try {
			Assessment updateAssessment = new Assessment(request);
			(new AssessmentDAO()).update(updateAssessment.getId(), SearchBy.ID, updateAssessment);
			return "/secured/admin_page.jsp#assessments-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_ASSESSMENT_ERROR;
	}
	
	
	private String updateBill(HttpServletRequest request) {

		try {
			Bill updateBill = new Bill(request);
			(new BillDAO()).update(updateBill.getId(), SearchBy.ID, updateBill);
			return "/secured/admin_page.jsp#bills-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_BILL_ERROR;
	}
	
	
	private String updateCategory(HttpServletRequest request) {
		
		try {
			Category updateCategory = new Category(request);
			(new CategoryDAO()).update(updateCategory.getId(), SearchBy.ID, updateCategory);
			return "/secured/admin_page.jsp#categories-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_CATEGORY_ERROR;
	}
	
	
	private String updatePurchase(HttpServletRequest request) {
		
		try {
			Purchase updatePurchase = new Purchase(request);
			(new PurchaseDAO()).update(updatePurchase.getId(), SearchBy.ID, updatePurchase);
			return "/secured/admin_page.jsp#purchases-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_PURCHASE_ERROR;
	}
	
	
	private String updateRental(HttpServletRequest request) {
		
		try {
			Rental updateRental = new Rental(request);
			(new RentalDAO()).update(updateRental.getId(), SearchBy.ID, updateRental);
			return "/secured/admin_page.jsp#rentals-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_RENTAL_ERROR;
	}

	
	private String updateUser(HttpServletRequest request) {
		
		try {
			User updateUser = new User(request);
			(new UserDAO()).update(updateUser.getId(), SearchBy.ID, updateUser);
			return "/secured/admin_page.jsp#users-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_USER_ERROR;
	}
	
	
	private String updateVideogame(HttpServletRequest request) {
		
		try {
			Videogame updateVideogame = new Videogame(request);
			(new VideogameDAO()).update(updateVideogame.getId(), SearchBy.ID, updateVideogame);
			return "/secured/admin_page.jsp#videogames-title";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_VIDEOGAME_ERROR;
	}
}
