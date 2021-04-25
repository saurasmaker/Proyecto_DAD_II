package edu.ucam.actions.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.interfaces.IAction;

public class Read  implements IAction{
	
	public static final String ATR_ACTION = "ATR_READ_ACTION";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return null;
	}
}
