<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Videogame" %>

<%@ page import = "edu.ucam.daos.VideogameDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>


	<div class = "col-12">
        <h3 class = "display-3">Videojuegos</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-videogame-form" class = "form-group" action = "<%= request.getContextPath() %>/CREATE" method = "POST">
			
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Videogame.class.getName() %>" />
			
			<label for="videogame-input-id">ID: </label>
			<p><input id = "videogame-input-id" type = "text" class="form-control" placeholder = "ID del Usuario" name = "<%=Videogame.ATR_VIDEOGAME_ID %>" readonly></p>
				
			<label for="videogame-input-name">Nombre: </label>
			<p><input id = "videogame-update-name" type = "text" class="form-control" placeholder = "Introduce el Nombre del Videojuego..." name = "<%=Videogame.ATR_VIDEOGAME_NAME %>" required></p>

		    <label for="videogame-input-description">Descripción: </label>
			<p><textarea id = "videogame-input-description" class="form-control" placeholder = "Introduce el Correo Electrónico del Usuario..." name = "<%=Videogame.ATR_VIDEOGAME_DESCRIPTION %>" required></textarea></p>

			<label for="videogame-input-releasedate">Fecha de Lanzamiento: </label>
			<p><input id = "videogame-input-releasedate" type = "date" class="form-control" name = "<%=Videogame.ATR_VIDEOGAME_RELEASEDATE %>" required></p>
			
			<label for="videogame-input-stock">Stock: </label>
			<p><input id = "videogame-input-stock" type = "number" min = "0" step = "1" class="form-control" name = "<%=Videogame.ATR_VIDEOGAME_STOCK %>" required></p>
			
			<label for="videogame-input-purchaseprice">Precio de Compra: </label>
			<p><input id = "videogame-input-purchaseprice" type = "number" min = "0" step = "0.01" class="form-control" name = "<%=Videogame.ATR_VIDEOGAME_PURCHASEPRICE %>" required></p>
					
			<label for="videogame-input-rentalprice">Precio de Alquiler: </label>
			<p><input id = "videogame-input-rentalprice" type = "number" min = "0" step = "0.01" class="form-control" name = "<%=Videogame.ATR_VIDEOGAME_RENTALPRICE %>" required></p>												

            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-videogame-form" class = "form-group" action = "<%= request.getContextPath() %>/UPDATE" method = "POST" style = "display: 'none';">
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Videogame.class.getName() %>" />
			
			<label for="videogame-input-update-id">ID: </label>
			<p><input id = "videogame-input-update-id" type = "text" class="form-control" placeholder = "ID del Usuario" name = "<%=Videogame.ATR_VIDEOGAME_ID %>" readonly></p>
				
			<label for="videogame-input-update-name">Nombre: </label>
			<p><input id = "videogame-input-update-name" type = "text" class="form-control" placeholder = "Introduce el Nombre del Videojuego..." name = "<%=Videogame.ATR_VIDEOGAME_NAME %>" required></p>

		    <label for="videogame-input-update-description">Descripción: </label>
			<p><textarea id = "videogame-input-update-description" class="form-control" placeholder = "Introduce el Correo Electrónico del Usuario..." name = "<%=Videogame.ATR_VIDEOGAME_DESCRIPTION %>" required></textarea></p>

			<label for="videogame-input-update-releasedate">Fecha de Lanzamiento: </label>
			<p><input id = "videogame-input-update-releasedate" type = "date" class="form-control" name = "<%=Videogame.ATR_VIDEOGAME_RELEASEDATE %>" required></p>
			
			<label for="videogame-input-update-stock">Stock: </label>
			<p><input id = "videogame-input-update-stock" type = "number" min = "0" step = "1" class="form-control" name = "<%=Videogame.ATR_VIDEOGAME_STOCK %>" required></p>
			
			<label for="videogame-input-update-purchaseprice">Precio de Compra: </label>
			<p><input id = "videogame-input-update-purchaseprice" type = "number" min = "0" step = "0.01" class="form-control" name = "<%=Videogame.ATR_VIDEOGAME_PURCHASEPRICE %>" required></p>
					
			<label for="videogame-input-update-rentalprice">Precio de Alquiler: </label>
			<p><input id = "videogame-input-update-rentalprice" type = "number" min = "0" step = "0.01" class="form-control" name = "<%=Videogame.ATR_VIDEOGAME_RENTALPRICE %>" required></p>												

            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <button id = "input-edit-send" class="btn btn-secondary" role="button" onclick = "cancelUpdateVideogame()" style = "margin-left: 10px;">Cancelar</button>
            </p>
        </form>
    </div>

    
	  
	<div class = "col-lg-8 col-md-6 col-sm-12">
        <div class = "table-responsive" style = " max-height: 600px !important; overflow: auto;">
            <table class="table table-striped">
               	<thead class = "thead-dark">
                  	<tr>
                     	<th scope="col">ID</th>
                     	<th scope="col">Nombre</th>
                     	<th scope="col">Descripción</th>
						<th scope="col">Fecha de Lanzamiento</th>
						<th scope="col">Stock</th>
						<th scope="col">Precio de Compra</th>
                        <th scope="col">Precio de Alquiler</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
                <% ArrayList<Videogame> videogamesVideogameList = (new VideogameDAO()).list();
			  	for(int i = 0; i < videogamesVideogameList.size(); ++i) {
					Videogame showVideogame = videogamesVideogameList.get(i); %>
					<tr>
                     	<td><%=showVideogame.getId() %></td>
                     	<td><%=showVideogame.getName() %></td>
                        <td><%=showVideogame.getDescription() %></td>
                        <td><%=showVideogame.getReleaseDate() %></td>
                        <td><%=showVideogame.getStock() %></td>
                        <td><%=showVideogame.getPurchasePrice() %></td>
                        <td><%=showVideogame.getRentalPrice() %></td>
                        
                        <td>
                            <button class="btn btn-warning" onclick = "updateVideogame(<%=showVideogame.toJavaScriptFunction() %>)">Editar</button>
                        </td>
                        <td>
							<form action = "<%= request.getContextPath() %>/DELETE" method = "POST">
                           		<input type = "hidden" name = "<%=Videogame.ATR_VIDEOGAME_ID %>" value = "<%=showVideogame.getId() %>">
                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Videogame.class.getName() %>">
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