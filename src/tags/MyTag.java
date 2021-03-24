package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	private String atrNombre;
	private String zz;

    public int doStartTag() throws JspException {
        try{
            pageContext.getOut().print("¡Hola Mundo!<br/><br/>");
            
            if (atrNombre != null) {
                pageContext.getOut().print("¡Hola " + atrNombre + " " + this.zz + "!");
            }
        } catch (IOException e) {
            throw new JspException ("Error: IOException" + e.getMessage());
        }
        return SKIP_BODY;
    }
	
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public void setNombre(String nombre) {
        this.atrNombre = nombre;
    }
    
    public void setApellidos(String xx) {
    	this.zz = xx;
    }

}
