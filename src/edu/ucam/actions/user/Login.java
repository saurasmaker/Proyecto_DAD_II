package edu.ucam.actions.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.UserDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IAction;
import edu.ucam.pojos.User;

public class Login implements IAction{
	
	public static final String ATR_ACTION = "ATR_LOGIN_ACTION";

	private static String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User userToCheck = new User();
		userToCheck.setUsername(request.getParameter(User.ATR_USER_USERNAME));
		userToCheck.setPassword(request.getParameter(User.ATR_USER_PASSWORD));
		
		User userFinded = null; 		
		Pattern pattern = Pattern.compile(emailPattern);
		if (userToCheck.getUsername() != null) {
			UserDAO dao = new UserDAO();
		    Matcher matcher = pattern.matcher(userToCheck.getUsername());
		    if (matcher.matches()) 
		    	userFinded = dao.read(userToCheck.getUsername(), SearchBy.EMAIL);
		    else
		    	userFinded = dao.read(userToCheck.getUsername(), SearchBy.USERNAME);
		}
		else {
			return "/mod/error.jsp?ERROR_TYPE="+ErrorType.LOGIN_ERROR;
		}
				
		if(userFinded != null) {
			if(userToCheck.getPassword().equals(userFinded.getPassword())) {
				request.getSession().setAttribute(User.ATR_USER_LOGGED, userFinded);
				return "/index.jsp";
			}
			else
				return "/mod/error.jsp?ERROR_TYPE="+ErrorType.PASSWORD_DONT_MATCHES;
		}
		else {
			return "/mod/error.jsp?ERROR_TYPE="+ErrorType.USER_DONT_EXISTS;
		}
		
	}
	
	
}
