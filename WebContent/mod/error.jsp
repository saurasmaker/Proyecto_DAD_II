<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Mis prácticas DAD II</title>
	</head>

	<body>
		<jsp:include page="/mod/header.jsp" />
		<%= request.getParameter("ERROR_TYPE") %>
		<jsp:include page="/mod/footer.jsp" />
	</body>

</html>