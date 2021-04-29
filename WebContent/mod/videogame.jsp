<%@ page language='java' contentType='text/html; charset=ISO-8859-1'
    pageEncoding='ISO-8859-1'%>

<%@ page import = 'java.util.ArrayList' %> 
<%@ page import = 'sun.misc.BASE64Encoder' %>

<%@ page import = 'edu.ucam.servlets.*' %>
<%@ page import = 'edu.ucam.pojos.*' %> 
<%@ page import = 'edu.ucam.daos.*' %> 
<%@ page import = 'edu.ucam.enums.*' %>

<%@ page import = 'edu.ucam.actions.user.MakeAssessment' %>
<%@ page import = 'edu.ucam.actions.user.AddProductToBasket' %>

<!DOCTYPE html>
<html lang="es">

	<head>
		<jsp:include page="../mod/head.jsp" />
		<title>Videogame - Infodeo 2</title>
	</head>

	<body>
		<div class = "general container">
			<jsp:include page="../mod/header.jsp"></jsp:include>
			
			<% 
			/*
				declaration of necessary variables.
			*/
			String videogameId = request.getParameter(Videogame.ATR_VIDEOGAME_ID);
			if(videogameId != null) {
				User userLoged = (User) session.getAttribute(User.ATR_USER_LOGGED);
				Videogame thisVideogame = (new VideogameDAO()).read(videogameId, SearchBy.ID);
				ArrayList<Assessment> assessmentsVideogameList = (new AssessmentDAO()).listByVideogameId(videogameId);
				ArrayList<VideogameImage> videogameImagesList = (new VideogameImageDAO()).listByVideogameId(videogameId);
				ArrayList<VideogameCategory> videogameCategoriesList = (new VideogameCategoryDAO()).listByVideogameId(videogameId);
			%>
			
			
				<div class = 'row content show-videogame'>
		
					<div class = 'col-12'>
						<h3 class = 'display-2 text-center'><%=thisVideogame.getName() %></h3>
						<hr width = '50%'/>
						<br/>
					</div>	
					
					
					<div id='carouselExampleIndicators' class='carousel slide col-lg-6 col-md-6 col-sm-12' data-interval='0' data-ride='carousel'>
						<ol class='carousel-indicators'>
						<%
						
						if(videogameImagesList != null && videogameImagesList.size() != 0) {
							for(int j = 0; j < videogameImagesList.size(); ++j) {	%>
						    		<li data-target='#carouselExampleIndicators' data-slide-to='<%=j %>' <% if(j == 0){ %> class='active' <% } %> ></li>
						    
						<% 	} } else { %>
								<li data-target='#carouselExampleIndicators' data-slide-to='0' class='active'></li>
						<% 	} %>
						</ol>
					  	
					  	<div class='carousel-inner'>
					  	
					  		<%	if(videogameImagesList != null && videogameImagesList.size() != 0) {
									for(int j = 0; j < videogameImagesList.size(); ++j) { 	BASE64Encoder b64e = new BASE64Encoder(); %>
					    	<div class='carousel-item <% if(j == 0){ %> active <%}%>'>
					    		<img class='d-block w-100' src='data:image/png;base64,<%= b64e.encode((videogameImagesList.get(j).getImage()))%>' alt='<%=videogameImagesList.get(j).getName() %>'>
					    	</div>
					    	<% 	}	} %>
	
					  	</div>
					  	
					  	<a class='carousel-control-prev' href='#carouselExampleIndicators' role='button' data-slide='prev'>
					    	<span class='carousel-control-prev-icon' aria-hidden='true'></span>
					    	<span class='sr-only'>Previous</span>
					  	</a>
					  
					  	<a class='carousel-control-next' href='#carouselExampleIndicators' role='button' data-slide='next'>
					    	<span class='carousel-control-next-icon' aria-hidden='true'></span>
					    	<span class='sr-only'>Next</span>
					  	</a>
					</div>
					
					<div class="col-lg-6 col-md-6 col-sm-12">
						<strong>Características: </strong>
						<ul>
							<li>Stock: <strong><%= thisVideogame.getStock() %></strong></li>
		                    <li>Precio de Compra: <strong><%= thisVideogame.getPurchasePrice() %> &euro;</strong></li>
		                    <li>Precio de Alquiler: <strong><%= thisVideogame.getRentalPrice() %> &euro;</strong></li>
		                    
		                    <li>Categor&iacute;as: 
		                    <strong><% 
		                    
		                    if(videogameCategoriesList!=null)
		                    for(int j = 0; j < videogameCategoriesList.size(); ++j) {	                
		                    	VideogameCategory showVc = videogameCategoriesList.get(j);
		                    	out.print((new CategoryDAO()).read(showVc.getCategoryId(), SearchBy.ID).getName() + " ");
		                    }
		                    %></strong>
		                    </li>
		                    		                    
						</ul>
					
						<br/><strong>Descripción: </strong> <p><%= thisVideogame.getDescription() %></p>
					
						<% if(thisVideogame.getStock() > 0) { %>
		                   
		                	<div class='row'>
			                   	<div class='col-3'>
				                   	<form method = 'POST' action = '<%= request.getContextPath()%>/Controller'>
				                   		<input type = 'hidden' name = <%=Controller.ATR_SELECT_ACTION %> value = '<%= AddProductToBasket.ATR_ACTION %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_PRODUCTID %>' value = '<%=thisVideogame.getId() %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_AMOUNT %>' value = '1'/>
				                       	<input type = 'submit' value = 'Comprar' class='btn btn-primary'/>
				                   	</form>
			                   	</div>
			                   
			                   	<div class = 'col-3'>
				                   	<form method = 'POST' action = '<%= request.getContextPath()%>/Controller'>
				                   		<input type = 'hidden' name = <%=Controller.ATR_SELECT_ACTION %> value = '<%= AddProductToBasket.ATR_ACTION %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_PRODUCTID %>' value = '<%=thisVideogame.getId() %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_AMOUNT %>' value = '-1'/>
				                       	<input type = 'submit' value = 'Alquilar' class='btn btn-primary'/>
				                   	</form>
			                   	</div>
		                   	</div>
		            	<% } %>	
		             
					</div>






					<!-- ASSESSMENTS -->
					<div class='col-12 row'>
						<br/>
			
					
						<div id = "assessments-title" class = "col-12">
					        <h3 class = "display-3">Reseñas</h3>
					        <hr width = "25%" align = "left"/>
					        <br/>
					    </div>
					
					
						<!-- MAKE ASSESSMENT -->
						<div class ="col-6">
							<%if(userLoged!=null) { 
								Assessment thisAssessment = new AssessmentDAO().readByVideogameUserId(userLoged.getId(), videogameId);
							
							%>
							<p><a class="btn btn-secondary" data-toggle="collapse" href="#collapse-vote" role="button" aria-expanded="false" aria-controls="collapseExample">
			    					Votar
			  					</a></p>
			  					
			  					<% if(thisAssessment != null) { %>
			  				<div class="collapse" id="collapse-vote">
			  					<form id = "create-assessment-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
				
									<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= MakeAssessment.ATR_ACTION %>'/>
									<input id = "assessment-input-classname" type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>" />	
									<input id = "assessment-input-id" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_ID %>" value = "<%=thisAssessment.getId() %>">	
									<input id = "assessment-input-videogameid" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_VIDEOGAMEID %>" value = "<%=thisAssessment.getVideogameId() %>">
									<input id = "assessment-input-userid" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>" value = "<%=thisAssessment.getUserId() %>">
									
									<div>
			  							<p>Estrellas:
			  								<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="1" required <% if(thisAssessment.getValue() == 1) out.print("checked"); %>/>1
			            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="2" <% if(thisAssessment.getValue() == 2) out.print("checked"); %>/>2
			            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="3" <% if(thisAssessment.getValue() == 3) out.print("checked"); %>/>3
			            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="4" <% if(thisAssessment.getValue() == 4) out.print("checked"); %>/>4
			            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="5" <% if(thisAssessment.getValue() == 5) out.print("checked"); %>/>5
			            				</p>
			  						</div>
												
									<label for="assessment-input-subject">Asunto: </label>
									<p><input id = "assessment-input-subject" type = "text" class="form-control" placeholder = "texto..." name = "<%=Assessment.ATR_ASSESSMENT_SUBJECT %>" value = '<%=thisAssessment.getSubject() %>' required></p>
												
									<label for="assessment-input-comment">Comentario: </label>
									<p><textarea id = "assessment-input-comment" class="form-control" placeholder = "texto..." name = "<%=Assessment.ATR_ASSESSMENT_COMMENT %>" required><% if(thisAssessment != null) out.print(thisAssessment.getComment()); %></textarea></p>
												
						            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Editar"></p>
						        </form>
							</div>
			  				<% } else { %>
			  				
			  				<div class="collapse" id="collapse-vote">
			  					<form id = "create-assessment-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
				
									<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= MakeAssessment.ATR_ACTION %>'/>
									<input id = "assessment-input-classname" type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>" />		
									<input id = "assessment-input-videogameid" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_VIDEOGAMEID %>" value = "<%=videogameId %>">
									<input id = "assessment-input-userid" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>" value = "<%=userLoged.getId() %>">
									
									<div>
			  							<p>Estrellas:
			  								<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="1" required/>1
			            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="2"/>2
			            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="3"/>3
			            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="4"/>4
			            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="5"/>5
			            				</p>
			  						</div>
												
									<label for="assessment-input-subject">Asunto: </label>
									<p><input id = "assessment-input-subject" type = "text" class="form-control" placeholder = "texto..." name = "<%=Assessment.ATR_ASSESSMENT_SUBJECT %>" required></p>
												
									<label for="assessment-input-comment">Comentario: </label>
									<p><textarea id = "assessment-input-comment" class="form-control" placeholder = "texto..." name = "<%=Assessment.ATR_ASSESSMENT_COMMENT %>" required></textarea></p>
												
						            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Enviar"></p>
						        </form>
							</div>
			  				
			  				<% } } %>
						</div>


						<!-- SEE ASSESSMENTS -->
						<div class="col-6">
							<p><a class="btn btn-secondary" data-toggle="collapse" href="#collapse-assessments" role="button" aria-expanded="false" aria-controls="collapseExample">
			    					Ver votaciones
			  				</a></p>
		 					<div class="collapse" id="collapse-assessments">
			  					<div class="card card-body">
			  						<%for(Assessment a: assessmentsVideogameList){			  							
			  	  						User userOfVote = (new UserDAO()).read(a.getUserId(), SearchBy.ID);	
			  	  					%>
				  	  					<p><strong><%if(userOfVote!=null)out.println(userOfVote.getUsername());else out.println("NULL");%>: </strong><%= a.getValue() %> Estrellas. </p>
				
					  					<label for="comment-content"><strong><%=a.getSubject() %></strong></label>
										<p><textarea style = "resize:none;" id = "comment-content" class="form-control" readonly><% if(a.getComment()!=null) out.print(a.getComment()); %></textarea></p>
				  	  					
				  	  					<p>
				  	  					<% if(a.getPublicationDate()!=null) 
				  	  						out.print("<strong>Publicado: </strong>" + a.getPublicationDate().toString() + " " + a.getPublicationTime().toString()); %> 
				  	  					</p>
				  	  					<p>
				  	  					<% if( a.getPublicationDate()!= a.getEditDate() || a.getPublicationTime() != a.getEditTime()) { 
				  	  						out.print("<strong>Editado: </strong>" + a.getEditDate().toString() + " " + a.getEditTime().toString()); } %>
				  	  					</p>
				  	  					<hr/>
			  	  					<% } %>
			    					
			  					</div>
			  				</div>
						</div>
						
						
					</div>

				</div>
			<jsp:include page="../mod/footer.jsp"></jsp:include>
		</div>
		
		<% } %>
	</body>

</html>

	
	
		
	
	