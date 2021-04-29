package edu.ucam.actions.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.enums.ErrorType;
import edu.ucam.interfaces.IAction;

public class SearchProduct implements IAction{
	public static final String ATR_ACTION = "ATR_SEARCHPRODUCT_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		try {
			str = request.getParameter("SEARCH_VIDEOGAME_BY_STRING").toLowerCase();
		} catch(Exception e) {
			return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.SEARCH_VIDEOGAME_BY_STRING_ERROR;
		}

		
		if(str != null) return "/index.jsp?SEARCH_VIDEOGAME_BY_STRING="+str;
		else  return "/index.jsp";
	}
}
