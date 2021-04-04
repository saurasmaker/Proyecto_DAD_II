package edu.ucam.servlets.crud;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.AssessmentDAO;
import edu.ucam.daos.BillDAO;
import edu.ucam.daos.CategoryDAO;
import edu.ucam.daos.PurchaseDAO;
import edu.ucam.daos.RentalDAO;
import edu.ucam.daos.UserDAO;
import edu.ucam.daos.VideogameDAO;
import edu.ucam.daos.VideogamesCategoriesDAO;
import edu.ucam.pojos.*;
import edu.ucam.servlets.Controller;

/**
 * Servlet implementation class CreateVideogame
 */
@WebServlet({"/CREATE", "/Create", "/create"})
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
		
		request.getRequestDispatcher(request.getHeader("referer")).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String objectClass = request.getParameter(Controller.ATR_OBJECT_CLASS);		
		
		if(objectClass != null)
		switch(objectClass) {
		
		case "edu.ucam.pojos.Assessment":
			createAssessment(request);
			break;
		
		case "edu.ucam.pojos.Bill":
			createBill(request);
			break;
			
		case "edu.ucam.pojos.Category":
			createCategory(request);
			break;
			
		case "edu.ucam.pojos.Purchase":
			createPurchase(request);
			break;
			
		case "edu.ucam.pojos.Rental":
			createRental(request);
			break;
			
		case "edu.ucam.pojos.User":
			createUser(request);
			break;
					
		case "edu.ucam.pojos.Videogame":
			createVideogame(request);
			break;
			
		case "edu.ucam.pojos.VideogamesCategories":
			createVideogamesCategories(request);
			break;
		
		default:
			break;
		
		}
		
		doGet(request, response);
	}
	
	
	
	/*
	 * "Create" Methods
	 */
	private void createAssessment(HttpServletRequest request) {
		
		Assessment newAssessment = new Assessment();
		newAssessment.setValue(Integer.parseInt(request.getParameter(Assessment.ATR_ASSESSMENT_VALUE)));
		newAssessment.setSubject(request.getParameter(Assessment.ATR_ASSESSMENT_SUBJECT));
		newAssessment.setComment(request.getParameter(Assessment.ATR_ASSESSMENT_COMMENT));
		newAssessment.setPublicationDate(Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_PUBLICATIONDATE)));
		newAssessment.setEditDate(Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_EDITDATE)));
		newAssessment.setVideogameId(request.getParameter(Assessment.ATR_ASSESSMENT_VIDEOGAMEID));
		newAssessment.setUserId(request.getParameter(Assessment.ATR_ASSESSMENT_USERID));
		
		if(newAssessment.getSubject() != null && newAssessment.getComment() != null && newAssessment.getPublicationDate() != null && 
				newAssessment.getEditDate() != null && newAssessment.getVideogameId() != null && newAssessment.getUserId() != null) {
			AssessmentDAO assessmentDao = new AssessmentDAO();
			assessmentDao.create(newAssessment);
		}
	}
	
	
	
	private void createBill(HttpServletRequest request) {
		
		Bill newBill = new Bill();
		newBill.setUserId(request.getParameter(Bill.ATR_BILL_USERID));
		newBill.setPurchaseDate(Date.valueOf(request.getParameter(Bill.ATR_BILL_PURCHASEDATE)));
		if(request.getParameter(Bill.ATR_BILL_PURCHASEDATE).contentEquals("on")) newBill.setPaid(true);
		else newBill.setPaid(false);
		
		
		if(newBill.getUserId() != null && newBill.getPurchaseDate() != null) {
			BillDAO billDao = new BillDAO();
			billDao.create(newBill);
		}
	}
	
	
	
	private void createCategory(HttpServletRequest request) {
		Category newCategory = new Category(request.getParameter(Category.ATR_CATEGORY_NAME), request.getParameter(Category.ATR_CATEGORY_DESCRIPTION));
		
		if(newCategory.getName() != null && newCategory.getDescription() != null) {
			CategoryDAO categoryDao = new CategoryDAO();
			categoryDao.create(newCategory);
		}
	}

	
	
	private void createPurchase(HttpServletRequest request) {
		Purchase newPurchase = new Purchase();
		newPurchase.setAmount(Integer.parseInt(request.getParameter(Purchase.ATR_PURCHASE_AMOUNT)));
		newPurchase.setBillId(request.getParameter(Purchase.ATR_PURCHASE_BILLID));
		newPurchase.setVideogameId(request.getParameter(Purchase.ATR_PURCHASE_VIDEOGAMEID));

		if(newPurchase.getBillId() != null && newPurchase.getVideogameId() != null) {
			PurchaseDAO purchaseDao = new PurchaseDAO();
			purchaseDao.create(newPurchase);
		}
		
	}
	
	
	
	private void createRental(HttpServletRequest request) {
		Rental newRental = new Rental();
		newRental.setUserId(request.getParameter(Rental.ATR_RENTAL_USERID));
		newRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
		newRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
		newRental.setStartDate(Date.valueOf(request.getParameter(Rental.ATR_RENTAL_STARTDATE)));
		newRental.setEndDate(Date.valueOf(request.getParameter(Rental.ATR_RENTAL_ENDDATE)));
		newRental.setReturned(Boolean.valueOf(request.getParameter(Rental.ATR_RENTAL_RETURNED)));

		if(newRental.getUserId() != null && newRental.getVideogameId() != null) {
			RentalDAO rentalDao = new RentalDAO();
			rentalDao.create(newRental);
		}
	}
	
	
	
	private void createUser(HttpServletRequest request) {
		
		User newUser = new User(request.getParameter(User.ATR_USER_USERNAME), request.getParameter(User.ATR_USER_EMAIL), request.getParameter(User.ATR_USER_PASSWORD));
		if(newUser.getUsername() != null && newUser.getEmail() != null && newUser.getPassword() != null) {
			UserDAO userDao = new UserDAO();
			userDao.create(newUser);
		}
			
	}
	
	
	
	private void createVideogame(HttpServletRequest request) {
		
		Videogame newVideogame = new Videogame(request.getParameter(Videogame.ATR_VIDEOGAME_NAME), request.getParameter(Videogame.ATR_VIDEOGAME_DESCRIPTION),
				Date.valueOf(request.getParameter(Videogame.ATR_VIDEOGAME_RELEASEDATE)), Integer.parseInt(request.getParameter(Videogame.ATR_VIDEOGAME_STOCK)));
		newVideogame.setPurchasePrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_PURCHASEPRICE)));
		newVideogame.setRentalPrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_PURCHASEPRICE)));
		
		if(newVideogame.getName() != null && newVideogame.getDescription() != null && newVideogame.getReleaseDate() != null) {
			VideogameDAO videogameDao = new VideogameDAO();
			videogameDao.create(newVideogame);
		}
		
	}
	
	
	
	private void createVideogamesCategories(HttpServletRequest request) {
		
		VideogamesCategories newVideogamesCategories = new VideogamesCategories(request.getParameter(VideogamesCategories.ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID),
				request.getParameter(VideogamesCategories.ATR_VIDEOGAMESCATEGORIES_CATEGORYID));
		
		if(newVideogamesCategories.getCategoryId() != null && newVideogamesCategories.getVideogameId() != null) {
			VideogamesCategoriesDAO videogamesCategoriesDao = new VideogamesCategoriesDAO();
			videogamesCategoriesDao.create(newVideogamesCategories);
		}
		
	}
}
