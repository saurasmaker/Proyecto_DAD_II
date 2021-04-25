package edu.ucam.actions.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import edu.ucam.daos.AssessmentDAO;
import edu.ucam.daos.BillDAO;
import edu.ucam.daos.CategoryDAO;
import edu.ucam.daos.PurchaseDAO;
import edu.ucam.daos.RentalDAO;
import edu.ucam.daos.UserDAO;
import edu.ucam.daos.VideogameCategoryDAO;
import edu.ucam.daos.VideogameDAO;
import edu.ucam.daos.VideogameImageDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.interfaces.IAction;
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
		
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.SELECTED_OBTION_DOES_NOT_EXISTS;
	}
	
	
	
	/*
	 * "Create" Methods
	 */
	private String createAssessment(HttpServletRequest request) {
				
		try {
			Assessment newAssessment = new Assessment();
			newAssessment.setValue(Integer.parseInt(request.getParameter(Assessment.ATR_ASSESSMENT_VALUE)));
			newAssessment.setSubject(request.getParameter(Assessment.ATR_ASSESSMENT_SUBJECT));
			newAssessment.setComment(request.getParameter(Assessment.ATR_ASSESSMENT_COMMENT));
			newAssessment.setPublicationDate(Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_PUBLICATIONDATE)));
			newAssessment.setPublicationTime(Time.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_PUBLICATIONTIME)));
			newAssessment.setEditDate(Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_EDITDATE)));
			newAssessment.setEditTime(Time.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_EDITTIME)));
			newAssessment.setVideogameId(request.getParameter(Assessment.ATR_ASSESSMENT_VIDEOGAMEID));
			newAssessment.setUserId(request.getParameter(Assessment.ATR_ASSESSMENT_USERID));
			
			if(newAssessment.getSubject() != null && newAssessment.getComment() != null && newAssessment.getPublicationDate() != null && 
					newAssessment.getEditDate() != null && newAssessment.getVideogameId() != null && newAssessment.getUserId() != null) {
				(new AssessmentDAO()).create(newAssessment);
				return "/secured/admin_page.jsp#assessments-title";
			}
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_ASSESSMENT_ERROR;
	}
	
	
	
	private String createBill(HttpServletRequest request) {
				
		try {
			Bill newBill = new Bill();
			newBill.setUserId(request.getParameter(Bill.ATR_BILL_USERID));
			newBill.setBillingDate(Date.valueOf(request.getParameter(Bill.ATR_BILL_BILLINGDATE)));
			newBill.setBillingTime(Time.valueOf(request.getParameter(Bill.ATR_BILL_BILLINGTIME)));
			if(request.getParameter(Bill.ATR_BILL_PAID) != null) newBill.setPaid(true);
			else newBill.setPaid(false);
			newBill.setPaidDate(Date.valueOf(request.getParameter(Bill.ATR_BILL_PAIDDATE)));
			newBill.setPaidTime(Time.valueOf(request.getParameter(Bill.ATR_BILL_PAIDTIME)));
			
			if(newBill.getUserId() != null && newBill.getBillingDate() != null) { 
				(new BillDAO()).create(newBill);	
				return "/secured/admin_page.jsp#bills-title";
			}
		}catch(Exception e) {
			e.printStackTrace();	
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_BILL_ERROR;
	}
	
	
	
	private String createCategory(HttpServletRequest request) {
		
		try {
			Category newCategory = new Category(request.getParameter(Category.ATR_CATEGORY_NAME), request.getParameter(Category.ATR_CATEGORY_DESCRIPTION));
			
			if(newCategory.getName() != null && newCategory.getDescription() != null) {
				(new CategoryDAO()).create(newCategory);	
				return "/secured/admin_page.jsp#categories-title";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_CATEGORY_ERROR;
	}

	
	
	private String createPurchase(HttpServletRequest request) {
		
		try {
			Purchase newPurchase = new Purchase();
			newPurchase.setAmount(Integer.parseInt(request.getParameter(Purchase.ATR_PURCHASE_AMOUNT)));
			newPurchase.setBillId(request.getParameter(Purchase.ATR_PURCHASE_BILLID));
			newPurchase.setVideogameId(request.getParameter(Purchase.ATR_PURCHASE_VIDEOGAMEID));
	
			if(newPurchase.getBillId() != null && newPurchase.getVideogameId() != null) {
				(new PurchaseDAO()).create(newPurchase);
				return "/secured/admin_page.jsp#purchases-title";
			}
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_PURCHASE_ERROR;
	}
	
	
	
	private String createRental(HttpServletRequest request) {
		
		try {
			Rental newRental = new Rental();
			newRental.setUserId(request.getParameter(Rental.ATR_RENTAL_USERID));
			newRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
			newRental.setVideogameId(request.getParameter(Rental.ATR_RENTAL_VIDEOGAMEID));
			newRental.setStartDate(Timestamp.valueOf(request.getParameter(Rental.ATR_RENTAL_STARTDATE).replace("T"," ")));
			newRental.setEndDate(Timestamp.valueOf(request.getParameter(Rental.ATR_RENTAL_ENDDATE).replace("T"," ")));
			newRental.setReturned(Boolean.valueOf(request.getParameter(Rental.ATR_RENTAL_RETURNED)));
	
			if(newRental.getUserId() != null && newRental.getVideogameId() != null) {
				(new RentalDAO()).create(newRental);
				return "/secured/admin_page.jsp#rentals-title";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_RENTAL_ERROR;
	}
	
	
	
	private String createUser(HttpServletRequest request) {
		
		try {
			User newUser = new User(request.getParameter(User.ATR_USER_USERNAME), request.getParameter(User.ATR_USER_EMAIL), request.getParameter(User.ATR_USER_PASSWORD));
			String date = request.getParameter(User.ATR_USER_SIGNUPDATE);
			
			newUser.setSignUpDate(validateDate(date));
			date = request.getParameter(User.ATR_USER_LASTSIGNIN);
			newUser.setLastSignIn(validateDate(date));
			
			if(newUser.getUsername() != null && newUser.getEmail() != null && newUser.getPassword() != null) {
				(new UserDAO()).create(newUser);
				return "/secured/admin_page.jsp#users-title";
			}
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return request.getContextPath() + "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_USER_ERROR;	
	}
	
	
	
	private String createVideogame(HttpServletRequest request) {
		
		try {
			Videogame newVideogame = new Videogame(request.getParameter(Videogame.ATR_VIDEOGAME_NAME), request.getParameter(Videogame.ATR_VIDEOGAME_DESCRIPTION),
					Date.valueOf(request.getParameter(Videogame.ATR_VIDEOGAME_RELEASEDATE)), Integer.parseInt(request.getParameter(Videogame.ATR_VIDEOGAME_STOCK)));
			newVideogame.setPurchasePrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_PURCHASEPRICE)));
			newVideogame.setRentalPrice(Float.parseFloat(request.getParameter(Videogame.ATR_VIDEOGAME_RENTALPRICE)));
			
			if(newVideogame.getName() != null && newVideogame.getDescription() != null && newVideogame.getReleaseDate() != null) {
				(new VideogameDAO()).create(newVideogame);
				return "/secured/admin_page.jsp#videogames-title";
			}
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
	
	private Timestamp validateDate(String date) {
		
		Pattern simpleDatePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		
		if(date!=null && date.contains("T"))
			return Timestamp.valueOf(date.replace("T"," "));
		else if(simpleDatePattern.matcher(date).matches())
			return (Timestamp.valueOf(date + "T00:00:00:0000"));
		else 
			return Timestamp.valueOf(LocalDateTime.now());
	}
}
