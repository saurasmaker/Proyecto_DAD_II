<%@ page language='java' contentType='text/html; charset=ISO-8859-1'
    pageEncoding='ISO-8859-1'%>

<%@ page import = 'java.util.ArrayList' %> 
<%@ page import = 'sun.misc.BASE64Encoder' %>

<%@ page import = 'edu.ucam.pojos.*' %> 
<%@ page import = 'edu.ucam.daos.*' %> 
<%@ page import = 'edu.ucam.enums.*' %>


	<% 
		/*
			declaration of necessary variables.
		*/
		ArrayList<Assessment> assessmentsCatalogueList = (new AssessmentDAO()).list();
	%>