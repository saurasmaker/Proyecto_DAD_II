<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.ArrayList" %> 

<%@ page import = "edu.ucam.pojos.*" %> 
<%@ page import = "edu.ucam.daos.*" %> 

<div class = "row content videogames-catalogue">
	
	<%
	ArrayList<Videogame> videogamesCatalogueList = (new VideogameDAO()).list();
	ArrayList<Assessment> assessmentsCatalogueList = (new AssessmentDAO()).list();
	
	User thisUser = (User) session.getAttribute(User.ATR_USER_LOGGED);
	Basket basket = null;
	if(thisUser != null){
		basket = new Basket(thisUser.getId());
		session.setAttribute(Basket.ATR_BASKET, basket);
	}

	%>
	
	<div class = "col-12">
		<h3 class = "display-2 text-center">Catálogo</h3>
		<hr width = "50%"/>
		<br/>
	</div>
	
	<% for(int i = 0; i < videogamesCatalogueList.size(); ++i){ %>
	
		<div class="col-lg-4 col-md-6 col-sm-12" style = "padding-bottom: 10px;">
	    
	        <h2><%= videogamesCatalogueList.get(i).getName() %></h2>
				
			<div class = "row">
				<div class = "col-6">
					<ul>
						<li><strong>Release date:</strong> <%= videogamesCatalogueList.get(i).getReleaseDate() %></li>
						<li><strong>Stock:</strong> <%= videogamesCatalogueList.get(i).getReleaseDate() %></li>
	                    <li><strong>Purchase price:</strong> <%= videogamesCatalogueList.get(i).getPurchasePrice() %></li>
	                    <li><strong>Rental price:</strong> <%= videogamesCatalogueList.get(i).getRentalPrice() %></li>
					</ul>
				</div>
					
				<div id="carouselExampleIndicators" class="carousel slide col-6" data-interval="0" data-ride="carousel">
				  <ol class="carousel-indicators">
				    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
				    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				  </ol>
				  <div class="carousel-inner">
				    <div class="carousel-item active">
				      <img class="d-block w-100" src="..." alt="First slide">
				    </div>
				    <div class="carousel-item">
				      <img class="d-block w-100" src="..." alt="Second slide">
				    </div>
				    <div class="carousel-item">
				      <img class="d-block w-100" src="..." alt="Third slide">
				    </div>
				  </div>
				  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </a>
				</div>
					
				<div class = "col-12">
					<div>
						<p align = "justify"><%= videogamesCatalogueList.get(i).getDescription() %></p>
					</div>
	            </div>
	
	            
					
               	<% if(session.getAttribute(User.ATR_USER_LOGGED) != null && videogamesCatalogueList.get(i).getStock() > 0) { %>
                   <div class = "col-3">
                   <form method = "POST" action = "<%= request.getServletContext()%>/buy">
                       <input type = "hidden" name = "IDUSER" value = "<%= "asdf" %>"/>
                       <input type = "hidden" name = "IDPRODUCT" value = "<?php echo $id ?>"/>
                       <input type = "submit" value = "Comprar" class="btn btn-primary"/>
                   </form>
                   </div>
                   
                   <div class = "col-3">
                   <form method = "POST" action = "<%= request.getServletContext()%>/rent">
                       <input type = "hidden" name = "IDUSER" value = "<?php echo $_SESSION['logged_in_user_id']; ?>"/>
                       <input type = "hidden" name = "IDPRODUCT" value = "<?php echo $id ?>"/>
                       <input type = "submit" value = "Alquilar" class="btn btn-primary"/>
                   </form>
                   </div>
                   
                   <div class = "col-3">
                   <form method = "POST" action = "">
                       <input type = "hidden" name = "IDUSER" value = "<?php echo $_SESSION['logged_in_user_id']; ?>"/>
                       <input type = "hidden" name = "IDPRODUCT" value = "<?php echo $id ?>"/>
                       <input type = "submit" value = "Votar" class="btn btn-secondary"/>
                   </form>
                   </div>
                   
 				<% } %>
					
	            </div>
	        </div>	    
		
	<% } %>
	
 </div>
