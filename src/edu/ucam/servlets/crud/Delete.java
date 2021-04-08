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
import edu.ucam.servlets.Controller;

/**
 * Servlet implementation class DeleteVideogame
 */
@WebServlet({"/DELETE", "/Delete", "/delete"})
public class Delete extends HttpServlet {
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
		System.out.println("hola");
		response.sendRedirect(request.getHeader("referer"));	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String objectClass = request.getParameter(Controller.ATR_OBJECT_CLASS);		
		System.out.println(objectClass);
		if(objectClass != null)
		switch(objectClass) {
		
		case "edu.ucam.pojos.Assessment":
			deleteAssessment(request.getParameter(Assessment.ATR_ASSESSMENT_ID));
			break;
		
		case "edu.ucam.pojos.Bill":
			deleteBill(request.getParameter(Bill.ATR_BILL_ID));
			break;
			
		case "edu.ucam.pojos.Category":
			deleteCategory(request.getParameter(Category.ATR_CATEGORY_ID));
			break;
			
		case "edu.ucam.pojos.Purchase":
			deletePurchase(request.getParameter(Purchase.ATR_PURCHASE_ID));
			break;
			
		case "edu.ucam.pojos.Rental":
			deleteRental(request.getParameter(Rental.ATR_RENTAL_ID));
			break;
			
		case "edu.ucam.pojos.User":
			deleteUser(request.getParameter(User.ATR_USER_ID));
			break;
					
		case "edu.ucam.pojos.Videogame":
			deleteVideogame(request.getParameter(Videogame.ATR_VIDEOGAME_ID));
			break;
			
		case "edu.ucam.pojos.VideogameCategory":
			deleteVideogamesCategories(request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_ID));
			break;
		
		default:
			break;
		
		}
		doGet(request, response);
	}
	
	
	private ErrorType deleteAssessment(String idAssessment) {
		if(idAssessment != null) {
			AssessmentDAO assessmentDao = new AssessmentDAO();
			return assessmentDao.delete(idAssessment, SearchBy.ID);
		}
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteBill(String idBill) {
		if(idBill != null) {
			BillDAO billDao = new BillDAO();
			return billDao.delete(idBill, SearchBy.ID);
		}
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteCategory(String idCategory) {
		if(idCategory != null) {
			CategoryDAO categoryDao = new CategoryDAO();
			return categoryDao.delete(idCategory, SearchBy.ID);
		}
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deletePurchase(String idPurchase) {
		if(idPurchase != null) {
			PurchaseDAO purchaseDao = new PurchaseDAO();
			return purchaseDao.delete(idPurchase, SearchBy.ID);
		}
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteRental(String idRental) {
		if(idRental != null) {
			RentalDAO rentalDao = new RentalDAO();
			return rentalDao.delete(idRental, SearchBy.ID);
		}
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteUser(String idUser) {
		if(idUser != null) {
			UserDAO userDao = new UserDAO();
			return userDao.delete(idUser, SearchBy.ID);
		}
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteVideogame(String idVideogame) {
		if(idVideogame != null) {
			VideogameDAO videogameDao = new VideogameDAO();
			return videogameDao.delete(idVideogame, SearchBy.ID);
		}
		return ErrorType.PARAMETER_NULL;
	}
	
	private ErrorType deleteVideogamesCategories(String idVideogamesCategories) {
		if(idVideogamesCategories != null) {
			VideogameCategoryDAO videogamesCategoriesDao = new VideogameCategoryDAO();
			return videogamesCategoriesDao.delete(idVideogamesCategories, SearchBy.ID);
		}
		return ErrorType.PARAMETER_NULL;
	}
}
