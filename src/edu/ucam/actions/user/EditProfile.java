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

public class EditProfile implements IAction{
	public static final String ATR_ACTION = "ATR_EDITPROFILE_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDAO userDao = new UserDAO();
		
		try {
			User updateUser = (User)request.getSession().getAttribute(User.ATR_USER_LOGGED);
			
			updateUser.setUsername(request.getParameter(User.ATR_USER_USERNAME));
			updateUser.setEmail(request.getParameter(User.ATR_USER_EMAIL));
			updateUser.setPassword(request.getParameter(User.ATR_USER_PASSWORD));
			
			userDao.update(updateUser.getId(), SearchBy.ID, updateUser);
			return "/user/user_profile.jsp";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_USER_ERROR;
	}
}
