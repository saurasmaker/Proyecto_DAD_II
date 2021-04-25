package edu.ucam.actions.admin;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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
		
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.SELECTED_OBTION_DOES_NOT_EXISTS;	
	}
	
	private String updateAssessment(HttpServletRequest request) {
		
		ErrorType et = ErrorType.NO_ERROR;
		
		Assessment updateAssessment = new Assessment();
		updateAssessment.setId(request.getParameter(Assessment.ATR_ASSESSMENT_ID));
		updateAssessment.setValue(Integer.parseInt(request.getParameter(Assessment.ATR_ASSESSMENT_VALUE)));
		updateAssessment.setSubject(request.getParameter(Assessment.ATR_ASSESSMENT_SUBJECT));
		updateAssessment.setComment(request.getParameter(Assessment.ATR_ASSESSMENT_COMMENT));
		updateAssessment.setPublicationDate(Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_PUBLICATIONDATE)));
		updateAssessment.setPublicationTime(Time.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_PUBLICATIONTIME)));
		updateAssessment.setEditDate(Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_EDITDATE)));
		updateAssessment.setEditTime(Time.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_EDITTIME)));
		updateAssessment.setVideogameId(request.getParameter(Assessment.ATR_ASSESSMENT_VIDEOGAMEID));
		updateAssessment.setUserId(request.getParameter(Assessment.ATR_ASSESSMENT_USERID));
				
		if(updateAssessment.getSubject() != null && updateAssessment.getComment() != null && updateAssessment.getPublicationDate() != null && 
				updateAssessment.getEditDate() != null && updateAssessment.getVideogameId() != null && updateAssessment.getUserId() != null) {
			
			et = (new AssessmentDAO()).update(updateAssessment.getId(), SearchBy.ID, updateAssessment);
			
			if(et == ErrorType.NO_ERROR)
				return "/secured/admin_page.jsp#assessments-title";
		}
		
		return "/mod/error.jsp?ERROR_TYPE=" + et;
	}
	
	private String updateBill(HttpServletRequest request) {

		ErrorType et = ErrorType.NO_ERROR;
		
		Bill updateBill = new Bill();
		updateBill.setId(request.getParameter(Bill.ATR_BILL_ID));
		updateBill.setUserId(request.getParameter(Bill.ATR_BILL_USERID));
		updateBill.setBillingDate(Date.valueOf(request.getParameter(Bill.ATR_BILL_BILLINGDATE)));
		updateBill.setBillingTime(Time.valueOf(request.getParameter(Bill.ATR_BILL_BILLINGTIME)));
		if(request.getParameter(Bill.ATR_BILL_PAID) != null) updateBill.setPaid(true);
		else updateBill.setPaid(false);
		updateBill.setPaidDate(Date.valueOf(request.getParameter(Bill.ATR_BILL_PAIDDATE)));
		updateBill.setPaidTime(Time.valueOf(request.getParameter(Bill.ATR_BILL_PAIDTIME)));
				
		if(updateBill.getUserId() != null && updateBill.getBillingDate() != null) {
			
			et = (new BillDAO()).update(updateBill.getId(), SearchBy.ID, updateBill);
			
			if(et == ErrorType.NO_ERROR)
				return "/secured/admin_page.jsp#bills-title";
		}
		
		return "/mod/error.jsp?ERROR_TYPE=" + et;
	}
	
	private String updateCategory(HttpServletRequest request) {
		
		ErrorType et = ErrorType.NO_ERROR;
		
		Category updateCategory = new Category(request.getParameter(Category.ATR_CATEGORY_NAME), request.getParameter(Category.ATR_CATEGORY_DESCRIPTION));
		updateCategory.setId(request.getParameter(Category.ATR_CATEGORY_ID));
		
		if(updateCategory.getDescription() != null && updateCategory.getName() != null) {
			et = (new CategoryDAO()).update(updateCategory.getId(), SearchBy.ID, updateCategory);
		
			if(et == ErrorType.NO_ERROR) 
				return "/secured/admin_page.jsp#categories-title";
		}
	
	return "/mod/error.jsp?ERROR_TYPE=" + et;
	}
	
	private String updatePurchase(HttpServletRequest request) {
		
		ErrorType et = ErrorType.NO_ERROR;
		
		Purchase updatePurchase = new Purchase();
		updatePurchase.setId(request.getParameter(Purchase.ATR_PURCHASE_ID));
		updatePurchase.setAmount(Integer.parseInt(request.getParameter(Purchase.ATR_PURCHASE_AMOUNT)));
		updatePurchase.setBillId(request.getParameter(Purchase.ATR_PURCHASE_BILLID));
		updatePurchase.setVideogameId(request.getParameter(Purchase.ATR_PURCHASE_VIDEOGAMEID));

		if(updatePurchase.getVideogameId() != null && updatePurchase.getBillId() != null) {
			
			et = (new PurchaseDAO()).update(updatePurchase.getId(), SearchBy.ID, updatePurchase);
			
			if(et == ErrorType.NO_ERROR)
				return "/secured/admin_page.jsp#purchases-title";
		}
		
		return "/mod/error.jsp?ERROR_TYPE=" + et;
	}
	
	private String updateRental(HttpServletRequest request) {
		
		ErrorType et = ErrorType.NO_ERROR;
		
		Rental updateRental = new Rental();
		updateRental.setId(request.getParameter(Rental.ATR_RENTAL_ID));
		updateRental.setUserId(request.getParameter(Rental.ATR_RENTAL_USERID));
		updateRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
		updateRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
		updateRental.setStartDate(Timestamp.valueOf(request.getParameter(Rental.ATR_RENTAL_STARTDATE).replace("T"," ")));
		updateRental.setEndDate(Timestamp.valueOf(request.getParameter(Rental.ATR_RENTAL_ENDDATE).replace("T"," ")));
		updateRental.setReturned(Boolean.valueOf(request.getParameter(Rental.ATR_RENTAL_RETURNED)));

		if(updateRental.getVideogameId() != null && updateRental.getUserId() != null) {
			
			et = (new RentalDAO()).update(updateRental.getId(), SearchBy.ID, updateRental);
			
			if(et == ErrorType.NO_ERROR)
				return "/secured/admin_page.jsp#rentals-title";
		}
		
		return "/mod/error.jsp?ERROR_TYPE=" + et;
	}

	private String updateUser(HttpServletRequest request) {
		
		ErrorType et = ErrorType.NO_ERROR;
		
		User updateUser = new User(request.getParameter(User.ATR_USER_USERNAME), request.getParameter(User.ATR_USER_EMAIL), request.getParameter(User.ATR_USER_PASSWORD));
		updateUser.setId(request.getParameter(User.ATR_USER_ID));
		String buffer = request.getParameter(User.ATR_USER_SIGNUPDATE);
		System.out.println(buffer.replace("T"," ")+":00.0");

		if(buffer!=null) updateUser.setSignUpDate(Timestamp.valueOf(buffer.replace("T"," ")));
		buffer = request.getParameter(User.ATR_USER_LASTSIGNIN);
		if(buffer != null)updateUser.setLastSignIn(Timestamp.valueOf(buffer.replace("T"," ")));
				
		if(updateUser.getEmail() != null && updateUser.getPassword() != null) {
			
			et = (new UserDAO()).update(updateUser.getId(), SearchBy.ID, updateUser);
			
			if(et == ErrorType.NO_ERROR)
				return "/secured/admin_page.jsp#users-title";
		}
		
		return "/mod/error.jsp?ERROR_TYPE=" + et;
	}
	
	private String updateVideogame(HttpServletRequest request) {
		
		ErrorType et = ErrorType.NO_ERROR;
		
		Videogame updateVideogame = new Videogame(request.getParameter(Videogame.ATR_VIDEOGAME_NAME), request.getParameter(Videogame.ATR_VIDEOGAME_DESCRIPTION),
				Date.valueOf(request.getParameter(Videogame.ATR_VIDEOGAME_RELEASEDATE)), Integer.parseInt(request.getParameter(Videogame.ATR_VIDEOGAME_STOCK)));
		updateVideogame.setId(request.getParameter(Videogame.ATR_VIDEOGAME_ID));
		updateVideogame.setPurchasePrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_PURCHASEPRICE)));
		updateVideogame.setRentalPrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_RENTALPRICE)));
	
		if(updateVideogame.getName() != null && updateVideogame.getDescription() != null) {
			
			et = (new VideogameDAO()).update(updateVideogame.getId(), SearchBy.ID, updateVideogame);
			
			if(et == ErrorType.NO_ERROR)
				return "/secured/admin_page.jsp#videogames-title";
		}
		
		return "/mod/error.jsp?ERROR_TYPE=" + et;
	}
}
