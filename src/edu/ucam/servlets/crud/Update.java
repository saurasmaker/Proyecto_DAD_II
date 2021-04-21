package edu.ucam.servlets.crud;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.*;
import edu.ucam.enums.*;
import edu.ucam.pojos.*;

import edu.ucam.servlets.Controller;

/**
 * Servlet implementation class UpdateVideogame
 */
@WebServlet({"/UPDATE", "/Update", "/update"})
public class Update extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	String url = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getHeader("referer"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		url = request.getHeader("referer");

		String objectClass = request.getParameter(Controller.ATR_OBJECT_CLASS);		
		
		if(objectClass != null)
		switch(objectClass) {
		
		case "edu.ucam.pojos.Assessment":
			updateAssessment(request);
			url += "#assessments-title";
			break;
		
		case "edu.ucam.pojos.Bill":
			updateBill(request);
			url += "#bills-title";
			break;
			
		case "edu.ucam.pojos.Category":
			updateCategory(request);
			url += "#categories-title";
			break;
			
		case "edu.ucam.pojos.Purchase":
			updatePurchase(request);
			url += "#purchases-title";
			break;
			
		case "edu.ucam.pojos.Rental":
			updateRental(request);
			url += "#rentals-title";
			break;
			
		case "edu.ucam.pojos.User":
			updateUser(request);
			url += "#users-title";
			break;
					
		case "edu.ucam.pojos.Videogame":
			updateVideogame(request);
			url += "#videogames-title";
			break;
		
		default:
			break;
		
		}
				
		doGet(request, response);
	}
	
	private ErrorType updateAssessment(HttpServletRequest request) {
		
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
		
		System.out.println(updateAssessment.toJavaScriptFunction());
		
		if(updateAssessment.getSubject() != null && updateAssessment.getComment() != null && updateAssessment.getPublicationDate() != null && 
				updateAssessment.getEditDate() != null && updateAssessment.getVideogameId() != null && updateAssessment.getUserId() != null)
			return (new AssessmentDAO()).update(updateAssessment.getId(), SearchBy.ID, updateAssessment);
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType updateBill(HttpServletRequest request) {

		Bill updateBill = new Bill();
		updateBill.setId(request.getParameter(Bill.ATR_BILL_ID));
		updateBill.setUserId(request.getParameter(Bill.ATR_BILL_USERID));
		updateBill.setBillingDate(Date.valueOf(request.getParameter(Bill.ATR_BILL_BILLINGDATE)));
		updateBill.setBillingTime(Time.valueOf(request.getParameter(Bill.ATR_BILL_BILLINGTIME)));
		if(request.getParameter(Bill.ATR_BILL_PAID) != null) updateBill.setPaid(true);
		else updateBill.setPaid(false);
		updateBill.setPaidDate(Date.valueOf(request.getParameter(Bill.ATR_BILL_PAIDDATE)));
		updateBill.setPaidTime(Time.valueOf(request.getParameter(Bill.ATR_BILL_PAIDTIME)));
		
		System.out.println(updateBill.toJavaScriptFunction());
		
		if(updateBill.getUserId() != null && updateBill.getBillingDate() != null) {
			return (new BillDAO()).update(updateBill.getId(), SearchBy.ID, updateBill);
		}
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType updateCategory(HttpServletRequest request) {
		
		Category updateCategory = new Category(request.getParameter(Category.ATR_CATEGORY_NAME), request.getParameter(Category.ATR_CATEGORY_DESCRIPTION));
		updateCategory.setId(request.getParameter(Category.ATR_CATEGORY_ID));
		
		if(updateCategory.getName() != null && updateCategory.getDescription() != null) 
			return (new CategoryDAO()).update(updateCategory.getId(), SearchBy.ID, updateCategory);
			
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType updatePurchase(HttpServletRequest request) {
		
		Purchase updatePurchase = new Purchase();
		updatePurchase.setId(request.getParameter(Purchase.ATR_PURCHASE_ID));
		updatePurchase.setAmount(Integer.parseInt(request.getParameter(Purchase.ATR_PURCHASE_AMOUNT)));
		updatePurchase.setBillId(request.getParameter(Purchase.ATR_PURCHASE_BILLID));
		updatePurchase.setVideogameId(request.getParameter(Purchase.ATR_PURCHASE_VIDEOGAMEID));

		if(updatePurchase.getBillId() != null && updatePurchase.getVideogameId() != null) 
			return (new PurchaseDAO()).update(updatePurchase.getId(), SearchBy.ID, updatePurchase);
			
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType updateRental(HttpServletRequest request) {
		
		Rental updateRental = new Rental();
		updateRental.setId(request.getParameter(Rental.ATR_RENTAL_ID));
		updateRental.setUserId(request.getParameter(Rental.ATR_RENTAL_USERID));
		updateRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
		updateRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
		updateRental.setStartDate(Timestamp.valueOf(request.getParameter(Rental.ATR_RENTAL_STARTDATE).replace("T"," ")));
		updateRental.setEndDate(Timestamp.valueOf(request.getParameter(Rental.ATR_RENTAL_ENDDATE).replace("T"," ")));
		updateRental.setReturned(Boolean.valueOf(request.getParameter(Rental.ATR_RENTAL_RETURNED)));

		if(updateRental.getUserId() != null && updateRental.getVideogameId() != null)
			return (new RentalDAO()).update(updateRental.getId(), SearchBy.ID, updateRental);
		
		return ErrorType.PARAMETER_NULL;
	}

	private ErrorType updateUser(HttpServletRequest request) {
		User updateUser = new User(request.getParameter(User.ATR_USER_USERNAME), request.getParameter(User.ATR_USER_EMAIL), request.getParameter(User.ATR_USER_PASSWORD));
		updateUser.setId(request.getParameter(User.ATR_USER_ID));
		String buffer = request.getParameter(User.ATR_USER_SIGNUPDATE);
		System.out.println(buffer.replace("T"," ")+":00.0");

		if(buffer!=null) updateUser.setSignUpDate(Timestamp.valueOf(buffer.replace("T"," ")));
		buffer = request.getParameter(User.ATR_USER_LASTSIGNIN);
		if(buffer != null)updateUser.setLastSignIn(Timestamp.valueOf(buffer.replace("T"," ")));
				
		if(updateUser.getUsername() != null && updateUser.getEmail() != null && updateUser.getPassword() != null)
			return (new UserDAO()).update(updateUser.getId(), SearchBy.ID, updateUser);
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType updateVideogame(HttpServletRequest request) {
		
		Videogame newVideogame = new Videogame(request.getParameter(Videogame.ATR_VIDEOGAME_NAME), request.getParameter(Videogame.ATR_VIDEOGAME_DESCRIPTION),
				Date.valueOf(request.getParameter(Videogame.ATR_VIDEOGAME_RELEASEDATE)), Integer.parseInt(request.getParameter(Videogame.ATR_VIDEOGAME_STOCK)));
		newVideogame.setId(request.getParameter(Videogame.ATR_VIDEOGAME_ID));
		newVideogame.setPurchasePrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_PURCHASEPRICE)));
		newVideogame.setRentalPrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_RENTALPRICE)));
	
		if(newVideogame.getName() != null && newVideogame.getDescription() != null && newVideogame.getReleaseDate() != null) 
			return (new VideogameDAO()).update(newVideogame.getId(), SearchBy.ID, newVideogame);
				
		return ErrorType.PARAMETER_NULL;
	}

	
	
	
	/*
	private ErrorType updateVideogamesCategories(HttpServletRequest request){
		
		VideogameCategory newVideogamesCategories = new VideogameCategory(request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID),
				request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_CATEGORYID));
		newVideogamesCategories.setId(request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_ID));
		
		if(newVideogamesCategories.getCategoryId() != null && newVideogamesCategories.getVideogameId() != null) 
			(new VideogameCategoryDAO()).create(newVideogamesCategories);		
		
		return ErrorType.PARAMETER_NULL;
	}
	*/

}
