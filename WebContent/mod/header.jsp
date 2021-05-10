<%@page import="edu.ucam.actions.user.EditProductFromBasket"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
    
<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.enums.SearchBy" %>

<%@ page import = "edu.ucam.servlets.Controller" %> 
<%@ page import = 'edu.ucam.actions.user.*' %> 
 
<%@ page import = "edu.ucam.pojos.*" %>

<%@ page import = "edu.ucam.daos.CategoryDAO" %>
<%@ page import = "edu.ucam.daos.VideogameDAO" %>

<% 
	/*
		DAO	
	*/
	CategoryDAO headerCategoryDAO = new CategoryDAO();
	VideogameDAO headerVideogameDAO = new VideogameDAO();
	
	
	/*
		LISTS
	*/
	ArrayList<Category> headerCategoriesList = headerCategoryDAO.list();
	

	/*
		Objects
	*/
	User thisUser = (User) session.getAttribute(User.ATR_USER_LOGGED);
	Basket basket;
	
	
	/*
		Initializing BASKET.
	*/	
	if (session.getAttribute(Basket.ATR_BASKET) != null){
		basket = (Basket) session.getAttribute(Basket.ATR_BASKET);
	}
	else{
		basket = new Basket();
		session.setAttribute(Basket.ATR_BASKET, basket);
	}
	
	
	/*
		SETTING CONTEXT ATTRIBUTES
	*/
	pageContext.setAttribute("headerCategoriesList", headerCategoriesList);
	pageContext.setAttribute("basketProducts", basket.getProducts());
%>


	<c:if test='${sessionScope.ATR_USER_LOGGED == null}'>
		<jsp:include page="/mod/login.jsp" />
	</c:if>
	
	<header>
		<nav class="container navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
		    <a href = "index.jsp"> <img src = "img\INFO(2).png" height = "45px" style = "margin-right: 10px;" /></a>
		    <a class="navbar-brand" href="index.jsp">INFODEO</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		        <span class="navbar-toggler-icon"></span>
		    </button>
		
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		        <ul class="navbar-nav mr-auto">
		            <li class="nav-item">
		                <a class="nav-link" href = "<%= request.getContextPath()%>/index.jsp">Catálogo <span class="sr-only">()</span></a>
		            </li>
		            <li class="nav-item dropdown">
		                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownSections" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		            		Secciones
		                </a>
		                <div class="dropdown-menu" aria-labelledby="navbarDropdownSections">
		                    
		                    <c:forEach var='headerCategory' items='${headerCategoriesList}' varStatus='headerCategoriesLoop'>
		                    	<a class="dropdown-item" href="<%=request.getContextPath() %>/index.jsp?SEARCH_VIDEOGAME_BY_CATEGORY=${headerCategory.id}">${headerCategory.name}</a>
		                    </c:forEach>

		                </div>
		            </li>
		        </ul>
		
		        <form class="form-inline my-2 my-lg-0" style="margin-right: 10%;" action= "<%= request.getContextPath() %>/Controller" method="post">
		           	<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= SearchProduct.ATR_ACTION %>'/>
		            <input class="form-control mr-sm-2" type="search" name = "SEARCH_VIDEOGAME_BY_STRING" placeholder="Search" aria-label="Search" required>
		            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		        </form>
		
		        <ul class="navbar-nav my-2 my-lg-0">               
		            
		            
		            <li class="nav-item dropdown">
		            	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBasket" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		            		Cesta
		                </a>
		                <div class="dropdown-menu" aria-labelledby="navbarDropdownBasket">
		                    
		                    <c:forEach var='basketItem' items='${basketProducts}' varStatus='basketProductsLoop'>
		                    			                    	
		                    	<c:set var='videogameInBasket' value='<%=headerVideogameDAO.read(((Item)pageContext.getAttribute("basketItem")).getVideogameId(), SearchBy.ID) %>'/>
		                    	<div class="dropdown-item">
		    			  			
		    			  			${videogameInBasket.name}
			    			  		
			    			  		<c:choose>
			    			  			<c:when test='${basketItem.amount == -1}'>
			    			  				Alquiler
			    			  			</c:when>
			    			  			<c:when test='${basketItem.amount > 0}'>
			    			  				x ${basketItem.amount}
			    			  			</c:when>
			    			  		</c:choose>			    	
	
									<form action= "<%= request.getContextPath() %>/Controller" method="post">
				                    	<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= EditProductFromBasket.ATR_ACTION %>'/>
				                    	<input type='hidden' name='<%= Basket.ATR_BASKET_PRODUCTID %>' value='${videogameInBasket.id}'/>
				                    	
				                    	<c:choose>
				                    		<c:when test='${basketItem.amount == -1}'>
				                    			<input type='hidden' value='0' name='<%=Basket.ATR_BASKET_AMOUNT %>'/>
				                    		</c:when>
				                    		<c:otherwise>
				                    			<input type='number' step='1' min='0' value='${basketItem.amount}' name='<%=Basket.ATR_BASKET_AMOUNT %>'/>
				                    		</c:otherwise>
				                    	</c:choose>
				                    	
				                    	<input type = "submit" class = "btn-secondary" value = '<%=((Item)pageContext.getAttribute("basketItem")).getAmount() == -1 ? "Eliminar" : "Editar" %>' class="dropdown-item"/>
			                    	</form>	
		    			  	
		    			  		</div>
		                    </c:forEach>
		                    
							<div class="dropdown-divider"></div>
							<div>
								<form action= "<%= request.getContextPath() %>/Controller" method="post">
									<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= CreateBill.ATR_ACTION %>'/>
				                    <input type='submit' value='Generar Factura' class='dropdown-item'/>
								</form>
							</div>
							
		                </div>
		            </li>     

					
					<c:choose>
						<c:when test='${empty sessionScope.ATR_USER_LOGGED}'>
							<li class="nav-item " data-toggle="modal" data-target="#modalLoginForm">
			                	<a class="nav-link" href="#">
									<svg class="bi bi-person-fill" width="1em" height="1em" viewBox="-2 -2 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			    	        			<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 100-6 3 3 0 000 6z" clip-rule="evenodd" />
			                		</svg>
			                		Login
								</a>
			            	</li> 
						</c:when>
						<c:otherwise>
							<li class="nav-item dropdown">
				                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				            		<%= thisUser.getUsername() %>
				                </a>
				                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				                    <a class="dropdown-item" href="<%=request.getContextPath() %>/user/user_profile.jsp">Perfil</a>
				                    <a class="dropdown-item" href="<%=request.getContextPath() %>/user/user_bills.jsp">Facturas</a>
				                    <a class="dropdown-item" href="">Preferencias</a>		                    
				                    <div class="dropdown-divider"></div>
				                    <form action= "<%= request.getContextPath() %>/Controller" method="post">
				                    	<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Logout.ATR_ACTION %>'/>
				                    	<input type = "submit" value = "Logout" class="dropdown-item">
				                    </form>		                    
				                </div>
			            	</li>
			            	
			            	<c:if test='${sessionScope.ATR_USER_LOGGED.isAdmin == true}'>
			            		<li class="nav-item">
		                			<a class="nav-link" href = "<%=request.getContextPath() %>/secured/admin_page.jsp">Administrar<span class="sr-only">(current)</span></a>
		            			</li>
			            	</c:if>
			            	
						</c:otherwise>
					</c:choose>	
					
		        </ul>
		    </div>
		</nav>
	</header>