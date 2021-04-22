package edu.ucam.servlets.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.*;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.pojos.Assessment;
import edu.ucam.pojos.Bill;
import edu.ucam.pojos.Category;
import edu.ucam.pojos.Purchase;
import edu.ucam.pojos.Rental;
import edu.ucam.pojos.User;
import edu.ucam.pojos.Videogame;
import edu.ucam.pojos.VideogameCategory;
import edu.ucam.pojos.VideogameImage;
import edu.ucam.servlets.Controller;

/**
 * Servlet implementation class DeleteVideogame
 */
@WebServlet({"/DELETE", "/Delete", "/delete"})
public class Delete extends HttpServlet {
	
	private String url = null;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(url);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		url = request.getHeader("referer");
		ErrorType et = ErrorType.NO_ERROR;
		
		String objectClass = request.getParameter(Controller.ATR_OBJECT_CLASS);		
		System.out.println(objectClass);
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
		
		default:
			break;
		
		}
		
		if(et != ErrorType.NO_ERROR)
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + et;
		
		doGet(request, response);
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
		
		if(idCategory != null) 
			return (new CategoryDAO()).delete(idCategory, SearchBy.ID);
		
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
		
		if(idVideogame != null) 
			return (new VideogameDAO()).delete(idVideogame, SearchBy.ID);
		
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
