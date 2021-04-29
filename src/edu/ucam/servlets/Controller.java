package edu.ucam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.actions.admin.*;
import edu.ucam.actions.user.*;
import edu.ucam.pojos.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet({"/Controller","/controller", "/CONTROLLER"})
@MultipartConfig
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public static final String ATR_OBJECT_CLASS = "ATR_OBJECT_CLASS", ATR_SELECT_ACTION = "ATR_SELECT_ACTION";

	private String url;


    public Controller() {
        super();
    }

 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.sendRedirect(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		url = request.getContextPath();
		User user = (User) request.getSession().getAttribute(User.ATR_USER_LOGGED);
		String selectedAction = request.getParameter(ATR_SELECT_ACTION);
				
		/*
		 * For no logged Users
		 */
		switch(selectedAction) {
		
		case Login.ATR_ACTION:
			url += (new Login()).execute(request, response);
			break;
			
		case Signup.ATR_ACTION:
			url += (new Signup()).execute(request, response);
			break;
			
		}
		
		
		/*
		 * For logged Users
		 */
		if(user!=null) {
			switch(selectedAction) {
			
			case Buy.ATR_ACTION:
				url += (new Buy()).execute(request, response);
				break;
				
			case EditProfile.ATR_ACTION:
				url += (new EditProfile()).execute(request, response);
				break;
				
			case Logout.ATR_ACTION:
				url += (new Logout()).execute(request, response);
				break;
				
			case MakeAssessment.ATR_ACTION:
				url += (new MakeAssessment()).execute(request, response);
				break;
				
			case Rent.ATR_ACTION:
				url += (new Rent()).execute(request, response);
				break;
				
			case PayBill.ATR_ACTION:
				url += (new PayBill()).execute(request, response);
				break;
				
			case ReturnRental.ATR_ACTION:
				url += (new ReturnRental()).execute(request, response);
				break;
			
			}
			
			
			/*
			 * For admin Users
			 */
			if(user.getIsAdmin())
				switch(selectedAction) {
					
				case Create.ATR_ACTION:
					url += (new Create()).execute(request, response);
					break;
						
				case Read.ATR_ACTION:
					url += (new Read()).execute(request, response);
					break;
					
				case Update.ATR_ACTION:
					url += (new Update()).execute(request, response);
					break;
					
				case Delete.ATR_ACTION:
					url += (new Delete()).execute(request, response);
					break;
						
				}
		
		}
		
		doGet(request, response);
	}

}
