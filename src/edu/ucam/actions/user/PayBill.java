package edu.ucam.actions.user;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.BillDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.pojos.Bill;
import edu.ucam.interfaces.IAction;

public class PayBill implements IAction{
	
	public static final String ATR_ACTION = "ATR_PAYBILL_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Bill thisBill = (new BillDAO()).read(request.getParameter(Bill.ATR_BILL_ID), SearchBy.ID);
			thisBill.setPaid(true);
			thisBill.setPaidDate(Date.valueOf(LocalDate.now()));
			thisBill.setPaidTime(Time.valueOf(LocalTime.now()));			
			(new BillDAO()).update(thisBill.getId(), SearchBy.ID, thisBill);	
		} catch(Exception e) {
			return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.PAYING_BILL_ERROR;
		}

		return "/user/user_profile.jsp";
	}

}
