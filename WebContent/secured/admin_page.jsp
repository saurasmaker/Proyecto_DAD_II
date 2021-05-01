<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "edu.ucam.pojos.*" %>
<%@ page import = "edu.ucam.daos.*" %>
    
    
<%
	pageContext.setAttribute("usersList", (new UserDAO()).list());
	pageContext.setAttribute("videogamesList", (new VideogameDAO()).list());
	pageContext.setAttribute("categoriesList", (new CategoryDAO()).list());
	pageContext.setAttribute("assessmentsList", (new AssessmentDAO()).list());
	pageContext.setAttribute("billsList", (new BillDAO()).list());
	pageContext.setAttribute("purchasesList", (new PurchaseDAO()).list());
	pageContext.setAttribute("rentalsList", (new RentalDAO()).list());
%>
    
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../mod/head.jsp" />
		
		<!-- Styles -->
		<link rel = "stylesheet" href = "../styles/mystyle.css"/>
	
		<!-- Scripts -->
		<script type="text/javascript" src="../js/test.js"></script>
		<script type="text/javascript" src="../js/adminCRUD.js"></script>
	
		<title>Infodeo2 - Admin</title>
		
	</head>
	
	
	<!-- Select JS Script OnLoad depends on parameters -->
	<%  String a = request.getParameter("UPDATE_VIDEOGAME_PARAMETERS");
		String s = request.getParameter(Videogame.ATR_VIDEOGAME_ID);
		if(a != null && s != null) { %>
			<body onload = "updateVideogame(<%=a %>)"> 
	<% } else { %>
		<body onload = "displayAdminPage()">
	<% } %>


		<div class = "general container">
			<jsp:include page="../mod/header.jsp" />		
			
			<div class = "content row">
				<div class = "col-12">
					<h3 class = "display-2 text-center">Administrar</h3>
					<hr width = "50%"/>
					<br/>
				</div>
				
				<jsp:include page="users_admin.jsp"/>
				<jsp:include page="videogames_admin.jsp"/>
				<jsp:include page="categories_admin.jsp"/>
				<jsp:include page="assessments_admin.jsp" />
				<jsp:include page="bills_admin.jsp" /> 
				<jsp:include page="purchases_admin.jsp"/> 
				<jsp:include page="rentals_admin.jsp"/> 				
				
			</div>
			
			<jsp:include page="../mod/footer.jsp" />
		</div>
	</body>
</html>