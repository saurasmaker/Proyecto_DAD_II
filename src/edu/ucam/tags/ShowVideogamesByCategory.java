package edu.ucam.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class ShowVideogamesByCategory extends TagSupport{
	
	private static final long serialVersionUID = 1L;

    public int doStartTag() throws JspException {
        
    	//ArrayList<Videogame> videogamesCatalogueList = (new VideogameDAO()).listByCategoryId(atrCategory);
    	//ArrayList<Assessment> assessmentsCatalogueList = (new AssessmentDAO()).list();
    	//ArrayList<VideogameImage> videogamesImagesList;
    	
    	
        return ShowVideogamesByCategory.EVAL_BODY_INCLUDE;
    }
	
    
    
    
    
    
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
