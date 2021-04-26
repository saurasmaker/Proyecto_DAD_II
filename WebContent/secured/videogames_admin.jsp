<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.ArrayList" %>
<%@ page import = "sun.misc.BASE64Encoder" %>

<%@ page import = "edu.ucam.enums.*" %>

<%@ page import = "edu.ucam.pojos.Videogame" %>
<%@ page import = "edu.ucam.pojos.Category" %>
<%@ page import = "edu.ucam.pojos.VideogameImage" %>
<%@ page import = "edu.ucam.pojos.VideogameCategory" %>

<%@ page import = "edu.ucam.daos.VideogameDAO" %>
<%@ page import = "edu.ucam.daos.CategoryDAO" %>
<%@ page import = "edu.ucam.daos.VideogameImageDAO" %>
<%@ page import = "edu.ucam.daos.VideogameCategoryDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>
<%@ page import = 'edu.ucam.actions.admin.*' %>

	<div id = "videogames-title" class = "col-12">
        <h3 class = "display-3">Videojuegos</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-videogame-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
			
			<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
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



        <form id = "update-videogame-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
            
            <input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Update.ATR_ACTION %>'/>
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
                <a id = "input-edit-send" class="btn btn-secondary" type = "button" role="button" href = "#videogames-title" onclick = "cancelUpdateVideogame()" style = "margin-left: 10px;">Cancelar</a>
            </p>
        </form>
          
    </div>

    
	
	<div class = "row col-lg-8 col-md-6 col-sm-12">
		<div class = "col-12">
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
	                            <a class="btn btn-warning" 
	                            	href = "<%=request.getContextPath() %>/secured/admin_page.jsp?UPDATE_VIDEOGAME_PARAMETERS=<%=showVideogame.toJavaScriptFunction() %>&<%=Videogame.ATR_VIDEOGAME_ID %>=<%=showVideogame.getId() %>#videogames-title" >
	                            	Editar
	                            </a>
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
	    
	    <% 
		String updateVideogameParameters = request.getParameter("UPDATE_VIDEOGAME_PARAMETERS");
		String videogameId = request.getParameter(Videogame.ATR_VIDEOGAME_ID);
		if(updateVideogameParameters != null && videogameId != null) { 
		%>
			
		<!-- AÑADIR IMAGEN AL VIDEOJUEGO SELECCIONADO -->
		<div id = "videogameimage-title" class = "col-12">
	        <h4 class = "" align = "right">Añadir imagen</h4>
	        <hr/>
	    </div>
	    
		<div class = "col-6">
			<form id = "add-videogameimage-form" class = "form-group" enctype="multipart/form-data" action = "<%= request.getContextPath() %>/Controller" method = "POST">
	        	<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
	        	<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=VideogameImage.class.getName() %>" />
	        	
	        	<input id = "videogameimage-input-idvideogame" type = "hidden" name = "<%=VideogameImage.ATR_VIDEOGAMEIMAGE_VIDEOGAMEID %>" value = "<%=videogameId %>">
	        	
	        	<label for="videogameimage-input-image">Elige una imagen: </label>
				<p><input id = "videogameimage-input-image" type = "file" accept="image/*" class="form-control" name = "<%=VideogameImage.ATR_VIDEOGAMEIMAGE_IMAGE %>" required></p>												
	            
	            <p><input id = "input-send-videogameimage" type = "submit" class="btn btn-primary" value = "Añadir imagen a "></p>
	        </form>
		</div>
		
		<div id = "table-videogameimage" class = "col-6">
			<label>Imágenes </label>
			<div class = "table-responsive" style = " max-height: 600px !important; overflow: auto;">
			 	<table class="table table-striped">
			 		<tbody>
			 			<tr>
				 			<% ArrayList<VideogameImage> videogameImagesVideogameList = (new VideogameImageDAO()).listByVideogameId(videogameId);
				 			System.out.println("ey");
				 			for(int i = 0; i < videogameImagesVideogameList.size(); ++i) {
				 				VideogameImage showVideogameImage = videogameImagesVideogameList.get(i); BASE64Encoder b64e = new BASE64Encoder();%>
				 				
				 				<td><img src="data:image/png;base64,<%= b64e.encode((videogameImagesVideogameList.get(i).getImage()))%>" alt = "<%=showVideogameImage.getName() %>" width = "50px"/></td>
				 				
				 			<% } %>
			 			</tr>
			 			<tr>
				 			<% for(int i = 0; i < videogameImagesVideogameList.size(); ++i) {
				 				VideogameImage showVideogameImage = videogameImagesVideogameList.get(i); %>
				 				
				 				<td>
									<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
										<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>
		                           		<input type = "hidden" name = "<%=VideogameImage.ATR_VIDEOGAMEIMAGE_ID %>" value = "<%=showVideogameImage.getId() %>">
		                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=VideogameImage.class.getName() %>" />
		                           		<button type = "submit" class="btn btn-danger">Eliminar</button>
		                        	</form>
		                        </td>
				 				
				 			<% } %>
			 			</tr>
			 		</tbody>
			 	</table>
			</div>
		
		</div >

		
		<!-- AÑADIR CATEGORÍA AL VIDEOJUEGO SELECCIONADO -->
		<div id = "videogamecategory-title" class = "col-12">
	        <h4 class = "" align = "right">Añadir categoría</h4>
	        <hr/>
	    </div>
	    
		<div class = "col-6">
		
			<form id = "add-videogamecategory-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
			    
			    <input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
			    <input id = "videogameimage-input-idvideogame" type = "hidden" name = "<%=VideogameCategory.ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID %>" value = "<%=videogameId %>">
				<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=VideogameCategory.class.getName() %>" />
			
	        	<label for="videogame-input-category">Elige una categoría: </label>
				<p><select id = "videogame-input-category" class="form-control" name = "<%=VideogameCategory.ATR_VIDEOGAMESCATEGORIES_CATEGORYID %>">
				  <option value="none" selected>Select a User...</option>
				  <% 
				  ArrayList<Category> categoriesVideogameList = (new CategoryDAO()).list();
				  for(int i = 0; i < categoriesVideogameList.size(); ++i) { %>
					  <option value="<%=categoriesVideogameList.get(i).getId() %>"><%=categoriesVideogameList.get(i).getName() %></option>
				  <% } %>
				</select></p>												
	            <p><input id = "input-send-videogamecategory" type = "submit" class="btn btn-primary" value = "Añadir categoria a "></p>
	        </form>
	    </div>
	    
	    
	    <div id = "table-videogamescategories" class = "col-6">
	    	<label>Categorías </label>
	    	<div class = "table-responsive"  style = " max-height: 600px !important; overflow: auto;">
			 	<table class="table table-striped">
			 		<tbody>
			 			<tr>
			 				<% 
			 				ArrayList<VideogameCategory> videogameCategoriesByVideogameIdList = 
			 					(new VideogameCategoryDAO()).listByVideogameId(videogameId);
				 			for(int i = 0; i < videogameCategoriesByVideogameIdList.size(); ++i) {
				 				Category showCategory =  (new CategoryDAO()).read(videogameCategoriesByVideogameIdList.get(i).getCategoryId(), SearchBy.ID); %>
				 				
				 				<td><%=showCategory.getName() %></td>
				 				
				 			<% } %>
			 			</tr>
			 			<tr>
				 			<% for(int i = 0; i < videogameCategoriesByVideogameIdList.size(); ++i) {
								Category showCategory =  (new CategoryDAO()).read(videogameCategoriesByVideogameIdList.get(i).getCategoryId(), SearchBy.ID); %>
				 				
				 				<td>
									<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
										<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>	
		                           		<input type = "hidden" name = "<%=VideogameCategory.ATR_VIDEOGAMESCATEGORIES_ID %>" value = "<%=videogameCategoriesByVideogameIdList.get(i).getId() %>">
		                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=VideogameCategory.class.getName() %>" />
		                           		<button type = "submit" class="btn btn-danger">Eliminar</button>
		                        	</form>
		                        </td>
				 				
				 			<% } %>
			 			</tr>
			 		</tbody>
			 	</table>
		 	</div>
		</div>

		<% } %>

	</div>
	

	
	<br/>  