<%@ page language='java' contentType='text/html; charset=ISO-8859-1'
    pageEncoding='ISO-8859-1'%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page import = 'java.util.ArrayList' %> 
<%@ page import = 'sun.misc.BASE64Encoder' %>
<%@ page import = 'edu.ucam.servlets.Controller' %>
<%@ page import = 'edu.ucam.pojos.*, edu.ucam.daos.*, edu.ucam.enums.*' %> 
<%@ page import = 'edu.ucam.actions.user.AddProductToBasket' %>


<%
	/*
		DAO
	*/
	VideogameImageDAO videogameImageDao = new VideogameImageDAO();
	VideogameDAO videogameDao = new VideogameDAO();

	
	
	/*
		VARIABLES
	*/
	String categoryId = null;
	String nameContent = null;
	
	
	
	/*
		Objects
	*/
	Category categoryOfVideogame = null;
	
	
	
	/*
		LISTS
	*/
	ArrayList<Videogame> videogamesCatalogueList;
	ArrayList<VideogameCategory> videogamesCategoriesList;
	ArrayList<VideogameImage> videogamesImagesList;
	
	
	
	/*
		TOOLS
	*/
	BASE64Encoder b64e = new BASE64Encoder();
	
	
	
	/*
		Initializing videogame search variables.
	*/	
	categoryId = request.getParameter("SEARCH_VIDEOGAME_BY_CATEGORY");
	nameContent = request.getParameter("SEARCH_VIDEOGAME_BY_STRING");

	
	if(categoryId != null)
		videogamesCatalogueList = videogameDao.listByCategoryId(categoryId);
	else if(nameContent != null)
		videogamesCatalogueList = videogameDao.listByNameContent(nameContent);
	else 
		videogamesCatalogueList = videogameDao.list();
	
	
	
	/*
		SETTING CONTEXT ATTRIBUTES
	*/
	pageContext.setAttribute("videogameCatalogueList", videogamesCatalogueList);

%>

<!-- CATALOGUE -->
<div class = 'row content videogames-catalogue'>
	
	<div class = 'col-12'>
		<h3 class = 'display-2 text-center'>Cat&aacute;logo</h3>
		<hr width = '50%'/>
		<br/>
	</div>		
		
		
	
	<c:forEach var="videogame" items="${videogameCatalogueList}" varStatus='videogamesLoop'>
	
		<!-- Videogame -->
		<div class='col-lg-3 col-md-4 col-sm-6'  style = 'margin-bottom: 10px;'>
	    
	        <h3><a href="<%= request.getContextPath()%>/mod/videogame.jsp?<%=Videogame.ATR_VIDEOGAME_ID %>=${videogame.id}">${videogame.name}</a></h3>
				
			<div class = 'row'>	
			
				<!-- IMAGES OF VIDEOGAME -->
				<div id='carouselExampleIndicators${videogamesLoop.index}' class='carousel slide col-12' data-interval='0' data-ride='carousel'>
					<ol class='carousel-indicators' style = 'background-color: rgba(220, 220, 220, 0.5);'>
			
						<% 
						videogamesImagesList = videogameImageDao.listByVideogameId(((Videogame)pageContext.getAttribute("videogame")).getId()); 
						pageContext.setAttribute("videogamesImagesList", videogamesImagesList);
						%>
						
						<c:choose>
							<c:when test='${not empty videogamesImagesList}'>
								<c:forEach var='videogameImage' items='${videogamesImagesList}' varStatus='videogamesImagesLoop'>
									<c:set var='videogamesImagesLoopIndex' value='${videogamesImagesLoop.index}'/>
									<li data-target='#carouselExampleIndicators${videogamesLoop.index}' data-slide-to='${videogamesImagesLoop.index}' 
										<%= ((int)pageContext.getAttribute("videogamesImagesLoopIndex")) == 0 ? "class='active'" : ""%>></li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li data-target='#carouselExampleIndicators${videogamesLoop.index}' data-slide-to='0' class='active'></li>
							</c:otherwise>
						</c:choose>
						
					</ol>
				  	
				  	
				  	<div class='carousel-inner'>
				  	
				  		<c:if test='${not empty videogamesImagesList}'>
				  			<c:forEach var='videogameImage' items='${videogamesImagesList}' varStatus='videogamesImagesLoop'>
				  			<c:set var='videogamesImagesLoopIndex' value='${videogamesImagesLoop.index}'/>
				  				<div class='carousel-item <%= ((int)pageContext.getAttribute("videogamesImagesLoopIndex")) == 0 ? "active" : ""%>'>
				  					<c:set var='videogamesImagesLoopIndex' value='${videogamesImagesLoop.index}'/>
				  					<c:set var='base64Image' value='<%= b64e.encode((videogamesImagesList.get((int)pageContext.getAttribute("videogamesImagesLoopIndex")).getImage()))%>'/>
				    				<img class='d-block w-100' src='data:image/png;base64,${base64Image}' alt='${videogameImage.name}'>
				    			</div>
				  			</c:forEach>
				  		</c:if>				  

				  	</div>
				  	
				  	<a class='carousel-control-prev' href='#carouselExampleIndicators${videogamesLoop.index}' role='button' data-slide='prev'>
				    	<span class='carousel-control-prev-icon' aria-hidden='true'></span>
				    	<span class='sr-only'>Previous</span>
				  	</a>
				  
				  	<a class='carousel-control-next' href='#carouselExampleIndicators${videogamesLoop.index}' role='button' data-slide='next'>
				    	<span class='carousel-control-next-icon' aria-hidden='true'></span>
				    	<span class='sr-only'>Next</span>
				  	</a>
				</div>
	
	
				<!-- ATTRIBUTES OF VIDEOGAME -->
	            <div class = 'col-12'>
					<ul>
						<li>Stock: <strong>${videogame.stock}</strong></li>
	                    <li>Precio de Compra: <strong>${videogame.purchasePrice} &euro;</strong></li>
	                    <li>Precio de Alquiler: <strong>${videogame.rentalPrice} &euro;</strong></li>
					</ul>
				</div>  
				
				
				<!-- BUTTONS TO BUY OR RENT VIDEOGAME -->
				<c:if test='${videogame.stock > 0}'>
					<div class='row'>
	                   	<div class='col-6'>
		                   	<form method = 'POST' action = '<%= request.getContextPath()%>/Controller'>
		                   		<input type = 'hidden' name = <%=Controller.ATR_SELECT_ACTION %> value = '<%= AddProductToBasket.ATR_ACTION %>'/>
		                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_PRODUCTID %>' value = '${videogame.id}'/>
		                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_AMOUNT %>' value = '1'/>
		                       	<input type = 'submit' value = 'Comprar' class='btn btn-primary'/>
		                   	</form>
	                   	</div>
	                   
	                   	<div class = 'col-6'>
		                   	<form method = 'POST' action = '<%= request.getContextPath()%>/Controller'>
		                   		<input type = 'hidden' name = <%=Controller.ATR_SELECT_ACTION %> value = '<%= AddProductToBasket.ATR_ACTION %>'/>
		                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_PRODUCTID %>' value = '${videogame.id}'/>
		                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_AMOUNT %>' value = '-1'/>
		                       	<input type = 'submit' value = 'Alquilar' class='btn btn-primary'/>
		                   	</form>
	                   	</div>
                   	</div>
				</c:if>          	
					
	        </div>
	   	</div>	    
		
	</c:forEach>
	
 </div>
