<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Assessment" %>
<%@ page import = "edu.ucam.pojos.User" %>
<%@ page import = "edu.ucam.pojos.Videogame" %>

<%@ page import = "edu.ucam.daos.AssessmentDAO" %>
<%@ page import = "edu.ucam.daos.UserDAO" %>
<%@ page import = "edu.ucam.daos.VideogameDAO" %>
<%@ page import = "edu.ucam.servlets.Controller" %>


	<div class = "col-12">
        <h3 class = "display-3">Reseñas</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-assessment-form" enctype = "multipart/form-data" class = "form-group" action = "<%= request.getContextPath() %>/CREATE" method = "POST">
			
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>" />
			
			<label for="assessment-input-id">ID: </label>
			<p><input id = "assessment-input-id" type = "text" class="form-control" placeholder = "Introduce el nombre del producto..." name = "<%=Assessment.ATR_ASSESSMENT_ID %>" disabled></p>
				
			<label for="assessment-input-value">Valor: </label>
			<p><input id = "assessment-input-value" type = "text" class="form-control" placeholder = "Introduce el valor de la reseña..." name = "<%=Assessment.ATR_ASSESSMENT_VALUE %>" required></p>
						
			<label for="assessment-input-subject">Asunto: </label>
			<p><input id = "assessment-input-subject" type = "text" class="form-control" placeholder = "Introduce la marca del producto..." name = "<%=Assessment.ATR_ASSESSMENT_SUBJECT %>" required></p>
						
			<label for="assessment-input-comment">Comentario: </label>
			<p><input id = "assessment-input-comment" type = "text" class="form-control" placeholder = "Introduce el modelo del producto..." name = "<%=Assessment.ATR_ASSESSMENT_COMMENT %>" required></p>
						
		    <label for="assessment-input-publicationdate">Fecha de Publicación: </label>
			<p><input id = "assessment-input-publicationdate" type = "date" class="form-control" placeholder = "Introduce el precio del producto formato (n,xy)..." name = "<%=Assessment.ATR_ASSESSMENT_PUBLICATIONDATE %>" required></p>
						
			<label for="assessment-input-editdate">Fecha de última Edición: </label>
			<p><input id = "assessment-input-editdate" type = "date" class="form-control" placeholder = "Introduce la cantidad de stock disponible" name = "<%=Assessment.ATR_ASSESSMENT_EDITDATE %>"></p>
						
			<label for="assessment-input-videogameid">ID Videojuego: </label>
			<p><select id = "assessment-input-videogameid" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_VIDEOGAMEID %>">
			  <option value="none" selected>Select a User...</option>
			  <% 
			  ArrayList<Videogame> videogamesAssessmentList = (new VideogameDAO()).list();
			  for(int i = 0; i < videogamesAssessmentList.size(); ++i) { %>
				  <option value="<%=videogamesAssessmentList.get(i).getId() %>"><%=videogamesAssessmentList.get(i).getName() %></option>
			  <% } %>
			</select></p>
			
			<label for="assessment-input-userid">ID Usuario: </label>
			<p><select id = "assessment-input-userid" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  <% ArrayList<User> usersAssessmentList = (new UserDAO()).list();
			  for(int i = 0; i < usersAssessmentList.size(); ++i) { %>
				  <option value="<%=usersAssessmentList.get(i).getId() %>"><%=usersAssessmentList.get(i).getUsername() %></option>
			  <% } %>
			</select></p>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-assessment-form" enctype = "multipart/form-data" class = "form-group" action = "<%= request.getContextPath() %>/UPDATE" method = "POST" style = "display: 'none';">
            
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Assessment.class.getName() %>" />
            
            <label for="assessment-input-update-id">ID: </label>
			<p><input id = "assessment-input-update-id" type = "text" class="form-control" placeholder = "Introduce el nombre del producto..." name = "<%=Assessment.ATR_ASSESSMENT_ID %>" disabled></p>
				
			<label for="assessment-input-update-value">Valor: </label>
			<p><input id = "assessment-input-update-value" type = "text" class="form-control" placeholder = "Introduce el valor de la reseña..." name = "<%=Assessment.ATR_ASSESSMENT_VALUE %>" required></p>
						
			<label for="assessment-input-update-subject">Asunto: </label>
			<p><input id = "assessment-input-update-subject" type = "text" class="form-control" placeholder = "Introduce la marca del producto..." name = "<%=Assessment.ATR_ASSESSMENT_SUBJECT %>" required></p>
						
			<label for="assessment-input-update-comment">Comentario: </label>
			<p><input id = "assessment-input-update-comment" type = "text" class="form-control" placeholder = "Introduce el modelo del producto..." name = "<%=Assessment.ATR_ASSESSMENT_COMMENT %>" required></p>
						
		    <label for="assessment-input-update-publicationdate">Fecha de Publicación: </label>
			<p><input id = "assessment-input-update-publicationdate" type = "date" class="form-control" placeholder = "Introduce el precio del producto formato (n,xy)..." name = "<%=Assessment.ATR_ASSESSMENT_PUBLICATIONDATE %>" required></p>
						
			<label for="assessment-input-update-editdate">Fecha de última Edición: </label>
			<p><input id = "assessment-input-update-editdate" type = "date" class="form-control" placeholder = "Introduce la cantidad de stock disponible" name = "<%=Assessment.ATR_ASSESSMENT_EDITDATE %>"></p>
						
			<label for="assessment-input-update-videogameid">ID Videojuego: </label>
			<p><select id = "assessment-input-update-videogameid" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>">
			  <option value="none" selected>Elige un Videojuego...</option>
			  <% for(int i = 0; i < videogamesAssessmentList.size(); ++i) { %>
				  <option value="<%=videogamesAssessmentList.get(i).getId() %>"><%=videogamesAssessmentList.get(i).getName() %></option>
			  <% } %>
			</select></p>
			
			<label for="assessment-input-update-userid">ID Usuario: </label>
			<p><select id = "assessment-input-update-userid" class="form-control" name = "<%=Assessment.ATR_ASSESSMENT_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  <% for(int i = 0; i < usersAssessmentList.size(); ++i) { %>
				  <option value="<%=usersAssessmentList.get(i).getId() %>"><%=usersAssessmentList.get(i).getUsername() %></option>
			  <% } %>
			</select></p>		
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <button id = "input-edit-send" class="btn btn-secondary" role="button" onclick = "cancelUpdateAssessment()" style = "margin-left: 10px;">Cancelar</button>
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
						<th scope="col">Fecha de Publicación</th>
                        <th scope="col">Fecha de última Edición</th>
                        <th scope="col">ID Videojuego</th>
                        <th scope="col">ID Usuario</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
                <% ArrayList<Assessment> assessmentsList = (new AssessmentDAO()).list();
			  	for(int i = 0; i < assessmentsList.size(); ++i) {
					Assessment showAssessment = assessmentsList.get(i); %>
					<tr>
                     	<td><%=showAssessment.getId() %></td>
                     	<td><%=showAssessment.getValue() %></td>
                        <td><%=showAssessment.getSubject() %></td>
                        <td><%=showAssessment.getComment() %></td>
                        <td><%=showAssessment.getPublicationDate() %></td>
                        <td><%=showAssessment.getEditDate() %></td>
                        <td><%=showAssessment.getVideogameId() %></td>
                        <td><%=showAssessment.getUserId() %></td>
                        <td>
                        	<button type = "submit" class="btn btn-warning" onclick = "updateAssessment(<%=showAssessment.toJavaScriptFunction() %>)">Editar</button>           
                        </td>
                        <td>
							<form action = "<%= request.getServletContext() %>/DELETE" method = "POST">
                           		<input type = "hidden" name = "<%=Assessment.ATR_ASSESSMENT_ID %>" value = "<%=showAssessment.getId() %>">
                           		<button type = "submit" class="btn btn-danger">Eliminar</button>
                        	</form>
                        </td>
                	</tr>
					  
				<% } %>
				</tbody>
            </table>
        </div>
    </div>
	
	<br/>  