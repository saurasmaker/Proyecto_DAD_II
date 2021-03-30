<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.*" %>
<%@ page import = "edu.ucam.daos.*" %>
<%@ page import = "edu.ucam.servlets.Controller" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Infodeo 2</title>
</head>
<body>
	<h1>Admin Page</h1>
	
	<h2>Users</h2>
	
	<h3>Add user</h3>
	<form action = "<%= request.getContextPath() %>/create" method = "post">
		<input type = "hidden" name = "<%= Controller.OBJECT_CLASS%>" value = "<%= User.class.getName() %>" >
		<p>
			<input type = "text" name = "<%= User.ATR_USER_USERNAME %>" placeholder = "username" required/><br/>
			<input type = "email" name = "<%= User.ATR_USER_EMAIL %>" placeholder = "email@infodeo.com" required><br/>
			<input type = "password" name = "<%= User.ATR_USER_PASSWORD %>" placeholder = "password" required><br/>
			<input type = "submit" value = "Crear">
		</p>
		
	</form>
	
	<h3>Users list</h3>
	<table class="default">
	  	<tr>
	    	<td>ID</td>	
	    	<td>Username</td>	
	    	<td>Email</td>    	
	    	<td>Password</td>
	  	</tr>
	
	<%
		UserDAO userDao = new UserDAO();
		ArrayList<User> usersList = userDao.list();
	
		if(usersList != null)
		for (int i = 0; i < usersList.size(); ++i){ %>		
			<tr>
				<td><%= usersList.get(i).getId() %></td>	
			    <td><%= usersList.get(i).getUsername() %></td>
			    <td><%= usersList.get(i).getEmail() %></td>
			    <td><%= usersList.get(i).getPassword() %></td>
			</tr>		
		<% } %>
	
	</table>
	
	
	
	
	<h2>Videogames</h2>
	
	<h3>Add videogame</h3>
	<form action = "<%= request.getContextPath() %>/create" method = "post">
		<input type = "hidden" name = "<%= Controller.OBJECT_CLASS%>" value = "<%= Videogame.class.getName() %>" >
		<p>
			<input type = "text" name = "<%= Videogame.ATR_VIDEOGAME_NAME %>" placeholder = "username" required/><br/>
			<textarea name = "<%= Videogame.ATR_VIDEOGAME_DESCRIPTION %>" placeholder = "description" required></textarea><br/>
			<input type = "date" name = "<%= Videogame.ATR_VIDEOGAME_RELEASEDATE %>" required><br/>
			<input type = "number" name = "<%= Videogame.ATR_VIDEOGAME_PURCHASEPRICE %>" placeholder = "purchase price" step = ".01" required><br/>
			<input type = "number" name = "<%= Videogame.ATR_VIDEOGAME_RENTALPRICE %>" placeholder = "rental price" step = ".01" required><br/>
			<input type = "number" name = "<%= Videogame.ATR_VIDEOGAME_STOCK %>" placeholder = "stock" step = "1" required><br/>
			<input type = "submit" value = "Crear">
		</p>
		
	</form>
	
	<h3>Videogames list</h3>
	<table class="default">
	  	<tr>
	    	<td>ID</td>	
	    	<td>Name</td>	
	    	<td>Purchase Price</td>    	
	    	<td>Rental Price</td>
	    	<td>Stock</td>
	  	</tr>
	
	<%
		VideogameDAO videogameDao = new VideogameDAO();
		ArrayList<Videogame> videogamesList = videogameDao.list();
	
		if(videogamesList != null)
		for (int i = 0; i < videogamesList.size(); ++i){ %>		
			<tr>
				<td><%= videogamesList.get(i).getId() %></td>	
			    <td><%= videogamesList.get(i).getName() %></td>
			    <td><%= videogamesList.get(i).getPurchasePrice() %></td>
			    <td><%= videogamesList.get(i).getRentalPrice() %></td>
			    <td><%= videogamesList.get(i).getStock() %></td>
			</tr>		
		<% } %>
	
	</table>
	
</body>
</html>