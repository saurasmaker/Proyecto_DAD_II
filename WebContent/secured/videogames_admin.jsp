<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

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

<%
	BASE64Encoder b64e = new BASE64Encoder();

	pageContext.setAttribute("videogamesList", new VideogameDAO().list());
	pageContext.setAttribute("categoriesList", new CategoryDAO().list());
%>

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
			<p><input id = "videogame-input-id" type = "text" class="form-control" placeholder = "ID del Videojuego" name = "<%=Videogame.ATR_VIDEOGAME_ID %>" readonly></p>
				
			<label for="videogame-input-name">Nombre: </label>
			<p><input id = "videogame-update-name" type = "text" class="form-control" placeholder = "Introduce el Nombre del Videojuego..." name = "<%=Videogame.ATR_VIDEOGAME_NAME %>" required></p>

		    <label for="videogame-input-description">Descripción: </label>
			<p><textarea id = "videogame-input-description" class="form-control" placeholder = "Introduce la Descripción del Videojuego..." name = "<%=Videogame.ATR_VIDEOGAME_DESCRIPTION %>" required></textarea></p>

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
			<p><input id = "videogame-input-update-id" type = "text" class="form-control" placeholder = "ID del Videojuego" name = "<%=Videogame.ATR_VIDEOGAME_ID %>" readonly></p>
				
			<label for="videogame-input-update-name">Nombre: </label>
			<p><input id = "videogame-input-update-name" type = "text" class="form-control" placeholder = "Introduce el Nombre del Videojuego..." name = "<%=Videogame.ATR_VIDEOGAME_NAME %>" required></p>

		    <label for="videogame-input-update-description">Descripción: </label>
			<p><textarea id = "videogame-input-update-description" class="form-control" placeholder = "Introduce la Descripción del Videojuego..." name = "<%=Videogame.ATR_VIDEOGAME_DESCRIPTION %>" required></textarea></p>

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
				   	
				   		<c:forEach var='videogame' items='${videogamesList}' varStatus=''>
				   		
				   			<% Videogame v = (Videogame) pageContext.getAttribute("videogame"); %>
				   		
				   			<tr>
		                     	<td>${videogame.id}</td>
		                     	<td>${videogame.name}</td>
		                        <td><textarea readonly>${videogame.description}</textarea></td>
		                        <td>${videogame.releaseDate}</td>
		                        <td>${videogame.stock}</td>
		                        <td>${videogame.purchasePrice}</td>
		                        <td>${videogame.rentalPrice}</td>
		                        
		                        <td>
		                            <a class="btn btn-warning" 
		                            	href = "<%=request.getContextPath() %>/secured/admin_page.jsp?UPDATE_VIDEOGAME_PARAMETERS=<%=v.toJavaScriptFunction() %>&<%=Videogame.ATR_VIDEOGAME_ID %>=${videogame.id}#videogames-title" >
		                            	Editar
		                            </a>
		                        </td>
		                        <td>
									<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
									<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>
		                           		<input type = "hidden" name = "<%=Videogame.ATR_VIDEOGAME_ID %>" value = "${videogame.id}">
		                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Videogame.class.getName() %>">
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
	    
	    <% 			
			pageContext.setAttribute("updateVideogameParameters", request.getParameter("UPDATE_VIDEOGAME_PARAMETERS"));
			pageContext.setAttribute("videogameId", request.getParameter(Videogame.ATR_VIDEOGAME_ID));	
		%>
			
		<c:if test='${not empty updateVideogameParameters && not empty videogameId }'>
		
			<!-- AÑADIR IMAGEN AL VIDEOJUEGO SELECCIONADO -->
			<div id = "videogameimage-title" class = "col-12">
		        <h4 class = "" align = "right">Añadir imagen</h4>
		        <hr/>
		    </div>
		    
			<div class = "col-6">
				<form id = "add-videogameimage-form" class = "form-group" enctype="multipart/form-data" action = "<%= request.getContextPath() %>/Controller" method = "POST">
		        	<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
		        	<input id = "videogameimage-input-idvideogame" type = "hidden" name = "<%=VideogameImage.ATR_VIDEOGAMEIMAGE_VIDEOGAMEID %>" value = "${videogameId}">
					<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=VideogameImage.class.getName() %>" />
		        	
		        	
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
					 			<% pageContext.setAttribute("videogameImagesList", (new VideogameImageDAO()).listByVideogameId((String)pageContext.getAttribute("videogameId"))); %>
					 			
					 			<c:forEach var='videogameImage' items='${videogameImagesList}' varStatus='videogameImagesListLoop'>
					 				<c:set var='base64Image' value='<%=b64e.encode(((VideogameImage) pageContext.getAttribute("videogameImage")).getImage())%>'/>
					 				<td><img src="data:image/png;base64,${base64Image}" alt='${videogameImage.name}' width = "50px"/></td>
					 			</c:forEach>
					 			
				 			</tr>
				 			<tr>
				 				<c:forEach var='videogameImage' items='${videogameImagesList}' varStatus='videogameImagesListLoop'>
				 					<td>
										<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
											<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>
			                           		<input type = "hidden" name = "<%=VideogameImage.ATR_VIDEOGAMEIMAGE_ID %>" value = "${videogameImage.id}">
			                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=VideogameImage.class.getName() %>" />
			                           		<button type = "submit" class="btn btn-danger">Eliminar</button>
			                        	</form>
			                        </td>
				 				</c:forEach>

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
				    <input id = "videogameimage-input-idvideogame" type = "hidden" name = "<%=VideogameCategory.ATR_VIDEOGAMESCATEGORIES_VIDEOGAMEID %>" value = "${videogameId}">
					<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=VideogameCategory.class.getName() %>" />
				
		        	<label for="videogame-input-category">Elige una categoría: </label>
					<p><select id = "videogame-input-category" class="form-control" name = "<%=VideogameCategory.ATR_VIDEOGAMESCATEGORIES_CATEGORYID %>">
					  <option value="none" selected>Categoría...</option>
					  <c:forEach var='category' items='${categoriesList}' varStatus=''>
					 	<option value="${category.id}">${category.name}</option>
					  </c:forEach>
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
				 				
				 				<% pageContext.setAttribute("videogameCategoriesList", (new VideogameCategoryDAO()).listByVideogameId((String)pageContext.getAttribute("videogameId"))); %>
				 			
				 				<c:forEach var='videogameCategory' items='${videogameCategoriesList}' varStatus=''>	
				 					<% pageContext.setAttribute("category", new CategoryDAO().read(((VideogameCategory) pageContext.getAttribute("videogameCategory")).getCategoryId(), SearchBy.ID)); %>
				 					<td>${category.name}</td>	
				 				</c:forEach>
				 			
				 			</tr>
				 			<tr>
				 				<c:forEach var='videogameCategory' items='${videogameCategoriesList}' varStatus=''>	
				 					<td>
										<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
											<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>	
			                           		<input type="hidden" name = "<%=VideogameCategory.ATR_VIDEOGAMESCATEGORIES_ID %>" value = "${videogameCategory.id}">
			                           		<input type="hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=VideogameCategory.class.getName() %>" />
			                           		<button type="submit" class="btn btn-danger">Eliminar</button>
			                        	</form>
			                        </td>
				 				</c:forEach>
				 			</tr>
				 		</tbody>
				 	</table>
			 	</div>
			</div>
		
		</c:if>	
		
		


	</div>
	

	
	<br/>  