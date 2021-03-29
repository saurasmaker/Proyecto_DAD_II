package edu.ucam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.UserDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchUserBy;
import edu.ucam.pojos.User;

/**
 * Servlet implementation class Register
 */
@WebServlet({"/Signup", "/signup"})
public class Signup extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
	private static String url = "/index.jsp";
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		user.setUsername(request.getParameter(User.ATR_USER_USERNAME));
		user.setEmail(request.getParameter(User.ATR_USER_EMAIL));
		user.setPassword(request.getParameter(User.ATR_USER_PASSWORD));
		
		ErrorType errorType = UserDAO.create(user);
		if(errorType != ErrorType.NO_ERROR) {		
			url = "/mod/error.jsp?ERROR_TYPE=" + errorType;
		}
		else {
			user = UserDAO.read(user.getUsername(), SearchUserBy.USERNAME);
			request.getSession().setAttribute(User.ATR_USER, user);
			url = "/index.jsp";
		}
		
		doGet(request, response);
	}
	
	

}
