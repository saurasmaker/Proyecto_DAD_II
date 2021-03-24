package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.cj.xdevapi.Result;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Factory factory = null;
	
	@Resource (name = "jdbc/infodeo2")
	private static DataSource ds;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
    public void init() throws ServletException {
    	System.out.println("Inicializando...");
        
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	
    	try {
    		Connection con = Controller.ds.getConnection();
    		ps = con.prepareStatement("SELECT * FROM ACTIONS");
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			System.out.println(rs.getString("id"));
    		}
    		
    		
    	}catch(Exception t) {
    		
    	}
    	finally {
			try {
				if(rs != null) rs.close();
	    		if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
