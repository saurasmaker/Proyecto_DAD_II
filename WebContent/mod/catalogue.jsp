<%@ page language='java' contentType='text/html; charset=ISO-8859-1'
    pageEncoding='ISO-8859-1'%>

<%@ page import = 'java.util.ArrayList' %> 
<%@ page import = 'sun.misc.BASE64Encoder' %>

<%@ page import = 'edu.ucam.servlets.Controller' %>

<%@ page import = 'edu.ucam.pojos.*' %> 
<%@ page import = 'edu.ucam.daos.*' %> 
<%@ page import = 'edu.ucam.enums.*' %>

<%@ page import = 'edu.ucam.actions.user.AddProductToBasket' %>


<%
	
	ArrayList<Videogame> videogamesCatalogueList;
	
	
	/*
		Initializing videogame search variables.
	*/	
	String categoryId = null;
	try{
		categoryId = request.getParameter("SEARCH_VIDEOGAME_BY_CATEGORY");
	}catch(Exception e){
		categoryId = null;
	}
	
	if(categoryId != null)
		videogamesCatalogueList = (new VideogameDAO()).listByCategoryId(categoryId);
	else 
		videogamesCatalogueList = (new VideogameDAO()).list();
	
	/*
		Initializing and declaring other necessary variables.
	*/
	ArrayList<VideogameCategory> videogamesCategoriesList;
	ArrayList<VideogameImage> videogamesImagesList;
	Category categoryOfVideogame;

%>


<div class = 'row content videogames-catalogue'>
	
	<div class = 'col-12'>
		<h3 class = 'display-2 text-center'>Cat&aacute;logo</h3>
		<hr width = '50%'/>
		<br/>
	</div>		
		
	<% for(int i = 0; i < videogamesCatalogueList.size(); ++i) { 
		Videogame showVcl = videogamesCatalogueList.get(i); %>
	
		<div class='col-lg-3 col-md-4 col-sm-6'  style = 'margin-bottom: 10px; background-color: rgba(220, 220, 220, 0.5);'>
	    
	        <h3><a href="<%= request.getContextPath()%>/mod/videogame.jsp?<%=Videogame.ATR_VIDEOGAME_ID %>=<%=showVcl.getId() %>"><%= showVcl.getName()%></a></h3>
				
			<div class = 'row'>	
			
				<div id='carouselExampleIndicators<%=i %>' class='carousel slide col-12' data-interval='0' data-ride='carousel'>
					<ol class='carousel-indicators'  style = 'background-color: rgba(220, 220, 220, 0.5); '>
					<% VideogameImageDAO videogameImageDao = new VideogameImageDAO();
					videogamesImagesList = videogameImageDao.listByVideogameId(videogamesCatalogueList.get(i).getId());
					
					if(videogamesImagesList != null && videogamesImagesList.size() != 0) {
						for(int j = 0; j < videogamesImagesList.size(); ++j) {	%>
					    		<li data-target='#carouselExampleIndicators<%=i %>' data-slide-to='<%=j %>' <% if(j == 0){ %> class='active' <% } %> ></li>
					    
					<% 	} } else { %>
							<li data-target='#carouselExampleIndicators<%=i %>' data-slide-to='0' class='active'></li>
					<% 	} %>
					</ol>
				  	
				  	<div class='carousel-inner'>
				  	
				  		<%	if(videogamesImagesList != null && videogamesImagesList.size() != 0) {
								for(int j = 0; j < videogamesImagesList.size(); ++j) { 	BASE64Encoder b64e = new BASE64Encoder(); %>
				    	<div class='carousel-item <% if(j == 0){ %> active <% } %>'>
				    		<img class='d-block w-100' src='data:image/png;base64,<%= b64e.encode((videogamesImagesList.get(j).getImage()))%>' alt='<%=videogamesImagesList.get(j).getName() %>'>
				    	</div>
				    	<% } } %>

				  	</div>
				  	
				  	<a class='carousel-control-prev' href='#carouselExampleIndicators<%=i %>' role='button' data-slide='prev'>
				    	<span class='carousel-control-prev-icon' aria-hidden='true'></span>
				    	<span class='sr-only'>Previous</span>
				  	</a>
				  
				  	<a class='carousel-control-next' href='#carouselExampleIndicators<%=i %>' role='button' data-slide='next'>
				    	<span class='carousel-control-next-icon' aria-hidden='true'></span>
				    	<span class='sr-only'>Next</span>
				  	</a>
				</div>
	
	            <div class = 'col-12'>
					<ul>
						<li>Stock: <strong><%= showVcl.getStock() %></strong></li>
	                    <li>Precio de Compra: <strong><%= showVcl.getPurchasePrice() %> &euro;</strong></li>
	                    <li>Precio de Alquiler: <strong><%= showVcl.getRentalPrice() %> &euro;</strong></li>
					</ul>
				</div>  
				
				<% if(showVcl.getStock() > 0) { %>
		                   
		                	<div class='row'>
			                   	<div class='col-6'>
				                   	<form method = 'POST' action = '<%= request.getContextPath()%>/Controller'>
				                   		<input type = 'hidden' name = <%=Controller.ATR_SELECT_ACTION %> value = '<%= AddProductToBasket.ATR_ACTION %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_PRODUCTID %>' value = '<%=showVcl.getId() %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_AMOUNT %>' value = '1'/>
				                       	<input type = 'submit' value = 'Comprar' class='btn btn-primary'/>
				                   	</form>
			                   	</div>
			                   
			                   	<div class = 'col-6'>
				                   	<form method = 'POST' action = '<%= request.getContextPath()%>/Controller'>
				                   		<input type = 'hidden' name = <%=Controller.ATR_SELECT_ACTION %> value = '<%= AddProductToBasket.ATR_ACTION %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_PRODUCTID %>' value = '<%=showVcl.getId() %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_AMOUNT %>' value = '-1'/>
				                       	<input type = 'submit' value = 'Alquilar' class='btn btn-primary'/>
				                   	</form>
			                   	</div>
		                   	</div>
		            	<% } %>	             	
					
	        </div>
	   	</div>	    
		
	<% } %>
	
 </div>
