package edu.ucam.actions.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.*;
import edu.ucam.enums.*;
import edu.ucam.pojos.*;

import edu.ucam.interfaces.IAction;
import edu.ucam.servlets.Controller;

public class Delete implements IAction{
	
	public static final String ATR_ACTION = "ATR_DELETE_ACTION";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ErrorType et = ErrorType.NO_ERROR;
		
		String url = "/secured/admin_page.jsp";
		String objectClass = request.getParameter(Controller.ATR_OBJECT_CLASS);		

		if(objectClass != null)
		switch(objectClass) {
		
		case "edu.ucam.pojos.Assessment":
			et = deleteAssessment(request.getParameter(Assessment.ATR_ASSESSMENT_ID));
			url += "#assessments-title";
			break;
		
		case "edu.ucam.pojos.Bill":
			et = deleteBill(request.getParameter(Bill.ATR_BILL_ID));
			url += "#bills-title";
			break;
			
		case "edu.ucam.pojos.Category":
			et = deleteCategory(request.getParameter(Category.ATR_CATEGORY_ID));
			url += "#categories-title";
			break;
			
		case "edu.ucam.pojos.Purchase":
			et = deletePurchase(request.getParameter(Purchase.ATR_PURCHASE_ID));
			url += "#purchases-title";
			break;
			
		case "edu.ucam.pojos.Rental":
			et = deleteRental(request.getParameter(Rental.ATR_RENTAL_ID));
			url += "#rentals-title";
			break;
			
		case "edu.ucam.pojos.User":
			et = deleteUser(request.getParameter(User.ATR_USER_ID));
			url += "#users-title";
			break;
					
		case "edu.ucam.pojos.Videogame":
			et = deleteVideogame(request.getParameter(Videogame.ATR_VIDEOGAME_ID));
			url += "#videogames-title";
			break;
			
		case "edu.ucam.pojos.VideogameCategory":
			et = deleteVideogameCategory(request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_ID));
			url += "#videogames-title";
			break;
			
		case "edu.ucam.pojos.VideogameImage":
			et = deleteVideogameImage(request.getParameter(VideogameImage.ATR_VIDEOGAMEIMAGE_ID));
			url += "#videogames-title";
			break;
		
		}
		
		if(et != ErrorType.NO_ERROR)
			return "/mod/error.jsp?ERROR_TYPE=" + et;
		else
			return url;
		
	}
	
	
	private ErrorType deleteAssessment(String idAssessment) {
		
		if(idAssessment != null) 
			return (new AssessmentDAO()).delete(idAssessment, SearchBy.ID);
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteBill(String idBill) {
		
		if(idBill != null) 
			return (new BillDAO()).delete(idBill, SearchBy.ID);
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteCategory(String idCategory) {
		
		if(idCategory != null) {
			
			if((new VideogameCategoryDAO()).listByVideogameId(idCategory).size() > 0)
				return ErrorType.CATEGORY_STILL_HAS_ASSOCIATED_VIDEOGAMES;
			
			return (new CategoryDAO()).delete(idCategory, SearchBy.ID);
		}
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deletePurchase(String idPurchase) {
		
		if(idPurchase != null) 
			return (new PurchaseDAO()).delete(idPurchase, SearchBy.ID);
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteRental(String idRental) {
		
		if(idRental != null) 
			return (new RentalDAO()).delete(idRental, SearchBy.ID);
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteUser(String idUser) {
		
		if(idUser != null) 
			return (new UserDAO()).delete(idUser, SearchBy.ID);
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteVideogame(String idVideogame) {
		
		if(idVideogame != null) {
			
			if((new VideogameCategoryDAO()).listByVideogameId(idVideogame).size() > 0)
				return ErrorType.VIDEOGAME_STILL_HAS_ASSOCIATED_CATEGORIES;
			
			return (new VideogameDAO()).delete(idVideogame, SearchBy.ID);
		}
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteVideogameCategory(String idVideogamesCategories) {
		if(idVideogamesCategories != null) 
			return (new VideogameCategoryDAO()).delete(idVideogamesCategories, SearchBy.ID);
		
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteVideogameImage(String idVideogameImage) {
		if(idVideogameImage != null) 
			return (new VideogameImageDAO()).delete(idVideogameImage, SearchBy.ID);
		
		return ErrorType.PARAMETER_NULL;
	}

}
