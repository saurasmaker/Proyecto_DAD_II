<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "edu.ucam.pojos.User" %>    

	<% if(session.getAttribute(User.ATR_USER_LOGGED)==null) { %>
	<jsp:include page="/mod/login.jsp" />
	<% } %>
	<header>
		<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark sticky-top">
		    <a href = "index.jsp"> <img src = "img\INFO(2).png" height = "45px" style = "margin-right: 10px;" /></a>
		    <a class="navbar-brand" href="index.jsp">INFODEO</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		        <span class="navbar-toggler-icon"></span>
		    </button>
		
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		        <ul class="navbar-nav mr-auto">
		            <li class="nav-item">
		                <a class="nav-link" href = "#">Catálogo <span class="sr-only">()</span></a>
		            </li>
		            <li class="nav-item">
		                <a class="nav-link" href="#">Ofertas</a>
		            </li>
		            <li class="nav-item dropdown">
		                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		            		Secciones
		                </a>
		                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		                    <a class="dropdown-item" href="#">Acción</a>
		                    <a class="dropdown-item" href="#">RPG</a>
		                    <a class="dropdown-item" href="#">Aventura</a>
		                    <a class="dropdown-item" href="#">Rol</a>
		                    <a class="dropdown-item" href="#">Sandbox</a>
		                    <a class="dropdown-item" href="#">Deportes</a>
		                    <a class="dropdown-item" href="#">Fantasía</a>
		                    <a class="dropdown-item" href="#">Ciencia Ficción</a>
		                    <div class="dropdown-divider"></div>
		                    <a class="dropdown-item" href="#">Otros</a>
		                </div>
		            </li>
		        </ul>
		
		        <form class="form-inline my-2 my-lg-0" style="margin-right: 10%;" action = "index.php" method = "get">
		            <input type = "hidden" name = "ACTUAL_PAGE" value = "catalogue">
		            <input class="form-control mr-sm-2" type="search" name = "SEARCH_PRODUCT" placeholder="Search" aria-label="Search" required>
		            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		        </form>
		
		        <ul class="navbar-nav my-2 my-lg-0">               
		            
		            <% User thisUser = (User) session.getAttribute(User.ATR_USER_LOGGED); %>
					<% if(thisUser == null){ %>	
		           
		            
		                <li class="nav-item " data-toggle="modal" data-target="#modalLoginForm">
		                	<a class="nav-link" href="#">
								<svg class="bi bi-person-fill" width="1em" height="1em" viewBox="-2 -2 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		    	        			<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 100-6 3 3 0 000 6z" clip-rule="evenodd" />
		                		</svg>
		                		Login
							</a>
		            	</li>     
		        	<% } else { %>	
		        		<li class="nav-item dropdown">
		                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		            		<%= thisUser.getUsername() %>
		                </a>
		                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		                    <a class="dropdown-item" href="#">Perfil</a>
		                    <a class="dropdown-item" href="index.php?ACTUAL_PAGE=orders">Pedidos</a>
		                    <a class="dropdown-item" href="#">Preferencias</a>
		                    <div class="dropdown-divider"></div>
		                    <a class="dropdown-item" href="<%= request.getContextPath() %>/logout">Logout</a>
		                </div>
		                
		            </li>
		            
		        		<% if(thisUser.getUsername().equals("admin")) { %>	
		                	<li class="nav-item">
		                		<a class="nav-link" href = "secured/admin_page.jsp">Administrar<span class="sr-only">(current)</span></a>
		            		</li>
		          	<% } }%>	     
		        </ul>
		    </div>
		</nav>
	</header>