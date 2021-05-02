package edu.ucam.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import edu.ucam.database.DatabaseController;
import edu.ucam.enums.ErrorType;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public Listener() {
        // TODO Auto-generated constructor stub
    }


	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {   	
		ErrorType et = DatabaseController.connect();
		if(et != ErrorType.NO_ERROR) System.out.println(et);
    }

	

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0)  { 
    	ErrorType et;
    	if(DatabaseController.DATABASE_CONNECTION == null) {
    		et = DatabaseController.connect();
    		System.out.println(et);
    	}
    }


	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
