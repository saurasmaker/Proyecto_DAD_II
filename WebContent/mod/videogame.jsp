<%@ page language='java' contentType='text/html; charset=ISO-8859-1'
    pageEncoding='ISO-8859-1'%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ taglib uri="infodeotags" prefix="infod" %>

<%@ page import = 'java.util.ArrayList' %> 
<%@ page import = 'sun.misc.BASE64Encoder' %>

<%@ page import = 'edu.ucam.servlets.*' %>
<%@ page import = 'edu.ucam.pojos.*' %> 
<%@ page import = 'edu.ucam.daos.*' %> 
<%@ page import = 'edu.ucam.enums.*' %>

<%@ page import = 'edu.ucam.actions.user.MakeAssessment' %>
<%@ page import = 'edu.ucam.actions.user.AddProductToBasket' %>


<% 
	/*
		Declaration of necessary variables.
	*/
	CategoryDAO categoryDao = new CategoryDAO();
	AssessmentDAO assessmentDao = new AssessmentDAO();	
	UserDAO userDao = new UserDAO();
	
	String videogameId = request.getParameter(Videogame.ATR_VIDEOGAME_ID);
	BASE64Encoder b64e = new BASE64Encoder();

	Videogame thisVideogame = null;
	Assessment userAssessment = null;
	
	ArrayList<Assessment> assessmentsVideogameList = null;
	ArrayList<VideogameImage> videogameImagesList = null;
	ArrayList<VideogameCategory> videogameCategoriesList = null;

	if(videogameId != null) {
		thisVideogame = (new VideogameDAO()).read(videogameId, SearchBy.ID);
		assessmentsVideogameList = assessmentDao.listByVideogameId(videogameId);
		videogameImagesList = (new VideogameImageDAO()).listByVideogameId(videogameId);
		videogameCategoriesList = (new VideogameCategoryDAO()).listByVideogameId(videogameId);
		
		pageContext.setAttribute("thisVideogame", thisVideogame);
		pageContext.setAttribute("assessmentsVideogameList", assessmentsVideogameList);
		pageContext.setAttribute("videogameImagesList", videogameImagesList);
		pageContext.setAttribute("videogameCategoriesList", videogameCategoriesList);
	}
%>

<!DOCTYPE html>

