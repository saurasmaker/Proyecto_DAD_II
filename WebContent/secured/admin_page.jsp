<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.*" %>
<%@ page import = "edu.ucam.daos.*" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Infodeo 2</title>
</head>
<body>
	<h2>Admin Page</h2>
	
	<h3>Users</h3>
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
	
		for (int i = 0; i < usersList.size(); ++i){ %>		
			<tr>
				<td><%= usersList.get(i).getId() %></td>	
			    <td><%= usersList.get(i).getUsername() %></td>
			    <td><%= usersList.get(i).getEmail() %></td>
			    <td><%= usersList.get(i).getPassword() %></td>
			</tr>		
		<% } %>
	
	</table>
	
	
	<h3>Videogames</h3>
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