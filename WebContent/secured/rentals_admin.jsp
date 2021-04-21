<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Rental" %>
<%@ page import = "edu.ucam.pojos.Videogame" %>
<%@ page import = "edu.ucam.pojos.User" %>

<%@ page import = "edu.ucam.daos.RentalDAO" %>
<%@ page import = "edu.ucam.daos.VideogameDAO" %>
<%@ page import = "edu.ucam.daos.UserDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>

<div id = "rentals-title" class = "col-12">
        <h3 class = "display-3">Alquileres</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-rental-form" class = "form-group" action = "<%= request.getContextPath() %>/CREATE" method = "POST">
			
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Rental.class.getName() %>" />
			
			<label for="rental-input-id">ID: </label>
			<p><input id = "rental-input-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Rental.ATR_RENTAL_ID %>" readonly></p>

		    <label for="rental-input-userid">ID Usuario: </label>
			<p><select id = "rental-input-userid" class="form-control" name = "<%=Rental.ATR_RENTAL_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  <% ArrayList<User> usersRentalList = (new UserDAO()).list();
			  for(int i = 0; i < usersRentalList.size(); ++i) { %>
				  <option value="<%=usersRentalList.get(i).getId() %>"><%=usersRentalList.get(i).getId() %></option>
			  <% } %>
			</select></p>
			
			<label for="rental-input-videogameid">ID Videojuego: </label>
			<p><select id = "rental-input-videogameid" class="form-control" name = "<%=Rental.ATR_RENTAL_VIDEOGAMEID %>">
			  <option value="none" selected>Elige un Videojuego...</option>
			  <% ArrayList<Videogame> videogamesRentalList = (new VideogameDAO()).list();
			  for(int i = 0; i < videogamesRentalList.size(); ++i) { %>
				  <option value="<%=videogamesRentalList.get(i).getId() %>"><%=videogamesRentalList.get(i).getName() %></option>
			  <% } %>
			</select></p>
			
			<label for="rental-input-startdate">Fecha Inicio: </label>
			<p><input id = "rental-input-startdate" type = "date" class="form-control" name = "<%=Rental.ATR_RENTAL_STARTDATE %>" required></p>
			
			<label for="rental-input-enddate">Fecha Devolución: </label>
			<p><input id = "rental-input-enddate" type = "date" class="form-control" name = "<%=Rental.ATR_RENTAL_ENDDATE %>" required></p>
			
			<label for="rental-input-returned">Devuelto: </label>
			<p><input id = "rental-input-returned" type = "checkbox" class="form-control" name = "<%=Rental.ATR_RENTAL_RETURNED %>"></p>
						
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-rental-form" class = "form-group" action = "<%= request.getContextPath() %>/UPDATE" method = "POST" style = "display: 'none';">
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Rental.class.getName() %>" />
			
			<label for="rental-input-update-id">ID: </label>
			<p><input id = "rental-input-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Rental.ATR_RENTAL_ID %>" readonly></p>

		    <label for="rental-input-update-userid">ID Usuario: </label>
			<p><select id = "rental-input-update-userid" class="form-control" name = "<%=Rental.ATR_RENTAL_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  <% for(int i = 0; i < usersRentalList.size(); ++i) { %>
				  <option value="<%=usersRentalList.get(i).getId() %>"><%=usersRentalList.get(i).getId() %></option>
			  <% } %>
			</select></p>
			
			<label for="rental-input-update-videogameid">ID Videojuego: </label>
			<p><select id = "rental-input-update-videogameid" class="form-control" name = "<%=Rental.ATR_RENTAL_VIDEOGAMEID %>">
			  <option value="none" selected>Elige un Videojuego...</option>
			  <% for(int i = 0; i < videogamesRentalList.size(); ++i) { %>
				  <option value="<%=videogamesRentalList.get(i).getId() %>"><%=videogamesRentalList.get(i).getName() %></option>
			  <% } %>
			</select></p>
			
			<label for="rental-input-update-startdate">Fecha Inicio: </label>
			<p><input id = "rental-input-update-startdate" type = "date" class="form-control" name = "<%=Rental.ATR_RENTAL_STARTDATE %>" required></p>
			
			<label for="rental-input-update-enddate">Fecha Devolución: </label>
			<p><input id = "rental-input-update-enddate" type = "date" class="form-control" name = "<%=Rental.ATR_RENTAL_ENDDATE %>" required></p>
			
			<label for="rental-input-update-returned">Devuelto: </label>
			<p><input id = "rental-input-update-returned" type = "checkbox" class="form-control" name = "<%=Rental.ATR_RENTAL_RETURNED %>"></p>
			
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <a id = "input-edit-send" class="btn btn-secondary" role="button" href = "rentals-title" onclick = "cancelUpdateRental()" style = "margin-left: 10px;">Cancelar</a>
            </p>
        </form>
    </div>

    
	  
	<div class = "col-lg-8 col-md-6 col-sm-12">
        <div class = "table-responsive" style = " max-height: 600px !important; overflow: auto;">
            <table class="table table-striped">
               	<thead class = "thead-dark">
                  	<tr>
                     	<th scope="col">ID</th>
                     	<th scope="col">ID Usuario</th>
                     	<th scope="col">ID Videojuego</th>
                     	<th scope="col">Fecha Inicio</th>
                        <th scope="col">Fecha Devolución</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
                <% ArrayList<Rental> rentalsRentalList = (new RentalDAO()).list(); 
			  	for(int i = 0; i < rentalsRentalList.size(); ++i) {
			  		Rental showRental = rentalsRentalList.get(i); %>
					<tr>
                     	<td><%=showRental.getId() %></td>
                     	<td><%=showRental.getUserId() %></td>
                     	<td><%=showRental.getVideogameId() %></td>
                     	<td><%=showRental.getStartDate() %></td>
                     	<td><%=showRental.getEndDate() %></td>
                     	
                        <td>
                            <button type = "submit" class="btn btn-warning" onclick = "updateRental(<%=showRental.toJavaScriptFunction() %>)">Editar</button>
                        </td>
                        <td>
							<form action = "<%= request.getContextPath() %>/DELETE" method = "POST">
                           		<input type = "hidden" name = "<%=Rental.ATR_RENTAL_ID %>" value = "<%=showRental.getId() %>">
                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Rental.class.getName() %>">                
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