<html lang="es">

	<head>
		<jsp:include page="../mod/head.jsp" />
		<title>Videogame - Infodeo 2</title>
	</head>

	<body>
		<div class = "general container">
			<jsp:include page="../mod/header.jsp"></jsp:include>
			
			
			<c:if test='${not empty thisVideogame}'>
			
				<div class = 'row content show-videogame'>
		
					<div class = 'col-12'>
						<h3 class = 'display-2 text-center'><%=thisVideogame.getName() %></h3>
						<hr width = '50%'/>
						<br/>
					</div>	
					
					
					<div id='carouselExampleIndicators' class='carousel slide col-lg-6 col-md-6 col-sm-12' data-interval='0' data-ride='carousel'>
						<ol class='carousel-indicators'>
						
							<c:if test='${not empty videogameImagesList}'>
								
								<c:forEach var='videogameImage' items='${videogameImagesList}' varStatus='videogameImagesLoop'>
									
									<c:choose>
										<c:when test='${videogameImagesLoop.index == 0}'>
											<li data-target='#carouselExampleIndicators' data-slide-to='${videogameImagesLoop.index}' class='active'/>
										</c:when>
										<c:otherwise>
											<li data-target='#carouselExampleIndicators' data-slide-to='${videogameImagesLoop.index}'/>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							
							</c:if>
						
						</ol>
					  	
					  	<div class='carousel-inner'>
					  	
					  		<c:if test='${not empty videogameImagesList}'>
					  		
					  			<c:forEach var='videogameImage' items='${videogameImagesList}' varStatus='videogameImagesLoop'>
					  				<c:set var='videogameImagesLoopIndex' value='${videogameImagesLoop.index}'/>
					  				<c:set var='base64Image' value='<%= b64e.encode(((VideogameImage)pageContext.getAttribute("videogameImage")).getImage())%>'/>
					  				<div class='carousel-item <%= ((int)pageContext.getAttribute("videogameImagesLoopIndex")) == 0 ? "active" : "" %>'>
							    		<img class='d-block w-100' src='data:image/png;base64,${base64Image}' alt='${videogameImage.name}'>
							    	</div>
					  			</c:forEach>
					  		
					  		</c:if>
	
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
							<infod:smallShowVideogameAttributes purchasePrice="${thisVideogame.purchasePrice}" rentalPrice="${thisVideogame.rentalPrice}" stock="${thisVideogame.stock}"/>
		                    
		                    <li>Categor&iacute;as: 
			                    <strong>
			                    	<c:if test='${not empty videogameCategoriesList}'>
			                    		<c:forEach var='videogameCategory' items='${videogameCategoriesList}' varStatus='videogameCategoriesLoop'>
			                    			<c:set var='category' value='<%=categoryDao.read(((VideogameCategory)pageContext.getAttribute("videogameCategory")).getCategoryId(), SearchBy.ID) %>'/>
			                    			${category.name} 
			                    		</c:forEach>
			                    	</c:if>
								</strong>
		                    </li>
		                    		                    
						</ul>
					
						<br/><strong>Descripción: </strong> <p>${category.description}</p>
					
					
						<c:if test='${thisVideogame.stock > 0}'>
						
							<div class='row'>
			                   	<div class='col-3'>
				                   	<form method = 'POST' action = '<%= request.getContextPath()%>/Controller'>
				                   		<input type = 'hidden' name = <%=Controller.ATR_SELECT_ACTION %> value = '<%= AddProductToBasket.ATR_ACTION %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_PRODUCTID %>' value = '${thisVideogame.id}'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_AMOUNT %>' value = '1'/>
				                       	<input type = 'submit' value = 'Comprar' class='btn btn-primary'/>
				                   	</form>
			                   	</div>
			                   
			                   	<div class = 'col-3'>
				                   	<form method = 'POST' action = '<%= request.getContextPath()%>/Controller'>
				                   		<input type = 'hidden' name = <%=Controller.ATR_SELECT_ACTION %> value = '<%= AddProductToBasket.ATR_ACTION %>'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_PRODUCTID %>' value = '${thisVideogame.id}'/>
				                       	<input type = 'hidden' name = '<%=Basket.ATR_BASKET_AMOUNT %>' value = '-1'/>
				                       	<input type = 'submit' value = 'Alquilar' class='btn btn-primary'/>
				                   	</form>
			                   	</div>
		                   	</div>
						
						</c:if>
		             
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
						
						
							<c:if test='${not empty sessionScope.ATR_USER_LOGGED}'>
								
								<c:set var='userAssessment' value='<%= userAssessment = assessmentDao.readByVideogameUserId(((User)session.getAttribute(User.ATR_USER_LOGGED)).getId(), videogameId) %>'/>
								
								<p><a class="btn btn-secondary" data-toggle="collapse" href="#collapse-vote" role="button" aria-expanded="false" aria-controls="collapseExample">
			    					Votar
			  					</a></p>
			  					
			  					<c:choose>
			  					
			  						<c:when test='${not empty userAssessment}'>
			  						
			  							<div class="collapse" id="collapse-vote">
						  					<form id = "create-assessment-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
							
												<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= MakeAssessment.ATR_ACTION %>'/>
												<input id = "assessment-input-classname" type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>" />	
												<input id = "assessment-input-id" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_ID %>" value = "<%=userAssessment.getId() %>">	
												<input id = "assessment-input-videogameid" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_VIDEOGAMEID %>" value = "<%=userAssessment.getVideogameId() %>">
												<input id = "assessment-input-userid" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>" value = "<%=userAssessment.getUserId() %>">
												
												<div>
						  							<p>Estrellas:
						  								<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="1" required <%= userAssessment.getValue() == 1 ? "checked" : "" %>/>1
						            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="2" <%= userAssessment.getValue() == 2 ? "checked" : "" %>/>2
						            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="3" <%= userAssessment.getValue() == 3 ? "checked" : "" %>/>3
						            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="4" <%= userAssessment.getValue() == 4 ? "checked" : "" %>/>4
						            					<input type="radio" name="<%=Assessment.ATR_ASSESSMENT_VALUE %>" value="5" <%= userAssessment.getValue() == 5 ? "checked" : "" %>/>5
						            				</p>
						  						</div>
															
												<label for="assessment-input-subject">Asunto: </label>
												<p><input id = "assessment-input-subject" type = "text" class="form-control" placeholder = "texto..." name = "<%=Assessment.ATR_ASSESSMENT_SUBJECT %>" value = '<%=userAssessment.getSubject() %>' required></p>
															
												<label for="assessment-input-comment">Comentario: </label>
												<p><textarea id = "assessment-input-comment" class="form-control" placeholder = "texto..." name = "<%=Assessment.ATR_ASSESSMENT_COMMENT %>" required><% if(userAssessment != null) out.print(userAssessment.getComment()); %></textarea></p>
															
									            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Editar"></p>
									        </form>
										</div>
			  						
			  						</c:when>
			  						<c:otherwise>
			  							
			  							<div class="collapse" id="collapse-vote">
						  					<form id = "create-assessment-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
							
												<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= MakeAssessment.ATR_ACTION %>'/>
												<input id = "assessment-input-classname" type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>" />		
												<input id = "assessment-input-videogameid" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_VIDEOGAMEID %>" value = "<%=videogameId %>">
												<input id = "assessment-input-userid" type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>" value = "${sessionScope.ATR_USER_LOGGED.id}">
												
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
			  						
			  						</c:otherwise>
			  					
			  					</c:choose>			  										  				
							
							</c:if>

						</div>


						<!-- SEE ASSESSMENTS -->
						<div class="col-6">
							<p><a class="btn btn-secondary" data-toggle="collapse" href="#collapse-assessments" role="button" aria-expanded="false" aria-controls="collapseExample">
			    					Ver votaciones
			  				</a></p>
		 					<div class="collapse" id="collapse-assessments">
			  					<div class="card card-body">
			  					
			  						<c:forEach var='videogameAssessment' items='${assessmentsVideogameList}' varStatus='videogameAssessmentsLoop'>
			  							
			  							<c:set var='userOfVote' value='<%=userDao.read(((Assessment)pageContext.getAttribute("videogameAssessment")).getUserId(), SearchBy.ID) %>'/>		  			
			  							
			  							<c:if test='${not empty userOfVote}'>
			  								<p><strong>${userOfVote.username}: </strong>${videogameAssessment.value} Estrellas. </p>
			  							</c:if>
			  							
				
					  					<label for="comment-content"><strong>${videogameAssessment.subject}</strong></label>
					  					<p><textarea style = "resize:none;" id = "comment-content" class="form-control" readonly>${videogameAssessment.comment}</textarea></p>
										
				  	  					
				  	  					<p>
				  	  						<c:if test='${not empty videogameAssessment}'>
				  	  							<strong>Publicado: ${videogameAssessment.publicationDate} ${videogameAssessment.publicationTime}</strong>
				  	  						</c:if>
				  	  					</p>
				  	  					<p>
				  	  						<c:if test='${videogameAssessment.publicationDate != videogameAssessment.editDate || videogameAssessment.publicationTime!=videogameAssessment.editTime}'>
				  	  							<strong>Editado: </strong> ${videogameAssessment.editDate} ${videogameAssessment.editTime}
				  	  						</c:if>
				  	  					</p>
				  	  					
				  	  					<hr/>
			  						
			  						</c:forEach>

			  					</div>
			  				</div>
						</div>
												
					</div>

				</div>
			</c:if>
			
			<jsp:include page="../mod/footer.jsp"></jsp:include>
		</div>
	
	</body>

</html>

	
	
		
	
	