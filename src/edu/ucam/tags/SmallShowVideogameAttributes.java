package edu.ucam.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class SmallShowVideogameAttributes extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Attributes
	 */
	private String stock, purchasePrice, rentalPrice;
	
	
	public int doStartTag() throws JspException {
        
		try{
            pageContext.getOut().print("<li>Stock: <strong>" + stock + "</strong></li>\r\n" + 
            		"<li>Precio de Compra: <strong>" + purchasePrice + " &euro;</strong></li>\r\n" + 
            		"<li>Precio de Alquiler: <strong>" + rentalPrice + " &euro;</strong></li>\r\n");
            
        } catch (IOException e) {
            throw new JspException ("Error: IOException" + e.getMessage());
        }
    	
        return ShowVideogameAttributes.EVAL_BODY_INCLUDE;
    }
	
    
    
    
    
    
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public void setRentalPrice(String rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
}
