<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Category" %>
<%@ page import = "edu.ucam.daos.CategoryDAO" %>
<%@ page import = "edu.ucam.servlets.Controller" %>

<div class = "col-12">
        <h3 class = "display-3">Categorías</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-category-form" class = "form-group" action = "<%= request.getContextPath() %>/CREATE" method = "POST">
			
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Category.class.getName() %>" />
			
			<label for="category-input-id">ID: </label>
			<p><input id = "category-input-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Category.ATR_CATEGORY_ID %>" readonly></p>

		    <label for="category-input-name">Nombre: </label>
			<p><input id = "category-input-name" type = "text" class="form-control" placeholder = "Introduce el nombre de la categoría..." name = "<%=Category.ATR_CATEGORY_NAME %>" required></p>
		
			<label for="category-input-description">Descripción: </label>
			<p><textarea id = "category-input-description" class="form-control" placeholder = "Introduce la descripción de la categoría..." name = "<%=Category.ATR_CATEGORY_DESCRIPTION %>" required></textarea></p>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-category-form" class = "form-group" action = "<%= request.getContextPath() %>/UPDATE" method = "POST" style = "display: 'none';">
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Category.class.getName() %>" />
			
			<label for="category-input-update-id">ID: </label>
			<p><input id = "category-input-update-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Category.ATR_CATEGORY_ID %>" readonly></p>

		    <label for="category-input-update-name">Nombre: </label>
			<p><input id = "category-input-update-name" type = "text" class="form-control" placeholder = "Introduce el nombre de la categoría..." name = "<%=Category.ATR_CATEGORY_NAME %>" required></p>
		
			<label for="category-input-update-description">Descripción: </label>
			<p><textarea id = "category-input-update-description" class="form-control" placeholder = "Introduce la descripción de la categoría..." name = "<%=Category.ATR_CATEGORY_DESCRIPTION %>" required></textarea></p>
            
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <button id = "input-edit-send" class="btn btn-secondary" type = "button" role="button" onclick = "cancelUpdateCategory()" style = "margin-left: 10px;">Cancelar</button>
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
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
                <% ArrayList<Category> categoriesCategoryList = (new CategoryDAO()).list();
			  	for(int i = 0; i < categoriesCategoryList.size(); ++i) {
					Category showCategory = categoriesCategoryList.get(i); %>
					<tr>
                     	<td><%=showCategory.getId() %></td>
                     	<td><%=showCategory.getName() %></td>
                        <td><%=showCategory.getDescription() %></td>
                        <td>
                            <button type = "submit" class="btn btn-warning" onclick = "updateCategory(<%=showCategory.toJavaScriptFunction() %>)">Editar</button>
                        </td>
                        <td>
							<form action = "<%= request.getContextPath() %>/DELETE" method = "POST">
                           		<input type = "hidden" name = "<%=Category.ATR_CATEGORY_ID %>" value = "<%=showCategory.getId() %>">
                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Category.class.getName() %>">   
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

