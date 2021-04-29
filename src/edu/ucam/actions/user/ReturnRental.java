package edu.ucam.actions.user;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.RentalDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IAction;
import edu.ucam.pojos.Rental;

public class ReturnRental implements IAction{
	public static final String ATR_ACTION = "ATR_RETURNRENTAL_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Rental thisRental = (new RentalDAO()).read(request.getParameter(Rental.ATR_RENTAL_ID), SearchBy.ID);
			thisRental.setReturned(true);
			thisRental.setEndDate(Date.valueOf(LocalDate.now()));
			thisRental.setEndTime(Time.valueOf(LocalTime.now()));			
			(new RentalDAO()).update(thisRental.getId(), SearchBy.ID, thisRental);	
		} catch(Exception e) {
			return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.RETURNING_RENTAL_ERROR;
		}

		return "/user/user_bills.jsp";
	}

}
