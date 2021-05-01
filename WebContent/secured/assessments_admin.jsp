<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Assessment" %>
<%@ page import = "edu.ucam.pojos.User" %>
<%@ page import = "edu.ucam.pojos.Videogame" %>

<%@ page import = "edu.ucam.daos.AssessmentDAO" %>
<%@ page import = "edu.ucam.daos.UserDAO" %>
<%@ page import = "edu.ucam.daos.VideogameDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>
<%@ page import = "edu.ucam.actions.admin.*" %>

<%
	pageContext.setAttribute("usersList", (new UserDAO()).list());
	pageContext.setAttribute("videogamesList", (new VideogameDAO()).list());
	pageContext.setAttribute("assessmentsList", (new AssessmentDAO()).list());
%>

	<div id = "assessments-title" class = "col-12">
        <h3 class = "display-3">Rese�as</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-assessment-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
			
			<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>" />
			
			<label for="assessment-input-id">ID: </label>
			<p><input id = "assessment-input-id" type = "text" class="form-control" placeholder = "ID de la Rese�a..." name = "<%=Assessment.ATR_ASSESSMENT_ID %>" readonly></p>
				
			<label for="assessment-input-value">Valor: </label>
			<p><input id = "assessment-input-value" type = "number" min = "1" max = "5" step = "1" class="form-control" placeholder = "n�mero (0,00)..." name = "<%=Assessment.ATR_ASSESSMENT_VALUE %>" required></p>
						
			<label for="assessment-input-subject">Asunto: </label>
			<p><input id = "assessment-input-subject" type = "text" class="form-control" placeholder = "Tu asunto..." name = "<%=Assessment.ATR_ASSESSMENT_SUBJECT %>" required></p>
						
			<label for="assessment-input-comment">Comentario: </label>
			<p><textarea id = "assessment-input-comment" class="form-control" placeholder = "Tu comentario..." name = "<%=Assessment.ATR_ASSESSMENT_COMMENT %>" required></textarea></p>
				
			<label style = "text-decoration: underline black;">Publicaci�n: </label>
			<div class = "row col-12">
				
				<div class = "col-12">
					
					<label for="assessment-input-publicationdate">Fecha de Publicaci�n: </label>
					<p><input id = "assessment-input-publicationdate" type = "date" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_PUBLICATIONDATE %>" required></p>
						
					<label for="assessment-input-publicationtime">Hora de Publicaci�n: </label>
					<p><input id = "assessment-input-publicationtime" type = "time" class="form-control" step="1" name = "<%=Assessment.ATR_ASSESSMENT_PUBLICATIONTIME %>" required></p>
					
				</div>
			</div>	
					
			<label style = "text-decoration: underline black;">Edici�n: </label>
			<div class = "row col-12">
				<div class ="col-12">
				
					<label for="assessment-input-editdate">Fecha de �ltima Edici�n: </label>
					<p><input id = "assessment-input-editdate" type = "date" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_EDITDATE %>"></p>	
				
					<label for="assessment-input-edittime">Hora de �ltima Edici�n: </label>
					<p><input id = "assessment-input-edittime" type = "time" class="form-control" step="1" name = "<%=Assessment.ATR_ASSESSMENT_EDITTIME %>"></p>	
				
				</div>		
			</div>		
			
		    
			<label for="assessment-input-videogameid">ID Videojuego: </label>
			<p><select id = "assessment-input-videogameid" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_VIDEOGAMEID %>">
			  <option value="none" selected>Videojuego...</option>
			  
			  <c:forEach var='videogameAssessment' items='${videogamesList}' varStatus='videogamesAssessmentsListLoop'>
			  	<option value='${videogameAssessment.id}'>${videogameAssessment.name}</option>
			  </c:forEach>

			</select></p>
			
			<label for="assessment-input-userid">ID Usuario: </label>
			<p><select id = "assessment-input-userid" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>">
			  <option value="none" selected>Usuario...</option>
			  
			  <c:forEach var='userAssessment' items='${usersList}' varStatus='usersAssessmentsListLoop'>
			  	<option value='${userAssessment.id}'>${userAssessment.username}</option>
			  </c:forEach>
			  
			</select></p>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-assessment-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST" style = "display: 'none';">
            
            <input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Update.ATR_ACTION %>'/>
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>" />
            
            <label for="assessment-input-update-id">ID: </label>
			<p><input id = "assessment-input-update-id" type = "text" class="form-control" placeholder = "ID de la Rese�a" name = "<%=Assessment.ATR_ASSESSMENT_ID %>" readonly></p>
				
			<label for="assessment-input-update-value">Valor: </label>
			<p><input id = "assessment-input-update-value" type = "number" min = "1" max = "5" step = "1" placeholder = "N�mero..." class="form-control" placeholder = "Introduce el valor de la rese�a..." name = "<%=Assessment.ATR_ASSESSMENT_VALUE %>" required></p>
						
			<label for="assessment-input-update-subject">Asunto: </label>
			<p><input id = "assessment-input-update-subject" type = "text" class="form-control" placeholder = "Tu asunto..." name = "<%=Assessment.ATR_ASSESSMENT_SUBJECT %>" required></p>
						
			<label for="assessment-input-update-comment">Comentario: </label>
			<p><textarea id = "assessment-input-update-comment" class="form-control" placeholder = "Tu comentario..." name = "<%=Assessment.ATR_ASSESSMENT_COMMENT %>" required></textarea></p>
						
			<label style = "text-decoration: underline black;">Publicaci�n: </label>
			<div class = "row col-12">
				
				<div class = "col-12">
					
					<label for="assessment-input-update-publicationdate">Fecha de Publicaci�n: </label>
					<p><input id = "assessment-input-update-publicationdate" type = "date" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_PUBLICATIONDATE %>" required></p>
						
					<label for="assessment-input-update-publicationtime">Hora de Publicaci�n: </label>
					<p><input id = "assessment-input-update-publicationtime" type = "time" class="form-control" step = "1" name = "<%=Assessment.ATR_ASSESSMENT_PUBLICATIONTIME %>" required></p>
					
				</div>
			</div>	
					
			<label style = "text-decoration: underline black;">Edici�n: </label>
			<div class = "row col-12">
				<div class ="col-12">
				
					<label for="assessment-input-editdate">Fecha de �ltima Edici�n: </label>
					<p><input id = "assessment-input-update-editdate" type = "date" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_EDITDATE %>"></p>	
				
					<label for="assessment-input-update-edittime">Hora de �ltima Edici�n: </label>
					<p><input id = "assessment-input-update-edittime" type = "time" class="form-control" step = "1" name = "<%=Assessment.ATR_ASSESSMENT_EDITTIME %>"></p>	
				
				</div>		
			</div>	
						
			<label for="assessment-input-update-videogameid">ID Videojuego: </label>
			<p><select id = "assessment-input-update-videogameid" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_VIDEOGAMEID %>">
			  <option value="none" selected>Elige un Videojuego...</option>
			  <c:forEach var='videogameAssessment' items='${videogamesList}' varStatus='videogamesAssessmentsListLoop'>
			  	<option value='${videogameAssessment.id}'>${videogameAssessment.name}</option>
			  </c:forEach>

			</select></p>
			
			<label for="assessment-input-update-userid">ID Usuario: </label>
			<p><select id = "assessment-input-update-userid" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  
			  <c:forEach var='userAssessment' items='${usersList}' varStatus='usersAssessmentsListLoop'>
			  	<option value='${userAssessment.id}'>${userAssessment.username}</option>
			  </c:forEach>
			  
			</select></p>		
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <a id = "input-edit-send" class="btn btn-secondary smooth-scroller" href = "#assessments-title" role="button" onclick = "cancelUpdateAssessment()" style = "margin-left: 10px;">Cancelar</a>
            </p>
        </form>
    </div>
	  
	<div class = "col-lg-8 col-md-6 col-sm-12">
        <div class = "table-responsive" style = " max-height: 600px !important; overflow: auto;">
            <table class="table table-striped">
               	<thead class = "thead-dark">
                  	<tr>
                     	<th scope="col">ID</th>
                     	<th scope="col">Valor</th>
                     	<th scope="col">Asunto</th>
						<th scope="col">Comentario</th>
						<th scope="col">Fecha de Publicaci�n</th>
                        <th scope="col">Fecha de �ltima Edici�n</th>
                        <th scope="col">ID Videojuego</th>
                        <th scope="col">ID Usuario</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
			   	
				   	<c:forEach var='assessment' items='${assessmentsList}' varStatus=''>
						
						<% Assessment a = (Assessment) pageContext.getAttribute("assessment"); %>
						
				   		<tr>
	                     	<td>${assessment.id}</td>
	                     	<td>${assessment.value}</td>
	                        <td>${assessment.subject}</td>
	                        <td>${assessment.comment}</td>
	                        <td>${assessment.publicationDate}</td>
	                        <td>${assessment.editDate}</td>
	                        <td>${assessment.videogameId}</td>
	                        <td>${assessment.userId}</td>
	                        <td>
	                        	<button type = "submit" class="btn btn-warning" onclick = "updateAssessment(<%=a.toJavaScriptFunction() %>)">Editar</button>           
	                        </td>
	                        <td>
								<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
									<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>
	                           		<input type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_ID %>" value = "${assessment.id}">
	                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>">   
	                           		<button type = "submit" class="btn btn-danger">Eliminar</button>
	                        	</form>
	                        </td>
	                	</tr>
	                	
				   	</c:forEach>
                
				</tbody>
            </table>
        </div>
    </div>
	
	<br/>
		  