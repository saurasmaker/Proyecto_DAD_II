package edu.ucam.actions.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.interfaces.IAction;

public class Rent implements IAction{
	public static final String ATR_ACTION = "ATR_RENT_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return null;
	}
}
