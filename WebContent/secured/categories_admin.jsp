<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Category" %>
<%@ page import = "edu.ucam.daos.CategoryDAO" %>
<%@ page import = "edu.ucam.servlets.Controller" %>

<div class = "col-12">
        <h3 class = "display-3">Categor�as</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-category-form" enctype = "multipart/form-data" class = "form-group" action = "<%= request.getContextPath() %>/CREATE" method = "POST">
			
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Category.class.getName() %>" />
			
			<label for="category-input-id">ID: </label>
			<p><input id = "category-input-id" type = "text" class="form-control" placeholder = "Identificador de la categor�a" name = "<%=Category.ATR_CATEGORY_ID %>" disabled></p>

		    <label for="category-input-name">Nombre: </label>
			<p><input id = "category-input-name" type = "date" class="form-control" placeholder = "Introduce el nombre de la categor�a..." name = "<%=Category.ATR_CATEGORY_NAME %>" required></p>
		
			<label for="category-input-description">Descripci�n: </label>
			<p><textarea id = "category-input-description" class="form-control" placeholder = "Introduce la descripci�n de la categor�a..." name = "<%=Category.ATR_CATEGORY_DESCRIPTION %>" required></textarea></p>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update_product_form" enctype = "multipart/form-data" class = "form-group" action = "src/crud/products/update_product.php" method = "POST" style = "display: 'none';">
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Category.class.getName() %>" />
			
			<label for="category-input-id">ID: </label>
			<p><input id = "category-input-id" type = "text" class="form-control" placeholder = "Identificador de la categor�a" name = "<%=Category.ATR_CATEGORY_ID %>" disabled></p>

		    <label for="category-input-name">Nombre: </label>
			<p><input id = "category-input-name" type = "date" class="form-control" placeholder = "Introduce el nombre de la categor�a..." name = "<%=Category.ATR_CATEGORY_NAME %>" required></p>
		
			<label for="category-input-description">Descripci�n: </label>
			<p><textarea id = "category-input-description" class="form-control" placeholder = "Introduce la descripci�n de la categor�a..." name = "<%=Category.ATR_CATEGORY_DESCRIPTION %>" required></textarea></p>
            
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <a id = "input-edit-send" class="btn btn-secondary" href = "#" role="button" onclick = "cancelEditCategory()" style = "margin-left: 10px;">Cancelar</a>
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
                     	<th scope="col">Descripci�n</th>
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
                            <button type = "submit" class="btn btn-warning" onclick = "editCategory()">Edit</button>
                        </td>
                        <td>
							<form action = "<%= request.getServletContext() %>/DELETE" method = "POST">
                           		<input type = "hidden" name = "<%=Category.ATR_CATEGORY_ID %>" value = "<%=showCategory.getId() %>">
                           		<button type = "submit" class="btn btn-danger">Remove</button>
                        	</form>
                        </td>
                	</tr>
					  
				<% } %>
				</tbody>
            </table>
        </div>
    </div>
	
	<br/>  
