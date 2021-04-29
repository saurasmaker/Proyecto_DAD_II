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
import edu.ucam.daos.PurchaseDAO;
import edu.ucam.daos.RentalDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.interfaces.IAction;
import edu.ucam.pojos.Basket;
import edu.ucam.pojos.Bill;
import edu.ucam.pojos.Item;
import edu.ucam.pojos.Purchase;
import edu.ucam.pojos.Rental;
import edu.ucam.pojos.User;

public class CreateBill implements IAction{

	public static final String ATR_ACTION = "ATR_CREATEBILL_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			Basket basket = (Basket) request.getSession().getAttribute(Basket.ATR_BASKET);
			User user = (User) request.getSession().getAttribute(User.ATR_USER_LOGGED);
			Bill bill = new Bill();
			
			if(basket.getProducts().size() == 0)
				return "/mod/error.jsp?ERROR_TYPE='La cesta no puede estar vacía a la hora de realizar la factura.'";
			if(user == null)
				return "/mod/error.jsp?ERROR_TYPE='Debes estar autenticado para poder realizar la compra de tus productos de la cesta.'";
			
			
			bill.setBillingDate(Date.valueOf(LocalDate.now()));
			bill.setBillingTime(Time.valueOf(LocalTime.now()));
			bill.setPaid(false);
			bill.setUserId(user.getId());
			
			bill = (new BillDAO()).createAndGet(bill);
			
			if(bill != null)
			for(int i = 0; i < basket.getProducts().size(); ++i) {
				Item item = basket.getProductByPosition(i);
				if(item.getAmount()==-1) { //It is a rental
					Rental rental = new Rental();
					
					rental.setBillId(bill.getId());
					rental.setStartDate(Date.valueOf(LocalDate.now()));
					rental.setStartTime(Time.valueOf(LocalTime.now()));
					rental.setReturned(false);
					rental.setVideogameId(item.getVideogameId());
					
					(new RentalDAO()).create(rental);
				} 
				else if(item.getAmount() > 0) { //It is a purchase
					Purchase purchase = new Purchase();

					purchase.setAmount(item.getAmount());
					purchase.setVideogameId(item.getVideogameId());
					purchase.setBillId(bill.getId());
					
					(new PurchaseDAO()).create(purchase);
				}
			}
			
			request.getSession().setAttribute(Basket.ATR_BASKET, new Basket());
			
			return "/user/user_bills.jsp";
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_BILL_ERROR;
	}
	

}
