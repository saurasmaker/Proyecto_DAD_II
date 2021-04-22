package edu.ucam.tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.ucam.daos.AssessmentDAO;
import edu.ucam.daos.VideogameDAO;
import edu.ucam.pojos.Assessment;
import edu.ucam.pojos.Videogame;
import edu.ucam.pojos.VideogameImage;

public class ShowVideogamesByCategory extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	private String atrCategory;

    public int doStartTag() throws JspException {
        
    	//ArrayList<Videogame> videogamesCatalogueList = (new VideogameDAO()).listByCategoryId(atrCategory);
    	//ArrayList<Assessment> assessmentsCatalogueList = (new AssessmentDAO()).list();
    	//ArrayList<VideogameImage> videogamesImagesList;
    	
    	
        return ShowVideogamesByCategory.EVAL_BODY_INCLUDE;
    }
	
    
    
    
    
    
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    
    
    public void setCategory(String category) {
        this.atrCategory = category;
    }
    

}
