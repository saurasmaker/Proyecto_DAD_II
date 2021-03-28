package edu.ucam.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.database.DatabaseController;
import edu.ucam.pojos.User;

/**
 * Servlet implementation class Register
 */
@WebServlet({"/Signup", "/signup"})
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		user.setUsername(request.getParameter(User.ATR_USER_USERNAME));
		user.setEmail(request.getParameter(User.ATR_USER_EMAIL));
		user.setPassword(request.getParameter(User.ATR_USER_PASSWORD));
		
		
		try {
			
			DatabaseController.connect();
			DatabaseController.executeQuery("INSERT INTO users (username, email, password)" + 
						"VALUES (" + user.getUsername() + ", " + user.getEmail() + ", " + user.getPassword() + ");");
			
		} catch (NullPointerException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			System.out.println(" --- Error when registering user ---");
			
		}

		doGet(request, response);
	}

}
