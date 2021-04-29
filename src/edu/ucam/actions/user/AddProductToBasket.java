package edu.ucam.actions.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ucam.enums.ErrorType;
import edu.ucam.interfaces.IAction;
import edu.ucam.pojos.Basket;

public class AddProductToBasket implements IAction{

	public static final String ATR_ACTION = "ATR_ADDPRODUCTTOBASKET_ACTION";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		try {
			HttpSession session = request.getSession();
			Basket basket = (Basket) session.getAttribute(Basket.ATR_BASKET);
			
			String strAmount = request.getParameter(Basket.ATR_BASKET_AMOUNT);
			int intAmount = 0;
			if(strAmount != null) intAmount = Integer.parseInt(strAmount);
			
			basket.addProduct(request.getParameter(Basket.ATR_BASKET_PRODUCTID), intAmount);
			
			session.setAttribute(Basket.ATR_BASKET, basket);
			
			return "/index.jsp";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.UPDATE_USER_ERROR;
		
	}

}

