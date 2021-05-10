package edu.ucam.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import edu.ucam.daos.AssessmentDAO;
import edu.ucam.daos.BillDAO;
import edu.ucam.daos.CategoryDAO;
import edu.ucam.daos.PurchaseDAO;
import edu.ucam.daos.RentalDAO;
import edu.ucam.daos.UserDAO;
import edu.ucam.daos.VideogameDAO;
import edu.ucam.database.DatabaseController;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener, ServletRequestListener {

    /**
     * Default constructor. 
     */
    public Listener() {
        // TODO Auto-generated constructor stub
    }


    @Override
    public void requestInitialized(ServletRequestEvent arg0)  { 
    	if(DatabaseController.DATABASE_CONNECTION == null) {
    		System.out.println(DatabaseController.connect());
    		
    		ServletContext sc = arg0.getServletContext();
    		System.out.println(initializeLists(sc));
    	}
    }


	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}
    
	@Override
    public void contextInitialized(ServletContextEvent arg0)  { 
    	if(DatabaseController.DATABASE_CONNECTION == null) {
    		System.out.println(DatabaseController.connect());
    		
    		ServletContext sc = arg0.getServletContext();
    		System.out.println(initializeLists(sc));
    	}
    }


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(DatabaseController.disconnect());
	}
	
	private String initializeLists(ServletContext sc) {
		try {
			sc.setAttribute("usersList", (new UserDAO()).list());
			sc.setAttribute("videogamesList", (new VideogameDAO()).list());
			sc.setAttribute("categoriesList", (new CategoryDAO()).list());
			sc.setAttribute("assessmentsList", (new AssessmentDAO()).list());
			sc.setAttribute("billsList", (new BillDAO()).list());
			sc.setAttribute("purchasesList", (new PurchaseDAO()).list());
			sc.setAttribute("rentalsList", (new RentalDAO()).list());
			
			sc.setAttribute("catalogueVideogamesList", (new VideogameDAO()).list());
			
			return "LISTS INITIALIZATED";
		}catch(Exception t) {
			return "ERROR INITIALIZATING LISTS";
		}
	}

	
}
