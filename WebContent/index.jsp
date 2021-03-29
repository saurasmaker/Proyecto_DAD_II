<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "edu.ucam.pojos.*" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Infodeo 2</title>
</head>
<body>
	<jsp:include page="/mod/footer.jsp" />
	
	
	<% User thisUser = (User) session.getAttribute(User.ATR_USER); %>
	<% if(thisUser != null){ %>
		
		<p>User: <%= thisUser.getUsername() %></p>
		<p>Email: <%= thisUser.getEmail() %></p>
		<p>Id: <%= thisUser.getId() %></p>
	
	<% } %>
	
	

	<p><a href = "mod/login.jsp"> Login </a></p>
	<p><a href = "secured/admin_page.jsp"> Admin Page </a></p>
	
	<jsp:include page="/mod/header.jsp" />
</body>
</html>