<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.ucam.servlets.Controller" %>
    
<%@ page import="edu.ucam.pojos.User" %>

<!DOCTYPE html>

<% User thisUser = (User)session.getAttribute(User.ATR_USER_LOGGED); %>

<html>
	<head>
		<jsp:include page="../mod/head.jsp" />
		<title><%=thisUser.getUsername()%> Profile Infodeo</title>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/profile_tools.js"></script>
	</head>
	
	<body>
		<div class = "general container">
			<jsp:include page="../mod/header.jsp" />
			
			<div class = 'row content profile-div'>
			
				<form id = "update-user-form" class = "form-group" action = "<%= request.getContextPath() %>/UPDATE" method = "POST" style = "display: 'none';">
		            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=User.class.getName() %>" />
					<input id = "user-input-update-id" type = "hidden" class="form-control" placeholder = "ID del Usuario" name = "<%=User.ATR_USER_ID %>" value="<%=thisUser.getId() %>">
						
					<label for="user-input-update-username">Nombre de Usuario: </label>
					<p><input id = "user-input-update-username" type = "text" class="form-control" placeholder = "Introduce el Nombre del Usuario..." name = "<%=User.ATR_USER_USERNAME %>" value="<%=thisUser.getUsername() %>" readonly required></p>
		
				    <label for="user-input-update-email">Correo Electrónico: </label>
					<p><input id = "user-input-update-email" type = "email" class="form-control" placeholder = "Introduce el Correo Electrónico del Usuario..." name = "<%=User.ATR_USER_EMAIL %>" value="<%=thisUser.getEmail() %>" readonly required></p>
		
					<label for="user-input-update-password">Contraseña: </label>
					<p><input id = "user-input-update-password" type = "password" class="form-control" placeholder = "Introduce la Contraseña del Usuario..." name = "<%=User.ATR_USER_PASSWORD %>" value="<%=thisUser.getPassword() %>" readonly required></p>
					
					<input type="checkbox" id="loginForm-check" name = "SHOWPASSWORD" onclick = "showPasswordProfile()">
	          		<label data-error="wrong" data-success="right" for="loginForm-check" >Show password.</label>
					
		            <p>
		                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
		                <a id = "input-edit-send" class="btn btn-secondary" type = "button" role="button" href = "users-title" onclick = "cancelUpdateUser()" style = "margin-left: 10px;">Cancelar</a>
		            </p>
		        </form>
				
			
			
			</div>
			
			<jsp:include page="../mod/footer.jsp" />
		</div>
	</body>
</html>