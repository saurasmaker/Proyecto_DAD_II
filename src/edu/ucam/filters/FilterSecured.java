package edu.ucam.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ucam.enums.ErrorType;
import edu.ucam.pojos.User;

/**
 * Servlet Filter implementation class FilterSecured
 */
@WebFilter("/secured/*")
public class FilterSecured implements Filter {

	private String url = "/mod/error.jsp";
	
	
    /**
     * Default constructor. 
     */
    public FilterSecured() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpRequest.getSession();
		
		User user = (User) httpSession.getAttribute(User.ATR_USER);
		
		if(user != null && user.getUsername().equals("admin")) {
			url = "/secured/admin_page.jsp";
		}
		else {
			url = "/mod/error.jsp?ERROR_TYPE="+ErrorType.ACCESS_DENIED;
		}		
		
		httpRequest.getRequestDispatcher(url).forward(httpRequest, response);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
