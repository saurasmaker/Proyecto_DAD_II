package edu.ucam.actions.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.interfaces.IAction;
import edu.ucam.pojos.User;

public class Logout implements IAction{

	public static final String ATR_ACTION = "ATR_LOGOUT_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute(User.ATR_USER_LOGGED);
		
		return "";
	}
	
}
