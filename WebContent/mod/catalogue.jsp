<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.ArrayList" %> 

<%@ page import = "edu.ucam.pojos.*" %> 
<%@ page import = "edu.ucam.daos.*" %> 

<div class = "videogames-catalogue">
	
	<%
	VideogameDAO videogameDao = new VideogameDAO();
	ArrayList<Videogame> videogamesList = videogameDao.list();
	
	AssessmentDAO assessmentDao = new AssessmentDAO();
	ArrayList<Assessment> assessmentsList = assessmentDao.list();
	%>
	
	CATALOGO
	
	<% for(int i = 0; i < videogamesList.size(); ++i){ %>
	
		<div class="col-lg-4 col-md-6 col-sm-12" style = "padding-bottom: 10px;">
	    
	        <h2><?php echo $name?></h2>
				
			<div class = "row">
				<div class = "col-6">
					<ul>
						<li><strong>Marca:</strong> <?php echo $trademark?></li>
						<li><strong>Modelo:</strong> <?php echo $model?></li>
	                    <li><strong>Precio:</strong> <?php echo $price?></li>
	                    <li><strong>Stock:</strong> <?php echo $stock?></li>
					</ul>
				</div>
					
				<div class ="col-6"><img src = "<?php echo $image; ?>" alt = "example" height = "130px"></div>
					
				<div class = "col-12">
					<div>
						<p align = "justify"><?php echo $description?></p>
					</div>
	            </div>
	
	            <div class = "col-12">
					<p>
	                	<% if(session.getAttribute(User.ATR_USER_LOGGED) != null && videogamesList.get(i).getStock() > 0) { %>
	                    <form method = "POST" action = "src/buy_product.php">
	                        <input type = "hidden" name = "IDUSER" value = "<?php echo $_SESSION['logged_in_user_id']; ?>"/>
	                        <input type = "hidden" name = "IDPRODUCT" value = "<?php echo $id ?>"/>
	                        <input type = "submit" value = "Comprar" class="btn btn-primary"/>
	                    </form>
	  					<% } %>
					</p>
	            </div>
	        </div>
	        
	    </div>
		
	<% } %>
	
</div>

