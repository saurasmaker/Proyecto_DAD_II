<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.ucam.pojos.*" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ISC Login</title>
</head>
<body>

	<jsp:include page="/mod/header.jsp" />
			
			
				
	<form action= "<%= request.getContextPath() %>/login" method="post">
		<h3>Sign in</h3>
		<p>Nombre:<input type="text" name="<%=User.ATR_USER_USERNAME %>" value="admin" required></p>
		<p>Clave:<input type="password" name="<%=User.ATR_USER_PASSWORD %>" value="admin" required></p>
		<p><input type="submit"></p>
	</form>
		
		
		
		
	<form action="<%= request.getContextPath() %>/signup" method="post">
		<h3>Sign up</h3>
		<p>Nombre: <input type="text" name="<%=User.ATR_USER_USERNAME %>" value="admin" required></p>
		<p>Email: <input type="email" name="<%=User.ATR_USER_EMAIL %>" value="admin@infodeo.com" required></p>
		<p>Clave: <input type="password" name="<%=User.ATR_USER_PASSWORD %>" value="admin" required></p>
		<p><input type="submit"></p>
	</form>
		
		
		
	<jsp:include page="/mod/footer.jsp" />
	
</body>
</html>