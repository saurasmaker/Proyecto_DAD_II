<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page import = "edu.ucam.pojos.User" %>

<%@ page import = "edu.ucam.servlets.Controller" %>
<%@ page import = 'edu.ucam.actions.admin.*' %>


	<div id = "users-title" class = "col-12">
        <h3 class = "display-3">Usuarios</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-user-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
			
			<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=User.class.getName() %>" />
			
			<label for="user-input-id">ID: </label>
			<p><input id = "user-input-id" type = "text" class="form-control" placeholder = "ID del Usuario" name = "<%=User.ATR_USER_ID %>" readonly></p>
				
			<label for="user-input-username">Nombre de Usuario: </label>
			<p><input id = "user-input-username" type = "text" class="form-control" placeholder = "Introduce el Nombre del Usuario..." name = "<%=User.ATR_USER_USERNAME %>" required></p>

		    <label for="user-input-email">Correo Electrónico: </label>
			<p><input id = "user-input-email" type = "email" class="form-control" placeholder = "Introduce el Correo Electrónico del Usuario..." name = "<%=User.ATR_USER_EMAIL %>" required></p>

			<label for="user-input-password">Contraseña: </label>
			<p><input id = "user-input-password" type = "text" class="form-control" placeholder = "Introduce la Contraseña del Usuario..." name = "<%=User.ATR_USER_PASSWORD %>" required></p>

			<label for="user-input-signupdate">Fecha de Registro: </label>
			<p><input id = "user-input-signupdate" type =  "date" step="1" class="form-control" name = "<%=User.ATR_USER_SIGNUPDATE %>" required></p>
			
			<label for="user-input-signuptime">Tiempo de Registro: </label>
			<p><input id = "user-input-signuptime" type =  "time" step="1" class="form-control" name = "<%=User.ATR_USER_SIGNUPTIME %>" required></p>
			
			<label for="user-input-lastsignindate">Fecha de ultima Autenticación: </label>
			<p><input id = "user-input-lastsignindate" type =  "date" class="form-control" name = "<%=User.ATR_USER_LASTSIGNINDATE %>" required></p>
			
			<label for="user-input-lastsignintime">Tiempo de ultima Autenticación: </label>
			<p><input id = "user-input-lastsignintime" type =  "time" step = "1" class="form-control" name = "<%=User.ATR_USER_LASTSIGNINTIME %>" required></p>
						
			<label for="user-input-isadmin">Admin: </label>
			<p><input id = "user-input-isadmin" type = "checkbox" class="form-control" name = "<%=User.ATR_USER_ISADMIN %>"></p>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-user-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST" style = "display: 'none';">
            
            <input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Update.ATR_ACTION %>'/>
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=User.class.getName() %>" />
			
			<label for="user-input-update-id">ID: </label>
			<p><input id = "user-input-update-id" type = "text" class="form-control" placeholder = "ID del Usuario" name = "<%=User.ATR_USER_ID %>" readonly></p>
				
			<label for="user-input-update-username">Nombre de Usuario: </label>
			<p><input id = "user-input-update-username" type = "text" class="form-control" placeholder = "Introduce el Nombre del Usuario..." name = "<%=User.ATR_USER_USERNAME %>" required></p>

		    <label for="user-input-update-email">Correo Electrónico: </label>
			<p><input id = "user-input-update-email" type = "email" class="form-control" placeholder = "Introduce el Correo Electrónico del Usuario..." name = "<%=User.ATR_USER_EMAIL %>" required></p>

			<label for="user-input-update-password">Contraseña: </label>
			<p><input id = "user-input-update-password" type = "text" class="form-control" placeholder = "Introduce la Contraseña del Usuario..." name = "<%=User.ATR_USER_PASSWORD %>" required></p>

			<label for="user-input-update-signupdate">Fecha de Registro: </label>
			<p><input id = "user-input-update-signupdate" type =  "date" step="1" class="form-control" name = "<%=User.ATR_USER_SIGNUPDATE %>" required></p>
			
			<label for="user-input-update-signuptime">Hora de Registro: </label>
			<p><input id = "user-input-update-signuptime" type =  "time" step="1" class="form-control" name = "<%=User.ATR_USER_SIGNUPTIME %>" required></p>
			
			<label for="user-input-update-lastsignindate">Fecha de última Autenticación: </label>
			<p><input id = "user-input-update-lastsignindate" type =  "date" class="form-control" name = "<%=User.ATR_USER_LASTSIGNINDATE %>" required></p>
			
			<label for="user-input-update-lastsignintime">Hora de última Autenticación: </label>
			<p><input id = "user-input-update-lastsignintime" type =  "time" step = "1" class="form-control" name = "<%=User.ATR_USER_LASTSIGNINTIME %>" required></p>
						
			<label for="user-input-update-isadmin">Admin: </label>
			<p><input id = "user-input-update-isadmin" type = "checkbox" class="form-control" name = "<%=User.ATR_USER_ISADMIN %>"></p>
			
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <a id = "input-edit-send" class="btn btn-secondary smooth-scroller" type = "button" role="button" href = "#users-title" onclick = "cancelUpdateUser()" style = "margin-left: 10px;">Cancelar</a>
            </p>
        </form>
    </div>

    
	  
	<div class = "col-lg-8 col-md-6 col-sm-12">
        <div class = "table-responsive" style = " max-height: 600px !important; overflow: auto;">
            <table class="table table-striped">
               	<thead class = "thead-dark">
                  	<tr>
                     	<th scope="col">ID</th>
                     	<th scope="col">Nombre de Usuario</th>
                     	<th scope="col">Correo Electrónico</th>
						<th scope="col">Contraseña</th>
						<th scope="col">Fecha de Registro</th>
						<th scope="col">Fecha de última Autenticación</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
               	
			   	<tbody>
				   	<c:forEach var='user' items='${usersList}'>
				   		
				   		<% User u = (User) pageContext.getAttribute("user"); %>
				   	
				   		<tr>
	                     	<td>${user.id}</td>
	                     	<td>${user.username}</td>
	                        <td>${user.email}</td>
	                        <td>${user.password}</td>
	                        <td>${user.signUpDate}</td>
	                        <td>${user.lastSignInDate}</td>
	                        
	                        <td>
	                            <button type = "submit" class="btn btn-warning" onclick = "updateUser(<%=u.toJavaScriptFunction() %>)">Editar</button>
	                        </td>
	                        <td>
								<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
									<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>
	                           		<input type = "hidden" name = "<%=User.ATR_USER_ID %>" value = '${user.id}'>
	                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=User.class.getName() %>">
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