package edu.ucam.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Servlet implementation class Login
 */
@WebServlet({"/Login", "/login"})
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private static String url = "/index.jsp";
	private static String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
    
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
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
		
		User userToCheck = new User();
		userToCheck.setUsername(request.getParameter(User.ATR_USER_USERNAME));
		userToCheck.setPassword(request.getParameter(User.ATR_USER_PASSWORD));
		
		User userFinded = null; 		
		Pattern pattern = Pattern.compile(emailPattern);
		if (userToCheck.getUsername() != null) {
		     Matcher matcher = pattern.matcher(userToCheck.getUsername());
		     if (matcher.matches()) 
		    	 userFinded = UserDAO.read(userToCheck.getUsername(), SearchUserBy.EMAIL);
		     else
		    	 userFinded = UserDAO.read(userToCheck.getUsername(), SearchUserBy.USERNAME);
		}
		else {
			url = "/mod/error.jsp?ERROR_TYPE="+ErrorType.LOGIN_ERROR;
		}
		
		if(userFinded != null) {
			if(userToCheck.getPassword().contentEquals(userFinded.getPassword())) {
				request.getSession().setAttribute(User.ATR_USER, userFinded);
				url = "/index.jsp";
			}
			else
				url = "/mod/error.jsp?ERROR_TYPE="+ErrorType.PASSWORD_DONT_MATCHES;
		}
		else {
			url = "/mod/error.jsp?ERROR_TYPE="+ErrorType.USER_DONT_EXISTS;
		}
		
		doGet(request, response);
	}

}
