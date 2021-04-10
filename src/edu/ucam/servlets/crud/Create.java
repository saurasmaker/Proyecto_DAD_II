package edu.ucam.servlets.crud;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import edu.ucam.daos.AssessmentDAO;
import edu.ucam.daos.BillDAO;
import edu.ucam.daos.CategoryDAO;
import edu.ucam.daos.PurchaseDAO;
import edu.ucam.daos.RentalDAO;
import edu.ucam.daos.UserDAO;
import edu.ucam.daos.VideogameDAO;
import edu.ucam.daos.VideogameImageDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.daos.VideogameCategoryDAO;
import edu.ucam.pojos.*;
import edu.ucam.servlets.Controller;

/**
 * Servlet implementation class CreateVideogame
 */
@WebServlet({"/CREATE", "/Create", "/create"})
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String url = "";
	
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
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		url = request.getHeader("referer");
		
		String objectClass = request.getParameter(Controller.ATR_OBJECT_CLASS);		
		
		System.out.println(objectClass);
		
		
		if(objectClass != null)
		switch(objectClass) {
		
		case "edu.ucam.pojos.Assessment":
			createAssessment(request);
			url += "#assessments-title";
			break;
		
		case "edu.ucam.pojos.Bill":
			createBill(request);
			url += "#bills-title";
			break;
			
		case "edu.ucam.pojos.Category":
			createCategory(request);
			url += "#categories-title";
			break;
			
		case "edu.ucam.pojos.Purchase":
			createPurchase(request);
			url += "#purchases-title";
			break;
			
		case "edu.ucam.pojos.Rental":
			createRental(request);
			url += "#rentals-title";
			break;
			
		case "edu.ucam.pojos.User":
			createUser(request);
			url += "#users-title";
			break;
					
		case "edu.ucam.pojos.Videogame":
			createVideogame(request);
			url += "#videogames-title";
			break;
			
		case "edu.ucam.pojos.VideogameImage":
			createVideogamesImages(request);
			url += "#videogames-title";
			break;	
		
		case "edu.ucam.pojos.VideogameCategory":
			createVideogamesCategories(request);
			url += "#videogames-title";
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
		
		try {
			Assessment newAssessment = new Assessment();
			newAssessment.setValue(Integer.parseInt(request.getParameter(Assessment.ATR_ASSESSMENT_VALUE)));
			newAssessment.setSubject(request.getParameter(Assessment.ATR_ASSESSMENT_SUBJECT));
			newAssessment.setComment(request.getParameter(Assessment.ATR_ASSESSMENT_COMMENT));
			newAssessment.setPublicationDate(Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_PUBLICATIONDATE)));
			newAssessment.setEditDate(Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_EDITDATE)));
			newAssessment.setVideogameId(request.getParameter(Assessment.ATR_ASSESSMENT_VIDEOGAMEID));
			newAssessment.setUserId(request.getParameter(Assessment.ATR_ASSESSMENT_USERID));
			
			if(newAssessment.getSubject() != null && newAssessment.getComment() != null && newAssessment.getPublicationDate() != null && 
					newAssessment.getEditDate() != null && newAssessment.getVideogameId() != null && newAssessment.getUserId() != null)
				(new AssessmentDAO()).create(newAssessment);
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_ASSESSMENT_ERROR;
		}
	}
	
	
	
	private void createBill(HttpServletRequest request) {
		
		try {
			Bill newBill = new Bill();
			newBill.setUserId(request.getParameter(Bill.ATR_BILL_USERID));
			newBill.setPurchaseDate(Date.valueOf(request.getParameter(Bill.ATR_BILL_PURCHASEDATE)));
			if(request.getParameter(Bill.ATR_BILL_PURCHASEDATE).contentEquals("on")) newBill.setPaid(true);
			else newBill.setPaid(false);
			
			
			if(newBill.getUserId() != null && newBill.getPurchaseDate() != null) 
				(new BillDAO()).create(newBill);	
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_BILL_ERROR;
		}
	}
	
	
	
	private void createCategory(HttpServletRequest request) {
		
		try {
			Category newCategory = new Category(request.getParameter(Category.ATR_CATEGORY_NAME), request.getParameter(Category.ATR_CATEGORY_DESCRIPTION));
			
			if(newCategory.getName() != null && newCategory.getDescription() != null) 
				(new CategoryDAO()).create(newCategory);		
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_CATEGORY_ERROR;
		}
	}

	
	
	private void createPurchase(HttpServletRequest request) {
		
		try {
			Purchase newPurchase = new Purchase();
			newPurchase.setAmount(Integer.parseInt(request.getParameter(Purchase.ATR_PURCHASE_AMOUNT)));
			newPurchase.setBillId(request.getParameter(Purchase.ATR_PURCHASE_BILLID));
			newPurchase.setVideogameId(request.getParameter(Purchase.ATR_PURCHASE_VIDEOGAMEID));
	
			if(newPurchase.getBillId() != null && newPurchase.getVideogameId() != null)
				(new PurchaseDAO()).create(newPurchase);
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_PURCHASE_ERROR;
		}
	}
	
	
	
	private void createRental(HttpServletRequest request) {
		
		try {
			Rental newRental = new Rental();
			newRental.setUserId(request.getParameter(Rental.ATR_RENTAL_USERID));
			newRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
			newRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
			newRental.setStartDate(Date.valueOf(request.getParameter(Rental.ATR_RENTAL_STARTDATE)));
			newRental.setEndDate(Date.valueOf(request.getParameter(Rental.ATR_RENTAL_ENDDATE)));
			newRental.setReturned(Boolean.valueOf(request.getParameter(Rental.ATR_RENTAL_RETURNED)));
	
			if(newRental.getUserId() != null && newRental.getVideogameId() != null) {
				(new RentalDAO()).create(newRental);
			}
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_RENTAL_ERROR;
		}
	}
	
	
	
	private void createUser(HttpServletRequest request) {
		
		try {
			User newUser = new User(request.getParameter(User.ATR_USER_USERNAME), request.getParameter(User.ATR_USER_EMAIL), request.getParameter(User.ATR_USER_PASSWORD));
			
			String buffer = request.getParameter(User.ATR_USER_SIGNUPDATE);
			if(buffer!=null) newUser.setSignUpDate(Timestamp.valueOf(buffer.replace("T"," ")));
			buffer = request.getParameter(User.ATR_USER_LASTSIGNIN);
			if(buffer != null)newUser.setLastSignIn(Timestamp.valueOf(buffer.replace("T"," ")));
			
			if(newUser.getUsername() != null && newUser.getEmail() != null && newUser.getPassword() != null) 
				(new UserDAO()).create(newUser);
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_USER_ERROR;
		}
			
	}
	
	
	
	private void createVideogame(HttpServletRequest request) {
		
		try {
			Videogame newVideogame = new Videogame(request.getParameter(Videogame.ATR_VIDEOGAME_NAME), request.getParameter(Videogame.ATR_VIDEOGAME_DESCRIPTION),
					Date.valueOf(request.getParameter(Videogame.ATR_VIDEOGAME_RELEASEDATE)), Integer.parseInt(request.getParameter(Videogame.ATR_VIDEOGAME_STOCK)));
			newVideogame.setPurchasePrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_PURCHASEPRICE)));
			newVideogame.setRentalPrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_RENTALPRICE)));
			
			if(newVideogame.getName() != null && newVideogame.getDescription() != null && newVideogame.getReleaseDate() != null)
				(new VideogameDAO()).create(newVideogame);
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_VIDEOGAME_ERROR;
		}
	}
	
	
	private void createVideogamesImages(HttpServletRequest request) {

		try {
			if(ServletFileUpload.isMultipartContent(request)) System.out.println("Me cago en todo");;
			VideogameImage newVideogameImage = new VideogameImage();
			newVideogameImage.setName(request.getParameter(VideogameImage.ATR_VIDEOGAMEIMAGE_NAME));
			newVideogameImage.setVideogameId(request.getParameter(VideogameImage.ATR_VIDEOGAMEIMAGE_VIDEOGAMEID));
			Part filePart = null;
			InputStream inputStream = null;
				filePart = request.getPart(VideogameImage.ATR_VIDEOGAMEIMAGE_IMAGE);
				inputStream = filePart.getInputStream();
				newVideogameImage.setImage((Blob)inputStream);
	
			
			System.out.println(filePart.getContentType());
			
			
			if(newVideogameImage.getName() != null && newVideogameImage.getVideogameId() != null && newVideogameImage.getImage() != null)
				(new VideogameImageDAO()).create(newVideogameImage);
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_VIDEOGAME_IMAGE_ERROR;
		}
	}
	
	
	private void createVideogamesCategories(HttpServletRequest request) {
		
		try {
			VideogameCategory newVideogamesCategories = new VideogameCategory(request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID),
					request.getParameter(VideogameCategory.ATR_VIDEOGAMESCATEGORIES_CATEGORYID));
			
			if(newVideogamesCategories.getCategoryId() != null && newVideogamesCategories.getVideogameId() != null)
				(new VideogameCategoryDAO()).create(newVideogamesCategories);
		
		}catch(Exception e) {
			e.printStackTrace();
			url = request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_VIDEOGAME_CATEGORY_ERROR;
		}
	}
	
}
