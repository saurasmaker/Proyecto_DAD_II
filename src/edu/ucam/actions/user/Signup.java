package edu.ucam.actions.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.UserDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IAction;
import edu.ucam.pojos.User;

public class Signup implements IAction{

	public static final String ATR_ACTION = "ATR_SIGNUP_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		UserDAO dao = new UserDAO();
		User user = new User(request);
		
		ErrorType errorType = dao.create(user);
		if(errorType != ErrorType.NO_ERROR) {		
			return "/mod/error.jsp?ERROR_TYPE=" + errorType;
		}
		else {
			user = dao.read(user.getUsername(), SearchBy.USERNAME);
			request.getSession().setAttribute(User.ATR_USER_LOGGED, user);
			return "/index.jsp";
		}
	}
}
